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

	jwtHelper: JwtHelper = new JwtHelper();

	constructor(
		private http: Http,
		private router: Router,
		private snackBar: MdSnackBar
	) {}

	getToken(): string {
		return localStorage.getItem('id_token');
	}

	isTokenExpired(): boolean {
		return this.jwtHelper.isTokenExpired(this.getToken());
	}

	getUserPermissions(): string[] {
		let result = JSON.parse(localStorage.getItem("user_permissions"));
		if (Array.isArray(result)) {
			return result;
		}
		return [];
	}

	login(username: string, password: string) {
		let body = JSON.stringify({username, password});
		let contentHeaders = new Headers({
			'Accept': 'application/json',
			'Content-Type': 'text/plain',
		});
		this.http.post(environment.loginUrl, body, {headers: contentHeaders}).subscribe(
			(response: Response) => {
				let jwtToken: string = response.headers.get("Authorization").replace("Bearer ", "");
				console.log("JWT Token: ", jwtToken);
				console.log("Decoded Token: ", this.jwtHelper.decodeToken(jwtToken));
				console.log("Token Expiration: ", this.jwtHelper.getTokenExpirationDate(jwtToken));
				localStorage.setItem("id_token", jwtToken);
				localStorage.setItem("user_permissions", JSON.stringify(this.parseUserPermissions(jwtToken)));
				this.router.navigate(['/' + AppConstant.RESTRICTED_ROUTE_BASE]);
			},
			(error: any) => {
				this.snackBar.open(JSON.parse(error.text()).message, "OK", {duration: 3000});
			});
	}

	logout() {
		localStorage.clear();
		this.router.navigate(['/login']);
	}

	/**
	 * Returns true if the current user has at least one of the given permissions.
	 * The given permission can be input as a single UserPermission or an array of UserPermission.
	 * @param permissions The permission(s) that the current user will be checked for.
	 *                    Can be specified either as a single UserPermission or an array of UserPermission.
	 */
	canAccess(permissions?: UserPermission | UserPermission[]): boolean {

		let requiredPermissions: UserPermission[];

		// If there were no required permissions specified, then allow access.
		if (!permissions) {
			return true;
		}
		else if (Array.isArray(permissions)) {
			if (permissions.length == 0) {
				return true;
			}
			requiredPermissions = permissions;
		}

		// if the input permission was not an array, then convert it to a array with one value.
		else {
			requiredPermissions = [permissions]
		}

		// Get string array of current user's permissions.
		let userPermissions: string[] = this.getUserPermissions();

		// If the current user is the super admin, then allow access.
		if (userPermissions.indexOf("SUPER_ADMIN") > -1) {
			return true;
		}

		// *** At this point, it is determined that the user is not the super admin.

		// Convert array of UserPermission into array of strings.
		console.log(requiredPermissions);
		let requiredPermissionStrings: string[] = requiredPermissions.map(p => p.constantName);

		// If the only the super admin can access the route, then deny access.
		if (requiredPermissionStrings.length == 1 && requiredPermissionStrings[0] == "SUPER_ADMIN") {
			return false;
		}

		// If the user is an admin, then allow access.
		if (userPermissions.indexOf(UserPermission.ADMIN.constantName) > -1) {
			return true;
		}

		// Check if the user has at least one of the required permission.
		for (let requiredPermission of requiredPermissionStrings) {
			if (userPermissions.indexOf(requiredPermission) > -1) {
				return true;
			}
		}
		return false;
	}

	/** Returns true if the currently logged in user is the super admin. */
	isSuperAdmin(): boolean {
		return this.getUserPermissions().indexOf("SUPER_ADMIN") > -1;
	}

	getPrinciple(): any {
		let token: any = this.jwtHelper.decodeToken(localStorage.getItem('id_token'));
		if (!token || !token.principle) {
			return null;
		}
		return token.principle;
	}

	private parseUserPermissions(jwtToken: string): string[] {
		let claims: any = this.jwtHelper.decodeToken(jwtToken);
		let permissions = claims["permissions"];
		if (permissions && Array.isArray(permissions)) {
			return permissions;
		}
		return [];
	}

}

export function authFactory(http: Http, options: RequestOptions) {
	return new AuthHttp(new AuthConfig({
		headerName: 'Authorization',
		headerPrefix: 'Bearer',
		tokenName: 'id_token',
		tokenGetter: (() => localStorage.getItem('id_token')),
		noJwtError: false
	}), http, options);
};

export const AuthProvider = {
	provide: AuthHttp,
	deps: [Http, RequestOptions],
	useFactory: authFactory
};