import { InstrumentType } from './../../../model/server/persistence/entity/instrument/instrument-type.class';
import { MdDialog, MdSnackBar } from '@angular/material';
import { InstrumentService } from './../../../services/instrument/instrument.service';
import { Instrument } from './../../../model/server/persistence/entity/instrument/instrument.class';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-instrument-list',
    templateUrl: './instrument-list.component.html',
    styleUrls: ['./instrument-list.component.scss']
})
export class InstrumentTypeListComponent implements OnInit {

    isDataLoaded:boolean;
    loadingMessage:string;
    instrumentList:Instrument[];
    
    sort:Sort = {
		current: "id",
		reversed: false
	}

	sortProperties:any = {
        serialNumber: [
            "serialNumber"
        ],
        instrumentType: [
            "instrumentType.type",
            "serialNumber"
        ],
        manufacturer: [
            "instrumentType.manufacturer",
            "instrumentType.type",
            "serialNumber"
        ],
        status: [
            "instrumentStatus",
            "serialNumber"
        ],
        serviceDueDate: [
            "serviceDueDate",
            "serialNumber"
        ],
        lastServiceDate: [
            "lastServiceDate",
            "serialNumber"
        ],
        purchaseDate: [
            "purchaseDate",
            "serialNumber"
        ],
        site: [
            "site",
            "serialNumber"
        ],
        inventoryNumber: [
            "inventoryNumber",
            "serialNumber"
        ],
	}

    constructor(
        private instrumentService:InstrumentService,
        private dialog:MdDialog,
		private snackBar:MdSnackBar
    ) {}

    ngOnInit() {
		this.loadingMessage = "Loading Equipment s...";
		this.loadInstruments();
	}
	
    loadInstruments() {
        this.instrumentService.getAll((data) => {
            console.log(data);
            this.instrumentList = data;
            this.isDataLoaded = true;
        });
    }

    sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.instrumentList, this.sortProperties[sortBy]);
	}
	
}