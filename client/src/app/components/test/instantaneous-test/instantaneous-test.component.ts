import {Component, OnInit} from '@angular/core';
//import {Http, Response, Headers, RequestOptions} from '@angular/http';
//import {Observable} from 'rxjs/Rx';
import 'rxjs/Rx';

import {InstantaneousDataService} from '../../../services/instantaneous-data.service';

@Component({
    selector: 'app-instantaneous-test',
    templateUrl: './instantaneous-test.component.html',
    styleUrls: ['./instantaneous-test.component.scss']
})
export class InstantaneousTestComponent implements OnInit {

    values:any[] = [];

    constructor (
        private _instantaneousDataService:InstantaneousDataService
        ) {}

    ngOnInit() {
        this.insertRow();
    }

    insertRow() {
        this.values.push({
            baro: 30.00,
            meth: 0.00,
            grid: 19,
            ime: ""
        });
    }

    saveTest() {
        var newData = [];
        for (let i = 0; i < this.values.length; i++) {
            let value = this.values[i];
            let newRow = {
                barometricPressure: value.baro * 100,
                methaneLevel: value.meth * 100,
                monitoringPoint: {
                    id: value.grid + 2046, // TODO Fix primary key index in MonitoringPoints table.
                },
                user: {
                    id: 4
                },
                instrument: {
                    id: 1
                },
                imeNumber: value.ime
            };
            newData.push(newRow);
        }
        this._instantaneousDataService.save((data) => {
            console.log(data);
        }, newData);
    }

}