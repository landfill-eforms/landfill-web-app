import {Component, OnInit} from '@angular/core';
//import {Observable} from 'rxjs/Rx';
import 'rxjs/Rx';

import {DateTimeUtils} from '../../../utils/date-time-utils';
import {InstantaneousDataService} from '../../../services/instantaneous-data.service';
import {SitesService} from '../../../services/sites.service';

@Component({
    selector: 'instantaneous-report-test',
    templateUrl: './instantaneous-report-test.component.html',
    styleUrls: ['./instantaneous-report-test.component.scss']
})
export class InstantaneousReportTestComponent implements OnInit {

    data:any[] = [];
    sites:any = {
        list: [],
        selected: {}
    }

    // Util functions
    getDate = DateTimeUtils.getDate;
    getTime = DateTimeUtils.getTime;

    constructor (
        private _instantaneousDataService:InstantaneousDataService,
        private _sitesService:SitesService
        ) {}

    ngOnInit() {

        // Load list of sites
        this._sitesService.getVisible((data) => {
            console.log("Sites Loaded", data);
            this.sites.list = data;
            this.sites.selected = data[0];
        });
    }

    getStatus(reading:number):string {
        if (reading > 500) {
            return "hotspot";
        }
        else if (reading > 300) {
            return "warmspot"
        }
        return "";
    }

    getAll() {
        console.log(this.sites);
        this._instantaneousDataService.getAll((data) => {
            console.log(data);
            this.data = data;
        });
    }

    getData() {
        this._instantaneousDataService.getBySiteName((data) => {
            console.log(data);
            this.data = data;
        }, this.sites.selected.siteName);
    }
    
}