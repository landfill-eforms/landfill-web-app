import { NewInstrumentTypeDialogComponent } from './../dialog/new-instrument-type-dialog/new-instrument-type-dialog.component';
import { MdDialogRef } from '@angular/material';
import { MdDialog, MdSnackBar, MdDialogConfig } from '@angular/material';
import { InstrumentTypeService } from './../../../services/instrument/instrument-type.service';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { InstrumentType } from './../../../model/server/persistence/entity/instrument/instrument-type.class';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-instrument-type-list',
    templateUrl: './instrument-type-list.component.html',
    styleUrls: ['./instrument-type-list.component.scss']
})
export class InstrumentTypeListComponent implements OnInit {

    isDataLoaded:boolean;
    loadingMessage:string;
    instrumentTypeList:InstrumentType[];
    
    sort:Sort = {
		current: "id",
		reversed: false
	}

	sortProperties:any = {
        type: [
            "type"
        ],
        manufacturer: [
            "manufacturer",
            "type"
        ],
        instantaneous: [
            "instantaneous",
            "type"
        ],
        probe: [
            "probe",
            "type"
        ],
        methanePercent: [
            "methanePercent",
            "type"
        ],
        methanePpm: [
            "methanePpm",
            "type"
        ],
        hydrogenSulfidePpm: [
            "hydrogenSulfidePpm",
            "type"
        ],
        oxygenPercent: [
            "oxygenPercent",
            "type"
        ],
        carbonDioxidePercent: [
            "carbonDioxidePercent",
            "type"
        ],
        nitrogenPercent: [
            "nitrogenPercent",
            "type"
        ],
        pressure: [
            "pressure",
            "type"
        ]
	}

    constructor(
        private instrumentTypeService:InstrumentTypeService,
        private dialog:MdDialog,
		private snackBar:MdSnackBar
    ) {}

    ngOnInit() {
		this.loadingMessage = "Loading...";
		this.loadInstrumentTypes();
	}
	
    loadInstrumentTypes() {
        this.instrumentTypeService.getAll((data) => {
            console.log(data);
            this.instrumentTypeList = data;
            this.isDataLoaded = true;
        });
    }

    openNewInstrumentTypeDialog() {
        let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
			//dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<NewInstrumentTypeDialogComponent> = this.dialog.open(NewInstrumentTypeDialogComponent, dialogConfig);
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("New user group has been created.", "OK", {duration: 2000});
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading..."
				this.loadInstrumentTypes();
			}
		});
    }

    sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.instrumentTypeList, this.sortProperties[sortBy]);
	}

}