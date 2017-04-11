import { TitleService } from './../../../services/app/title.service';
import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Route, Router, NavigationEnd, ActivatedRouteSnapshot } from '@angular/router';
import { RestrictedRoutes, DefinedRoutes, RestrictedRouteBase } from './../../../app.routing';
import { AuthService } from './../../../services/auth/auth.service';
import { UserPermission } from './../../../model/server/persistence/enums/user-permission.enum';

@Component({
	selector: 'app-navigation-base',
	templateUrl: './navigation-base.component.html',
	styleUrls: ['./navigation-base.component.scss']
})
export class NavigationBaseComponent implements OnInit {

	pageHierarchy:Route[] = [];

	constructor (
		private router:Router,
		private authService:AuthService,
		private titleService:TitleService
	) {}

	ngOnInit() {
		this.router.events.filter((event: any) => event instanceof NavigationEnd).subscribe(() => {
			
			console.log("Router Snapshot:", this.router.routerState.snapshot);
			let root:ActivatedRouteSnapshot = this.router.routerState.snapshot.root;

			// Find the last child.
			while (true) {
				if (!root.firstChild) {
					break;
				}
				root = root.firstChild;
			}

			// Set page title using the activated route's name, if it is defined.
			// If the name is not defined, the route's component probably has a dynamic name that it will set on init.
			if (root.data['name']) {
				this.titleService.setTitle(root.data['name']);
			}
		});
	}

}