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

	readonly links:{route:string, icon:string, label:string}[] = [
		{
			route: 'dashboard', 
			icon: 'dashboard', 
			label: 'Dashboard'
		},
		{
			route: 'users', 
			icon: 'people', 
			label: 'Manage Users'
		},
		{
			route: 'instantaneous_upload',
			icon: 'file_upload',
			label: 'Upload File'
		},
		{
			route: 'instantaneous_report',
			icon: 'assignment',
			label: 'Instantaneous Report'
		}
	];

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