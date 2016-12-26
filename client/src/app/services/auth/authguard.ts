import {Injectable} from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot} from '@angular/router';
import {tokenNotExpired} from 'angular2-jwt';


@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) {}

    canActivate(route:ActivatedRouteSnapshot):boolean {
        console.log(route.data);

        if (tokenNotExpired()) {
            let routeRoles:string[] = route.data["roles"];
            let userRole:string = localStorage.getItem("user_role");
            console.log(userRole, routeRoles);
            return true;
            // if (routeRoles.indexOf(userRole) >= 0) {
            //     return true;
            // }
        }

        // If not, they redirect them to the login page
        this.router.navigate(['/login']);
        return false;
    }
}