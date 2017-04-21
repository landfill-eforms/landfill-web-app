import { MdSnackBar } from '@angular/material';
import { FileUploadService } from './../../../services/file/file-upload.service';
import { Router } from '@angular/router';
import { Component, Input, ElementRef, ViewChild } from '@angular/core';


// Source: http://stackoverflow.com/questions/36352405/file-upload-with-angular2-to-rest-api/39862337#39862337
@Component({
    selector: 'app-file-upload',
    templateUrl: './file-upload.component.html',
    styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent {

    @Input() multiple:boolean = false;
    @Input() uploadRestUrl:string;
    @ViewChild('fileInput') el:ElementRef;

    selectedFileNames:string[] = [];

    constructor(
        private router:Router,
        private snackBar:MdSnackBar,
        private fileUploadService:FileUploadService
        ) {}

    fileSelected() {
        let inputEl:HTMLInputElement = this.el.nativeElement;
        console.log(inputEl.files);
        let fileCount:number = inputEl.files.length;
        this.selectedFileNames = [];
        for (let i = 0; i < fileCount; i++) {
            this.selectedFileNames.push(inputEl.files.item(i).name);
        }
    }

    upload() {
        let inputEl:HTMLInputElement = this.el.nativeElement;
        console.log(inputEl.files);
        let fileCount:number = inputEl.files.length;
        let formData = new FormData();
        if (fileCount > 0) {
            for (let i = 0; i < fileCount; i++) {
                formData.append('file', inputEl.files.item(i));
            }
            this.fileUploadService.upload("mobile", formData,
                (data) => {
                    console.log(data);

                    // TODO Move these somewhere else.
                    this.router.navigate(['/app/unverified-data-sets']); 
                    this.snackBar.open("File successfully uploaded.", "OK", {duration: 3000});
                    
                },
                (err) => {
                    this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
                }
            );
        }
    }

}