import { MdSnackBar } from '@angular/material';
import { FileDownloadService } from './../../../services/file/file-download.service';
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

	readonly uploadCard:SelectorCard = {
		title: "Upload",
		subtitle: "Upload Data from Mobile App",
		img: "https://www.qdtricks.net/wp-content/uploads/2016/05/latest-1080-wallpaper.jpg",
		route: RestrictedRoute.MOBILE_UPLOAD,
		visible: false,
		disabled: false
	};
		
	readonly downloadCard:SelectorCard = {
		title: "Download",
		subtitle: "Download Data to Mobile App",
		img: "https://wallpaperscraft.com/image/sea_coast_rocks_underwater_world_vegetation_fish_53966_1920x1080.jpg",
		route: RestrictedRoute.MOBILE_DOWNLOAD,
		visible: false,
		disabled: false
	};

	constructor(
		private fileDownloadService:FileDownloadService,
		private snackBar:MdSnackBar,
		private authService:AuthService,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
	}

	ngOnInit() {
		this.uploadCard.visible = !this.uploadCard.route.data || this.authService.canAccess(this.uploadCard.route.data["permissions"]);
		this.downloadCard.visible = !this.downloadCard.route.data || this.authService.canAccess(this.downloadCard.route.data["permissions"]);
	}

	download() {
		// this.fileDownloadService.getTestPdf();
		this.fileDownloadService.getMobileDataDump();
	}

}