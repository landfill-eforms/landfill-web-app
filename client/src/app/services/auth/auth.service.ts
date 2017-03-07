import { Router } from '@angular/router';
import { JwtHelper, AuthHttp, AuthConfig } from 'angular2-jwt';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { MdSnackBar } from '@angular/material';
import { RestrictedRouteBase } from './../../app.routing';
import { environment } from './../../../environments/environment';
import { UserRole } from './../../model/server/persistence/enums/user-role.enum';

@Injectable()
export class AuthService {

	jwtHelper:JwtHelper = new JwtHelper();

	constructor(
		private http:Http,
		private router:Router,
		private snackBar:MdSnackBar
	) {}

	getToken():string {
		return sessionStorage.getItem('id_token');
	}

	isTokenExpired():boolean {
		return this.jwtHelper.isTokenExpired(this.getToken());
	}

	getUserRoles():UserRole[] {
		let result = JSON.parse(sessionStorage.getItem("user_roles"));
		if (Array.isArray(result)) {
			return result;
		}
		return [];
	}

	login(username:string, password:string) {
		let body = JSON.stringify({username, password});
		let contentHeaders = new Headers({
			'Accept': 'application/json',
			'Content-Type': 'text/plain',
		});
		this.http.post(environment.loginUrl, body, {headers: contentHeaders}).subscribe(
			(response:Response) => {
				let jwtToken:string = response.headers.get("Authorization").replace("Bearer ", "");
				console.log("JWT Token:", jwtToken);
				console.log(
					"Decoded Token",
					this.jwtHelper.decodeToken(jwtToken),
					this.jwtHelper.getTokenExpirationDate(jwtToken)
				);
				sessionStorage.setItem("id_token", jwtToken);
				sessionStorage.setItem("user_roles", JSON.stringify(this.parseUserRoles(jwtToken)));
				this.router.navigate(['/' + RestrictedRouteBase]);
			},
			(error:any) => {
				this.snackBar.open(JSON.parse(error.text()).message, "OK", {duration: 2000});
			});
	}

	logout() {
		sessionStorage.clear();
		this.router.navigate(['/login']);
	}

	canAccess(requiredRoles:any[]) {
		if (!requiredRoles) {
			return true;
		}
		let userRoles:number[] = this.getUserRoles().map(r => r.ordinal);
		if (userRoles.indexOf(0) > -1 || userRoles.indexOf(1) > -1) {
			return true;
		}
		for (let i = 0; i < requiredRoles.length; i++) {
			if (userRoles.indexOf(requiredRoles[i].ordinal) > -1) {
				return true;
			}
		}
		return false;
	}

	private parseUserRoles(jwtToken:string):UserRole[] {
		let claims:any = this.jwtHelper.decodeToken(jwtToken);
		let roles = claims["roles"];
		if (roles && Array.isArray(roles)) {
			let result:UserRole[] = [];
			for (let i = 0; i < roles.length; i++) {
				let role:UserRole = UserRole[roles[i]];
				if (role) {
					result.push(role);
				}
			}
			return result;
		}
		return [];
	}

}

export function authFactory(http:Http, options:RequestOptions) {
	return new AuthHttp(new AuthConfig({
		headerName: 'Authorization',
		headerPrefix: 'Bearer',
		tokenName: 'id_token',
		tokenGetter: (() => sessionStorage.getItem('id_token')),
		//globalHeaders: [{ 'Content-Type': 'text/plain' }],
		noJwtError: false
	}), http, options);
};

export const AuthProvider = {
	provide: AuthHttp,
	deps: [Http, RequestOptions],
	useFactory: authFactory
};