import { AppNavLink } from './../../../model/client/app-nav-link';
import { FileDownloadService } from './../../../services/file/file-download.service';
import { RestrictedRoutes } from './../../../app.routing';
import { NavigationService } from './../../../services/app/navigation.service';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { TitleService } from './../../../services/app/title.service';
import { Component } from '@angular/core';

@Component({
	selector: 'app-mobile-sync-selector',
	templateUrl: './mobile-sync-selector.component.html'
})
export class MobileSyncSelectorComponent {

	readonly title = "Mobile Data Sync";
	readonly subtitle = 
		"Sync data between the Android application and the web application. " +
		"Data files exported from the Android application can be uploaded to the web application, " +
		"and data files downloaded from the web application can be imported into the Android application."

    readonly cards:AppNavLink[] = [
		{
			title: "Upload",
			subtitle: "Upload Data File from Android Application",
			img: "https://www.qdtricks.net/wp-content/uploads/2016/05/latest-1080-wallpaper.jpg",
			route: RestrictedRoute.MOBILE_UPLOAD,
		},
		{
			title: "Download",
			subtitle: "Download Data File to Android Application",
			img: "https://wallpaperscraft.com/image/sea_coast_rocks_underwater_world_vegetation_fish_53966_1920x1080.jpg",
			route: RestrictedRoute.MOBILE_DOWNLOAD,
			action: "download"
		}
	];

	constructor(
		private fileDownloadService:FileDownloadService,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
	}

	download(event) {
		if (event === "download") {
			this.fileDownloadService.getMobileDataDump();
		}
	}

}