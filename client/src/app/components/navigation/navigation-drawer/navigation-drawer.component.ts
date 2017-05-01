import { PublicRoute } from './../../../routes/public.route';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { environment } from './../../../../environments/environment';
import { Component, OnInit, Input } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Route, Router } from '@angular/router';
import { RestrictedRoutes } from './../../../app.routing';
import { AuthService } from './../../../services/auth/auth.service';
import { UserPermission } from './../../../model/server/persistence/enums/user/user-permission.enum';
import { MdSidenav } from '@angular/material';

@Component({
	selector: 'app-navigation-drawer',
	templateUrl: './navigation-drawer.component.html',
	styleUrls: ['./navigation-drawer.component.scss']
})
export class NavigationDrawerComponent implements OnInit {

	@Input() sidenav:MdSidenav;

	readonly citySealUrl:string = environment.assetsUrl + "/images/la-seal.svg";

	userName:string;
	userEmail:string;

	links:NavRouteLink[] = [
		{
			route: RestrictedRoute.DASHBOARD,
			icon: 'dashboard', 
			label: 'Dashboard',
			visible: false
		},
		{
			route: RestrictedRoute.MOBILE_SYNC_SELECTOR,
			icon: 'sync',
			label: 'Android Data Sync',
			visible: false
		},
		{
			route: RestrictedRoute.UNVERIFIED_DATA_SET_LIST,
			icon: 'gesture',
			label: 'Unverified Data',
			visible: false
		},
		{
			route: RestrictedRoute.EXCEEDANCE_SELECTOR,
			icon: 'format_list_numbered',
			label: 'Exceedances',
			visible: false
		},
		{
			route: RestrictedRoute.REPORT_SELECTOR,
			icon: 'assignment',
			label: 'Reports',
			visible: false
		},
		{
			route: RestrictedRoute.INSTRUMENT_LIST,
			icon: 'format_paint',
			label: 'Equipment',
			visible: false
		},
		{
			route: RestrictedRoute.USER_LIST, 
			icon: 'people', 
			label: 'Users',
			visible: false
		},
		{
			route: RestrictedRoute.USER_GROUP_LIST,
			icon: 'group_work',
			label: 'User Groups',
			visible: false
		},
		{
			route: PublicRoute.INFO,
			icon: 'info',
			label: 'Information',
			visible: false
		},
	];


	constructor (
		private router:Router,
		private http:Http,
		private authService:AuthService,
	) {}

	ngOnInit() {
		let principle:any = this.authService.getPrinciple();
		if (principle) {
			if (principle["id"] < 0) {
				this.userName = "Super Admin"
			}
			else {
				let middlename = principle["middlename"];
				let mInitial:string = " ";
				if (typeof middlename == "string") {
					mInitial = middlename ? " " + middlename.charAt(0) + ". " : " ";
				}
				this.userName = principle["firstname"] + mInitial + principle["lastname"];
				this.userEmail = principle["emailAddress"];
			}
		}
		for (let i = 0; i < this.links.length; i++) {
			let link = this.links[i];
			link.visible = !link.route.data || this.authService.canAccess(link.route.data["permissions"]);
		}
	}

	logout() {
		this.authService.logout();
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