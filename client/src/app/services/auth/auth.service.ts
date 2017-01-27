import { Router } from '@angular/router';
import { JwtHelper, AuthHttp, AuthConfig } from 'angular2-jwt';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { MdSnackBar } from '@angular/material';
import { RestrictedRouteBase } from './../../app.routing';
import { environment } from './../../../environments/environment';

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

	getUserRoles():string[] {
		let claims:any = this.jwtHelper.decodeToken(this.getToken());
		if (claims['roles'] && Array.isArray(claims['roles'])) {
			return claims['roles'];
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
					this.jwtHelper.decodeToken(jwtToken),
					this.jwtHelper.getTokenExpirationDate(jwtToken),
					this.jwtHelper.isTokenExpired(jwtToken),
				);
				sessionStorage.setItem("id_token", jwtToken);
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