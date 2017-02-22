import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Route, Router } from '@angular/router';
import { RestrictedRoutes } from './../../../app.routing';
import { AuthService } from './../../../services/auth/auth.service';
import { UserRole } from './../../../model/server/model/user-role.enum';

@Component({
	selector: 'app-navigation-base',
	templateUrl: './navigation-base.component.html',
	styleUrls: ['./navigation-base.component.scss']
})
export class NavigationBaseComponent implements OnInit {

	readonly homeSection:NavRouteSection = {
		name: "Home",
		links: [
			{
				route: 'dashboard',
				icon: 'dashboard', 
				label: 'Dashboard',
				visible: false
			},
			{
				route: 'coming-soon',
				icon: 'settings', 
				label: 'Application Settings',
				visible: false
			},
		]
	};

	readonly userManagementSection:NavRouteSection = {
		name: "User Management",
		links: [
			{
				route: 'users', 
				icon: 'people', 
				label: 'Users',
				visible: false
			},
			{
				route: 'user-group-list',
				icon: 'group_work',
				label: 'User Groups',
				visible: false
			},
		]
	};

	readonly instantaneousSection:NavRouteSection = {
		name: "Instantaneous Data",
		links: [
			{
				route: 'ime-numbers',
				icon: 'add',
				label: 'IME List',
				visible: false
			}
		]
	}

	readonly unverifiedDataSection:NavRouteSection = {
		name: "Data Verification",
		links: [
			{
				route: 'unverified-data-set-list',
				icon: 'gesture',
				label: 'Unverified Data Sets',
				visible: false
			}
		]
	}

	readonly reportsSection:NavRouteSection = {
		name: "Reports",
		links: [
			{
				route: 'instantaneous-report',
				icon: 'assignment',
				label: 'Instantaneous Report',
				visible: false
			},
			{
				route: 'coming-soon',
				icon: 'assignment',
				label: 'IME Report',
				visible: false
			},
			{
				route: 'coming-soon',
				icon: 'assignment',
				label: 'Integrated Report',
				visible: false
			},
			{
				route: 'coming-soon',
				icon: 'assignment',
				label: 'ISE Report',
				visible: false
			},
			{
				route: 'coming-soon',
				icon: 'assignment',
				label: 'Probe Report',
				visible: false
			},
			{
				route: 'coming-soon',
				icon: 'email',
				label: 'Email Reports',
				visible: false
			}
		]
	};

	readonly notificationSection:NavRouteSection = {
		name: "Notifications",
		links: [
			{
				route: 'coming-soon',
				icon: 'error',
				label: 'Current Alerts',
				visible: false
			},
			{
				route: 'coming-soon',
				icon: 'warning',
				label: 'Manage Alerts',
				visible: false
			},
			{
				route: 'coming-soon',
				icon: 'email',
				label: 'Email Notification Settings',
				visible: false
			}
		]
	};

	readonly dataTransferSection:NavRouteSection = {
		name: "Data Transfer",
		links: [
			{
				route: 'instantaneous-upload',
				icon: 'file_upload',
				label: 'Upload From Mobile',
				visible: false
			},
			{
				route: 'coming-soon',
				icon: 'sync',
				label: 'Sync To Mobile',
				visible: false
			}
		]
	};

	readonly sections:NavRouteSection[] = [
		this.homeSection,
		this.dataTransferSection,
		this.instantaneousSection,
		this.unverifiedDataSection,
		this.userManagementSection,
		this.reportsSection,
		this.notificationSection,
	];

	constructor (
		private router:Router,
		private http:Http,
		private authService:AuthService,
	) {}

	ngOnInit() {
		for (let i = 0; i < this.sections.length; i++) {
			let section:NavRouteSection = this.sections[i];
			for (let j = 0; j < section.links.length; j++) {
				let link = section.links[j];
				//link.visible = this.routeIsVisible(route.path.split('/'), 0, RestrictedRoutes[0].children);
			}
		}
	}

	logout() {
		this.authService.logout();
	}

	private routeIsVisible(path:string[], level:number, appRoutes:Route[]):boolean {
		let userRoles:UserRole[] = this.authService.getUserRoles();
		if (this.authService.hasRole(UserRole.SUPER_ADMIN, userRoles) || this.authService.hasRole(UserRole.ADMIN, userRoles)) {
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
						if (this.authService.hasRole(userRoles[j], requiredRoles)) {
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

class NavRouteLink {
	route:Route;
	icon:string;
	label:string;
	visible:boolean;
}

class NavRouteSection {
	name:string;
	links:NavRouteLink[];
}