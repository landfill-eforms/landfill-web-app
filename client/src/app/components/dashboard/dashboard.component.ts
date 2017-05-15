import { AppNavLink } from './../../model/client/app-nav-link';
import { PublicRoute } from './../../routes/public.route';
import { Router, ActivatedRoute } from '@angular/router';
import { StatusRoute } from './../../routes/status.route';
import { AuthService } from './../../services/auth/auth.service';
import { RestrictedRoute } from './../../routes/restricted.route';
import { environment } from './../../../environments/environment';
import { NavigationService } from './../../services/app/navigation.service';
import { AppConstant } from './../../app.constant';
import { TitleService } from './../../services/app/title.service';
import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

	/** The page title that displays on the tab, and on the navbar. */
	readonly title:string = "Dashboard";

	/** The list of items that appear on the 'grid' on the dashboard page. */
	readonly gridItems:VectorAppNavLink[] = [
		{
			title: "Android Data Sync",
			subtitle: "Sync Data with Android App",
			img: "sync",
			color: "#FF9800, #FFA726, #FF9100",
			route: RestrictedRoute.MOBILE_SYNC,
			disabled: false
		},
		{
			title: "Unverified Data",
			subtitle: "View and Verify Unverified Data",
			img: "gesture",
			color: "#2196F3, #42A5F5, #2979FF",
			route: RestrictedRoute.UNVERIFIED_DATA_SET_LIST,
			disabled: false
		},
		{
			title: "Exceedances",
			subtitle: "Log Exceedance Repairs and Rechecks",
			img: "whatshot",
			color: "#E53935, #F44336, #FF1744",
			route: RestrictedRoute.EXCEEDANCE_SELECTOR,
			disabled: false
		},
		{
			title: "Reports",
			subtitle: "Generate Reports",
			img: "assignment",
			color: "#4CAF50, #66BB6A, #00E676",
			route: RestrictedRoute.REPORT_SELECTOR,
			disabled: false
		},
		{
			title: "Equipment",
			subtitle: "Manage Equipment and Equipment Types",
			img: "format_paint",
			color: "#673AB7, #7E57C2, #651FFF",
			route: RestrictedRoute.INSTRUMENT_SELECTOR,
			disabled: false
		},
		{
			title: "Users",
			subtitle: "Manage User Accounts and User Groups",
			img: "people",
			color: "#FF5722, #FF7043, #FF3D00",
			route: RestrictedRoute.USER_SELECTOR,
			disabled: false
		},
		{
			title: "Notifications",
			subtitle: "Coming Soon",
			img: "hourglass_empty",
			color: "#EEEEEE, #E0E0E0, #EEEEEE",
			route: StatusRoute.COMING_SOON,
			disabled: true
		},
				{
			title: "Application Setttings",
			subtitle: "Web Application Global Settings",
			img: "settings",
			color: "#E91E63, #EC407A, #F50057",
			route: RestrictedRoute.APPLICATION_SETTINGS,
			disabled: false
		},
		{
			title: "Information",
			subtitle: "Release Notes and User Manual",
			img: "info_outline",
			color: "#00BCD4, #26C6DA, #00E5FF",
			route: PublicRoute.INFO,
			disabled: false
		}
	];

	constructor(
		private titleService:TitleService, 
		private authService:AuthService,
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private navigationService:NavigationService) {
			titleService.setTitle(this.title);
			navigationService.getNavbarComponent().title = this.title;
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
	}

	ngOnInit() {
		// Figure out which items can be accessed by the current user. Hide ones that cannot be accessed.
		for (let gridItem of this.gridItems) {
			gridItem.visible = !gridItem.route.data || this.authService.canAccess(gridItem.route.data["permissions"]);
		}
	}

	/** Navigates the user to the grid item's path. */
	navigate(gridItem:AppNavLink) {
		if (gridItem.disabled) {
			return;
		}
		this.router.navigate(["../" + gridItem.route.path], {relativeTo: this.activatedRoute});
		this.navigationService.getNavbarComponent().openNavDrawer();
	}

}

class VectorAppNavLink extends AppNavLink {
	color?:string;
}
