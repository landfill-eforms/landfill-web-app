import { InstrumentTypeService } from './../../../services/instrument/instrument-type.service';
import { NewInstrumentDialogComponent } from './../dialog/new-instrument-dialog/new-instrument-dialog.component';
import { InstrumentType } from './../../../model/server/persistence/entity/instrument/instrument-type.class';
import { MdDialog, MdSnackBar, MdDialogConfig, MdDialogRef } from '@angular/material';
import { InstrumentService } from './../../../services/instrument/instrument.service';
import { Instrument } from './../../../model/server/persistence/entity/instrument/instrument.class';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-instrument-list',
    templateUrl: './instrument-list.component.html',
    styleUrls: ['./instrument-list.component.scss']
})
export class InstrumentListComponent implements OnInit {

    isDataLoaded:boolean;
    loadingMessage:string;
    instruments:Instrument[];
    instrumentTypes:InstrumentType[];
    
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
        private instrumentTypeService:InstrumentTypeService,
        private dialog:MdDialog,
		private snackBar:MdSnackBar
    ) {}

    ngOnInit() {
		this.loadingMessage = "Loading Equipment List...";
		this.loadInstruments();
        this.loadInstrumentTypes();
        console.log("HELLO?")
	}
	
    loadInstruments() {
        this.instrumentService.getAll((data) => {
            console.log(data);
            this.instruments = data;
            //this.isDataLoaded = true;
        });
    }

    loadInstrumentTypes() {
        this.instrumentTypeService.getAll((data) => {
            console.log(data);
            this.instrumentTypes = data;
            this.isDataLoaded = true;
        });
    }

    openNewInstrumentDialog() {
        let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
        //dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<NewInstrumentDialogComponent> = this.dialog.open(NewInstrumentDialogComponent, dialogConfig);
        dialogRef.componentInstance.instrumentTypes = this.instrumentTypes;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("New user group has been created.", "OK", {duration: 2000});
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading..."
				this.loadInstruments();
			}
		});
    }

    sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.instruments, this.sortProperties[sortBy]);
	}
	
}