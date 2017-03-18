import { Router } from '@angular/router';
import {Component, OnInit, ElementRef} from '@angular/core';
import 'rxjs/Rx';

import {InstantaneousDataService} from '../../../services/instantaneous/instantaneous-data.service';

@Component({
    selector: 'app-instantaneous-upload-test',
    templateUrl: './instantaneous-upload-test.component.html',
    styleUrls: ['./instantaneous-upload-test.component.scss']
})
export class InstantaneousUploadTestComponent implements OnInit {

    _file:any;

    constructor(
        private _el:ElementRef, 
        private router:Router
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

}