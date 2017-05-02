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

	readonly logoUrl:string = environment.assetsUrl + "/images/la-san-logo-lite.png";

	readonly gridItems:AppNavLink[] = [
		{
			title: "Android Data Sync",
			subtitle: "Sync Data with Android App",
			img: "https://s-media-cache-ak0.pinimg.com/originals/c2/e6/38/c2e6388177b7835c377f36f2904b449b.jpg",
			route: RestrictedRoute.MOBILE_SYNC_SELECTOR,
			disabled: false
		},
		{
			title: "Unverified Data",
			subtitle: "View and Verify Unverified Data",
			img: "http://wallpapercave.net/images/abstract-wallpapers/abstract-wallpapers-19.jpg",
			route: RestrictedRoute.UNVERIFIED_DATA_SET_LIST,
			disabled: false
		},
		{
			title: "Exceedances",
			subtitle: "Log Exceedance Repairs and Rechecks",
			img: "http://www.ilikewallpaper.net/ipad-wallpapers/download/12644/Dark-Abstract-Design-ipad-wallpaper-ilikewallpaper_com.jpg",
			route: RestrictedRoute.EXCEEDANCE_SELECTOR,
			disabled: false
		},
		{
			title: "Reports",
			subtitle: "Generate Reports",
			img: "http://img.ifreepic.com/1409/27409_icon.jpg",
			route: RestrictedRoute.REPORT_SELECTOR,
			disabled: false
		},
		{
			title: "Equipment",
			subtitle: "Manage Equipment and Equipment Types",
			img: "http://www.uidownload.com/files/403/985/109/rainbow-colorful-background-abstract-design-vector-graphic.jpg",
			route: RestrictedRoute.INSTRUMENT_SELECTOR,
			disabled: false
		},
		{
			title: "Users",
			subtitle: "Manage User Accounts and User Groups",
			img: "https://www.hdwallpapers.net/previews/flame-1054.jpg",
			route: RestrictedRoute.USER_LIST,
			disabled: false
		},
		{
			title: "Notifications",
			subtitle: "Coming Soon",
			img: "https://image.freepik.com/vetores-gratis/modern-abstract-background_1048-1003.jpg",
			route: StatusRoute.COMING_SOON,
			disabled: true
		},
		{
			title: "Information",
			subtitle: "Release Notes and User Manual",
			img: "http://blog.apethebook.com/wp-content/uploads/2013/11/Technology-Background-2973777.jpg",
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
			titleService.setTitle("Dashboard");
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
	}

	ngOnInit() {
		for (let gridItem of this.gridItems) {
			gridItem.visible = !gridItem.route.data || this.authService.canAccess(gridItem.route.data["permissions"]);
		}
	}

	navigate(gridItem:AppNavLink) {
		if (gridItem.disabled) {
			return;
		}
		this.router.navigate(["../" + gridItem.route.path], {relativeTo: this.activatedRoute});
		this.navigationService.getNavbarComponent().openNavDrawer();
	}
	
}