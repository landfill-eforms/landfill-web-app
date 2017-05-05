import { NavigationService } from './../../../services/app/navigation.service';
import { FileUploadService, FileUploadResult } from './../../../services/file/file-upload.service';
import { MdSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Component, OnInit, ElementRef } from '@angular/core';
import 'rxjs/Rx';

@Component({
    selector: 'app-mobile-upload',
    templateUrl: './mobile-upload.component.html'
})
export class MobileUploadComponent implements OnInit {

    file:any;

    processing:boolean = false;

    constructor(
        private el:ElementRef, 
        private router:Router,
        private snackBar:MdSnackBar,
		private fileUploadService:FileUploadService,
        private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
	}

    ngOnInit() {

    }

    info() {
        let inputEl = this.el.nativeElement.firstElementChild;
        if (inputEl.files.length > 0) { 
            let file:FileList = inputEl.files[0];
            this.file = inputEl.files[0];
            console.log(this.file);
        }
    }

    test(result:FileUploadResult) {
        this.processing = true;
        if (result.success) {
            setTimeout(() => {
                this.router.navigate(['/app/unverified-data-sets']); 
                this.snackBar.open("File successfully uploaded.", "OK", {duration: 3000});
            }, 5000);
        }
        else {
            this.processing = false;
            console.log(result);   
            this.snackBar.open(JSON.parse(result.data.text()).message, "OK", {duration: 5000});
        }
    }

}