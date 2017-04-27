import { AppConstant } from './../../app.constant';
import { Router } from '@angular/router';
import { JwtHelper, AuthHttp, AuthConfig } from 'angular2-jwt';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Injectable } from '@angular/core';
import { MdSnackBar } from '@angular/material';
import { environment } from './../../../environments/environment';
import { UserPermission } from './../../model/server/persistence/enums/user/user-permission.enum';

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

	getUserPermissions():string[] {
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
				console.log("Decoded Token:", this.jwtHelper.decodeToken(jwtToken));
				console.log("Token Expiration:", this.jwtHelper.getTokenExpirationDate(jwtToken));
				sessionStorage.setItem("id_token", jwtToken);
				sessionStorage.setItem("user_permissions", JSON.stringify(this.parseUserPermissions(jwtToken)));
				this.router.navigate(['/' + AppConstant.RESTRICTED_ROUTE_BASE]);
			},
			(error:any) => {
				this.snackBar.open(JSON.parse(error.text()).message, "OK", {duration: 2000});
			});
	}

	logout() {
		sessionStorage.clear();
		this.router.navigate(['/login']);
	}

	canAccess(permissions:UserPermission[]) {

		// If there were no required permissions specified, then allow access.
		if (!permissions || permissions.length == 0) {
			return true;
		}

		// Get string array of current user's permissions.
		let userPermissions:string[] = this.getUserPermissions();

		// If the current user is the super admin, then allow access.
		if (userPermissions.indexOf("SUPER_ADMIN") > -1) {
			return true;
		}

		// *** At this point, it is determined that the user is not the super admin.

		// Convert array of UserPermission into array of strings.
		console.log(permissions);
		let requiredPermissions:string[] = permissions.map(p => p.constantName);

		// If the only the super admin can access the route, then deny access.
		if (requiredPermissions.length == 1 && requiredPermissions[0] == "SUPER_ADMIN") {
			return false;
		}

		// If the user is an admin, then allow access.
		if (userPermissions.indexOf(UserPermission.ADMIN.constantName) > -1) {
			return true;
		}

		// Check if the user has at least one of the required permission.
		for (let i = 0; i < requiredPermissions.length; i++) {
			if (userPermissions.indexOf(requiredPermissions[i]) > -1) {
				return true;
			}
		}
		return false;
	}

	isSuperAdmin():boolean {
		return this.getUserPermissions().indexOf("SUPER_ADMIN") > -1;
	}

	getPrinciple():any {
		let token:any = this.jwtHelper.decodeToken(sessionStorage.getItem('id_token'));
		if (!token || !token.principle) {
			return null;
		}
		return token.principle;
	}

	private parseUserPermissions(jwtToken:string):string[] {
		let claims:any = this.jwtHelper.decodeToken(jwtToken);
		let permissions = claims["permissions"];
		if (permissions && Array.isArray(permissions)) {
			// let result:UserPermission[] = [];
			// for (let i = 0; i < permissions.length; i++) {
			// 	let permission:UserPermission = UserPermission[permissions[i]];
			// 	if (permission) {
			// 		result.push(permission);
			// 	}
			// }
			// return result;
			return permissions;
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