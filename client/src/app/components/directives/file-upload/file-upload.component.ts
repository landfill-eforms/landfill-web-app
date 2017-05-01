import { MdSnackBar } from '@angular/material';
import { FileUploadService, FileUploadResult } from './../../../services/file/file-upload.service';
import { Router } from '@angular/router';
import { Component, Input, ElementRef, ViewChild, Output, EventEmitter } from '@angular/core';


// Source: http://stackoverflow.com/questions/36352405/file-upload-with-angular2-to-rest-api/39862337#39862337
@Component({
    selector: 'app-file-upload',
    templateUrl: './file-upload.component.html',
    styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent {

    @Input() multiple:boolean = false;
    @Input() restUrl:string;
    @Input() label:string = "SELECT FILE";
    @Output() uploadResult = new EventEmitter<FileUploadResult>();
    @ViewChild('fileInput') el:ElementRef;

    selectedFileNames:string[] = [];

    constructor(
        private router:Router,
        private snackBar:MdSnackBar,
        private fileUploadService:FileUploadService
        ) {}

    openFileSelector() {
        this.el.nativeElement.click();
    }

    getFileName():string {
        let files = this.el.nativeElement.files;
        if (files) {
            if (files.length == 1) {
                return files[0].name;
            }
            if (files.length > 0) {
                return "Multiple files selected"
            }
        }
        return "No file selected"
    }

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
            this.fileUploadService.upload(this.restUrl, formData,
                (data) => {
                    this.uploadResult.emit({
                        success: true,
                        data: data
                    });
                },
                (err) => {
                    this.uploadResult.emit({
                        success: false,
                        data: err
                    });
                }
            );
        }
    }

}