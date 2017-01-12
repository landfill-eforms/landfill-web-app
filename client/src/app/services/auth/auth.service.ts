import { Router } from '@angular/router';
import { JwtHelper } from 'angular2-jwt';
import { Headers, Http, Response } from '@angular/http';
import { Injectable } from '@angular/core';
import { RestrictedRouteBase } from './../../app.routing';
import { environment } from './../../../environments/environment';

@Injectable()
export class AuthService {

	jwtHelper:JwtHelper = new JwtHelper();

	constructor(
		private http:Http,
		private router:Router,
	) {}

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
				let claims:any = this.jwtHelper.decodeToken(jwtToken);
				sessionStorage.setItem("id_token", jwtToken);
				sessionStorage.setItem("username", claims["username"]);
				this.router.navigate(['/' + RestrictedRouteBase + '/instantaneous_report']);
			},
			(error:any) => {
			console.log(error.text());
		});
	}

	logout() {
		sessionStorage.clear();
		this.router.navigate(['/login']);
	}

}