import { Sort } from './../../../utils/sort.utils';
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

	}

    ngOnInit() {
		this.loadingMessage = "Loading Equipment Types...";
		this.loadInstrumentTypes();
	}
	
    loadInstrumentTypes() {

    }

}