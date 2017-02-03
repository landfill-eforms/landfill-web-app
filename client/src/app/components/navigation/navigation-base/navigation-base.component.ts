import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Route, Router } from '@angular/router';
import { RestrictedRoutes } from './../../../app.routing';
import { AuthService } from './../../../services/auth/auth.service';
import { UserRole } from './../../../model/lib/enumeration/user-role.enum';

@Component({
	selector: 'app-navigation-base',
	templateUrl: './navigation-base.component.html',
	styleUrls: ['./navigation-base.component.scss']
})
export class NavigationBaseComponent implements OnInit {

	readonly homeSection:RouteSection = {
		name: "Home",
		routes: [
			{
				path: 'dashboard',
				icon: 'dashboard', 
				label: 'Dashboard',
				visible: false
			},
		]
	};

	readonly userManagementSection:RouteSection = {
		name: "User Management",
		routes: [
			{
				path: 'users', 
				icon: 'people', 
				label: 'Manage Users',
				visible: false
			},
			{
				path: 'instantaneous_upload',
				icon: 'file_upload',
				label: 'Upload File',
				visible: false
			},
		]
	};

	readonly reportsSection:RouteSection = {
		name: "Reports",
		routes: [
			{
				path: 'instantaneous_report',
				icon: 'assignment',
				label: 'Instantaneous Report',
				visible: false
			}
		]
	};

	readonly dataTransferSection:RouteSection = {
		name: "Data Transfer",
		routes: [
			{
				path: 'instantaneous_upload',
				icon: 'file_upload',
				label: 'Upload From Mobile',
				visible: false
			}
		]
	};

	readonly sections:RouteSection[] = [
		this.homeSection,
		this.userManagementSection,
		this.reportsSection
	];

	constructor (
		private router:Router,
		private http:Http,
		private authService:AuthService,
	) {}

	ngOnInit() {
		for (let i = 0; i < this.sections.length; i++) {
			let section:RouteSection = this.sections[i];
			for (let j = 0; j < section.routes.length; j++) {
				let route = section.routes[j];
				route.visible = this.routeIsVisible(route.path.split('/'), 0, RestrictedRoutes[0].children);
			}
		}
	}

	logout() {
		this.authService.logout();
	}

	private routeIsVisible(path:string[], level:number, appRoutes:Route[]):boolean {
		let userRoles:UserRole[] = this.authService.getUserRoles();
		if (userRoles.indexOf(UserRole.SUPER_ADMIN) > -1 || userRoles.indexOf(UserRole.ADMIN) > -1) {
			return true;
		}
		for (let i = 0; i < appRoutes.length; i++) {
			let route:Route = appRoutes[i];
			if (route.path === path[level]) {
				if (path[level + 1]) {
					if (route.children && route.children.length) {
						return this.routeIsVisible(path, level + 1, route.children);
					}
					return false;
				}
				else if (route.data && route.data["roles"] && Array.isArray(route.data["roles"]) && route.data["roles"].length) {
					let requiredRoles:UserRole[] = route.data["roles"];
					for (let j = 0; j < userRoles.length; j++) {
						if (userRoles.indexOf(requiredRoles[j]) > -1) {
							return true;
						}
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

}

class RouteLink {
	path:string;
	icon:string;
	label:string;
	visible:boolean;
}

class RouteSection {
	name:string;
	routes:RouteLink[];
}