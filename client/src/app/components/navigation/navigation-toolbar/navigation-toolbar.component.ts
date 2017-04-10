import { AuthService } from './../../../services/auth/auth.service';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
import { environment } from './../../../../environments/environment';

@Component({
	selector: 'app-navigation-toolbar',
	templateUrl: './navigation-toolbar.component.html',
	styleUrls: ['./navigation-toolbar.component.scss']
})
export class NavigationToolbarComponent implements OnInit { 

	@Input() navdrawer;

	readonly logoUrl:string = environment.assetsUrl + "/images/la-san-logo-lite-bright.png";

	constructor (
		private router:Router,
		private route:ActivatedRoute,
		private authService:AuthService,
	) {}

	ngOnInit() {

	}

	logout() {
		this.authService.logout();
	}

	action() {
		console.log(this.navdrawer);
		this.navdrawer.toggle();
		console.log(this.router);
	}
	
}