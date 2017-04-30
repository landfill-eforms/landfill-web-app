import { RestrictedRoutes } from './../../../app.routing';
import { NavigationService } from './../../../services/app/navigation.service';
import { SelectorCard } from './../../../model/client/selector-card';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { TitleService } from './../../../services/app/title.service';
import { AuthService } from './../../../services/auth/auth.service';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-mobile-sync-selector',
	templateUrl: './mobile-sync-selector.component.html'
})
export class MobileSyncSelectorComponent implements OnInit {

	readonly links:SelectorCard[] = [
		{
			title: "Upload",
			subtitle: "Upload File Exported From Mobile App",
			img: "https://www.qdtricks.net/wp-content/uploads/2016/05/latest-1080-wallpaper.jpg",
			route: RestrictedRoute.MOBILE_UPLOAD,
			visible: false,
			disabled: false
		},
		{
			title: "Download",
			subtitle: "Download File to Import to Mobile App",
			img: "https://wallpaperscraft.com/image/sea_coast_rocks_underwater_world_vegetation_fish_53966_1920x1080.jpg",
			route: RestrictedRoute.MOBILE_DOWNLOAD,
			visible: false,
			disabled: false
		}
	];

	constructor(
		private authService:AuthService,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
	}


	ngOnInit() {
		// console.log(RestrictedRoutes);
		for (let link of this.links) {
			link.visible = !link.route.data || this.authService.canAccess(link.route.data["permissions"]);
		}
	}

}