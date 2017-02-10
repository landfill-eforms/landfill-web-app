import { Router } from '@angular/router';
import {Component, OnInit, ElementRef} from '@angular/core';
import 'rxjs/Rx';

import {InstantaneousDataService} from '../../../services/instantaneous-data.service';

@Component({
    selector: 'app-instantaneous-upload-test',
    templateUrl: './instantaneous-upload-test.component.html',
    styleUrls: ['./instantaneous-upload-test.component.scss']
})
export class InstantaneousUploadTestComponent implements OnInit {

    _file:any;

    constructor(
        private _el:ElementRef, 
        private router:Router,
        private _instantaneousDataService:InstantaneousDataService
        ) {}

    ngOnInit() {

    }

    info() {
        let inputEl = this._el.nativeElement.firstElementChild;
        if (inputEl.files.length > 0) { 
            let file:FileList = inputEl.files[0];
            this._file = inputEl.files[0];
            console.log(this._file);
        }
    }

    upload() {

        this._instantaneousDataService.upload((data) => {
            console.log(data);
            this.router.navigate(['/app/unverified-data-set/' + data.id]);
            // this._instantaneousDataService.save((data2) => {
            //     console.log(data2);
            // }, data);
        }, this._file);
    }
}