import { UserPermission } from './../../../model/server/persistence/enums/user/user-permission.enum';
import { MdSnackBar } from '@angular/material';
import { Router, ActivatedRouteSnapshot } from '@angular/router';
import { FileUploadResult, FileUploadService } from './../../../services/file/file-upload.service';
import { AuthService } from './../../../services/auth/auth.service';
import { environment } from './../../../../environments/environment.prod';
import { AppNavLink } from './../../../model/client/app-nav-link';
import { FileDownloadService } from './../../../services/file/file-download.service';
import { RestrictedRoutes } from './../../../app.routing';
import { NavigationService } from './../../../services/app/navigation.service';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { TitleService } from './../../../services/app/title.service';
import { Component, OnInit, ElementRef, AfterViewInit } from '@angular/core';

@Component({
	selector: 'app-mobile-sync',
	templateUrl: './mobile-sync.component.html',
	styleUrls: ['./mobile-sync.component.scss']
})
export class MobileSyncComponent implements OnInit, AfterViewInit {

	readonly title = "Android Data Sync";
	readonly subtitle = 
		"Sync data between the Android application and the web application. " +
		"Data files exported from the Android application can be uploaded to the web application, " +
		"and data files downloaded from the web application can be imported into the Android application."

	canUpload:boolean;
	canDownload:boolean;

	uploadDropdownOpen:boolean;

	file:any;

	constructor(
		private el:ElementRef, 
        private router:Router,
        private snackBar:MdSnackBar,
		private fileUploadService:FileUploadService,
		private authService:AuthService,
		private fileDownloadService:FileDownloadService,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
	}

	ngOnInit() {
		this.canUpload = this.authService.canAccess([UserPermission.UPLOAD_MOBILE_DATA]);
		this.canDownload = this.authService.canAccess([UserPermission.DOWNLOAD_MOBILE_DATA]);
	}

	ngAfterViewInit() {
		if (this.router.url.endsWith(RestrictedRoute.MOBILE_UPLOAD.path)) {
			this.toggleUploadDropdown();
		}
	}

	toggleUploadDropdown() {
		this.uploadDropdownOpen = !this.uploadDropdownOpen;
	}

    info() {
        let inputEl = this.el.nativeElement.firstElementChild;
        if (inputEl.files.length > 0) { 
            let file:FileList = inputEl.files[0];
            this.file = inputEl.files[0];
            console.log(this.file);
        }
    }

    upload(result:FileUploadResult) {
        if (result.success) {
            setTimeout(() => {
                this.router.navigate(['/app/unverified-data-sets']); 
                this.snackBar.open("File successfully uploaded.", "OK", {duration: 3000});
            }, 5000);
        }
        else {
            console.log(result);   
            this.snackBar.open(JSON.parse(result.data.text()).message, "OK", {duration: 5000});
        }
    }

	download() {
		this.fileDownloadService.getMobileDataDump();
	}

}