import { Title } from '@angular/platform-browser';
import { environment } from './../../../../environments/environment';
import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Route, Router, NavigationEnd } from '@angular/router';
import { RestrictedRoutes, DefinedRoutes } from './../../../app.routing';
import { AuthService } from './../../../services/auth/auth.service';
import { UserPermission } from './../../../model/server/persistence/enums/user-permission.enum';

@Component({
	selector: 'app-navigation-base',
	templateUrl: './navigation-base.component.html',
	styleUrls: ['./navigation-base.component.scss']
})
export class NavigationBaseComponent implements OnInit {

	constructor (
		private router:Router,
		private authService:AuthService,
		private titleService:Title
	) {}

	ngOnInit() {
		this.router.events.filter((event: any) => event instanceof NavigationEnd).subscribe(() => {
			console.log(this.router.routerState.snapshot.root);
			let root = this.router.routerState.snapshot.root;
			if (root.firstChild) {
				let data = root.firstChild.data;
				console.log(root.firstChild.data);
				if (data) {

					// Set page title using the route's name. Use default title if the route's name is undefined.
					if (data['name']) {
						this.titleService.setTitle(data['name']);
					}
					else {
						this.titleService.setTitle("Landfill e-Forms Web Application");
					}
				}
			}
		});
	}

}