import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Router } from '@angular/router';
import { AuthService } from './../../../services/auth/auth.service';

@Component({
	selector: 'navigation-base',
	templateUrl: './navigation-base.component.html',
	styleUrls: ['./navigation-base.component.scss']
})
export class NavigationBaseComponent implements OnInit {

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