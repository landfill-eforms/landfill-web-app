import { Router } from '@angular/router';
import { JwtHelper, AuthHttp, AuthConfig } from 'angular2-jwt';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { MdSnackBar } from '@angular/material';
import { RestrictedRouteBase } from './../../app.routing';
import { environment } from './../../../environments/environment';
import { UserPermission } from './../../model/server/persistence/enums/user-permission.enum';

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

	getUserPermissions():UserPermission[] {
		let result = JSON.parse(sessionStorage.getItem("user_permissions"));
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
				sessionStorage.setItem("user_permissions", JSON.stringify(this.parseUserPermissions(jwtToken)));
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

	canAccess(requiredPermissions:any[]) {
		if (!requiredPermissions) {
			return true;
		}
		// TODO Change this to not use ordinal.
		let userPermissions:number[] = this.getUserPermissions().map(r => r.ordinal);
		if (userPermissions.indexOf(0) > -1 || userPermissions.indexOf(1) > -1) {
			return true;
		}
		for (let i = 0; i < requiredPermissions.length; i++) {
			if (userPermissions.indexOf(requiredPermissions[i].ordinal) > -1) {
				return true;
			}
		}
		return false;
	}

	private parseUserPermissions(jwtToken:string):UserPermission[] {
		let claims:any = this.jwtHelper.decodeToken(jwtToken);
		let permissions = claims["permissions"];
		if (permissions && Array.isArray(permissions)) {
			let result:UserPermission[] = [];
			for (let i = 0; i < permissions.length; i++) {
				let permission:UserPermission = UserPermission[permissions[i]];
				if (permission) {
					result.push(permission);
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