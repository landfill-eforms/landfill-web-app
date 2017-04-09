import { environment } from './../../../../environments/environment';
import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Route, Router } from '@angular/router';
import { RestrictedRoutes, DefinedRoutes } from './../../../app.routing';
import { AuthService } from './../../../services/auth/auth.service';
import { UserPermission } from './../../../model/server/persistence/enums/user-permission.enum';

@Component({
	selector: 'app-navigation-base',
	templateUrl: './navigation-base.component.html',
	styleUrls: ['./navigation-base.component.scss']
})
export class NavigationBaseComponent implements OnInit {

	readonly logoUrl:string = environment.assetsUrl + "/images/la-san-logo-lite-bright.png";
	readonly citySealUrl:string = environment.assetsUrl + "/images/la-seal.svg";

	readonly homeSection:NavRouteSection = {
		name: "Home",
		links: [
			{
				route: DefinedRoutes.DASHBOARD,
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
				route: DefinedRoutes.USER_LIST, 
				icon: 'people', 
				label: 'Users',
				visible: false
			},
			{
				route: DefinedRoutes.USER_GROUP_LIST,
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
				route: DefinedRoutes.UNVERIFIED_DATA_SET_LIST,
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
				route: DefinedRoutes.INSTANTANEOUS_REPORT,
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
				route: DefinedRoutes.MOBILE_UPLOAD,
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
		// this.instantaneousSection,
		this.unverifiedDataSection,
		this.userManagementSection,
		this.reportsSection,
		this.notificationSection,
	];

	// TEMPORARY
	tempLinks:NavRouteLink[] = [
		{
			route: DefinedRoutes.DASHBOARD,
			icon: 'dashboard', 
			label: 'Dashboard',
			visible: false
		},
		{
			route: DefinedRoutes.USER_LIST, 
			icon: 'people', 
			label: 'Users',
			visible: false
		},
		{
			route: DefinedRoutes.USER_GROUP_LIST,
			icon: 'group_work',
			label: 'User Groups',
			visible: false
		},
		{
			route: DefinedRoutes.MOBILE_UPLOAD,
			icon: 'file_upload',
			label: 'Upload From Mobile',
			visible: false
		},
		{
			route: DefinedRoutes.UNVERIFIED_DATA_SET_LIST,
			icon: 'gesture',
			label: 'Unverified Data Sets',
			visible: false
		},
		{
			route: DefinedRoutes.IME_NUMBER_LIST,
			icon: 'format_list_numbered',
			label: 'IME Numbers',
			visible: false
		},
		{
			route: DefinedRoutes.REPORTS,
			icon: 'assignment',
			label: 'Reports',
			visible: false
		},
		{
			route: DefinedRoutes.INSTRUMENT_LIST,
			icon: 'format_paint',
			label: 'Equipment',
			visible: false
		},
	];

	userName:string;
	userEmail:string;

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
		for (let i = 0; i < this.sections.length; i++) {
			let section:NavRouteSection = this.sections[i];
			for (let j = 0; j < section.links.length; j++) {
				let link = section.links[j];
				link.visible = !link.route.data || this.authService.canAccess(link.route.data["permissions"]);
				console.log(link.route.path, link.visible);
			}
		}
		for (let i = 0; i < this.tempLinks.length; i++) {
			let link = this.tempLinks[i];
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