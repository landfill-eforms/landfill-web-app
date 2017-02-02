import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Router } from '@angular/router';
import { AuthService } from './../../../services/auth/auth.service';

@Component({
	selector: 'app-navigation-base',
	templateUrl: './navigation-base.component.html',
	styleUrls: ['./navigation-base.component.scss']
})
export class NavigationBaseComponent implements OnInit {

	readonly UserManagementSection:RouteSection = {
		name: "User Management",
		routes: [
			{
				route: 'dashboard',
				icon: 'dashboard', 
				label: 'Dashboard',
				visible: false
			},
			{
				route: 'users', 
				icon: 'people', 
				label: 'Manage Users',
				visible: false
			},
			{
				route: 'instantaneous_upload',
				icon: 'file_upload',
				label: 'Upload File',
				visible: false
			},
			{
				route: 'instantaneous_report',
				icon: 'assignment',
				label: 'Instantaneous Report',
				visible: false
			},
			{
				route: 'instantaneous_report',
				icon: 'assignment',
				label: 'Instantaneous Report',
				visible: false
			}
		]
	};

	constructor (
		private router:Router,
		private http:Http,
		private authService:AuthService,
	) {}

	ngOnInit() {

	}

	logout() {
		this.authService.logout();
	}

}

class Route {
	route:string;
	icon:string;
	label:string;
	visible:boolean;
}

class RouteSection {
	name:string;
	routes:Route[];
}