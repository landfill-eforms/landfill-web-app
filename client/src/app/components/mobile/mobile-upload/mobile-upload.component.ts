import { FileUploadService } from './../../../services/file/file-upload.service';
import { MdSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Component, OnInit, ElementRef } from '@angular/core';
import 'rxjs/Rx';

@Component({
    selector: 'app-mobile-upload',
    templateUrl: './mobile-upload.component.html',
    styleUrls: ['./mobile-upload.component.scss']
})
export class MobileUploadComponent implements OnInit {

    file:any;

    constructor(
        private el:ElementRef, 
        private router:Router,
        private snackBar:MdSnackBar,
		private fileUploadService:FileUploadService
        ) {}

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

}