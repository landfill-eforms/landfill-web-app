import { AuthService } from './auth.service';
import { tokenNotExpired, JwtHelper } from 'angular2-jwt';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthGuard implements CanActivate {

	constructor(
		private router: Router,
		private authService:AuthService
	) {}

	canActivate(route:ActivatedRouteSnapshot):boolean {

		console.log(route.data);

		if (this.authService.isTokenExpired()) {
			console.log("AuthGuard: JWT Token is expired...")
			this.router.navigate(['/login']);
			// TODO Add inactivity message.
			return false;
		}

		let userRoles:string[] = this.authService.getUserRoles();
		if (userRoles.indexOf("SUPER_ADMIN") > -1 || userRoles.indexOf("ADMIN") > -1) {
			console.log("AuthGuard: Allowing access because user is an admin.")
			return true;
		}

		let requiredRoles:string[] = route.data["roles"];
		for (let i = 0; i < userRoles.length; i++) {
			if (requiredRoles.indexOf(userRoles[i]) > -1) {
				console.log("AuthGuard: Allowing access because user has one or more required roles.")
				return true;
			}
		}

		console.log("AuthGuard: Denying access because user doesn't have any of the requred roles.")
		this.router.navigate(['/forbidden']);
		return false;

	}

}