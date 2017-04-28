import { InstrumentService } from './../../../services/instrument/instrument.service';
import { Instrument } from './../../../model/server/persistence/entity/instrument/instrument.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-components/abstract-sideinfo.component';
import { Component } from '@angular/core';

@Component({
	selector: 'app-instrument-list-sideinfo',
	templateUrl: './instrument-list-sideinfo.component.html'
})
export class InstrumentListSideinfoComponent extends AbstractSideinfoComponent {

	instrument:Instrument;
	tests:string[] = []

	constructor(
		private navigationService:NavigationService,
		private instrumentService:InstrumentService) {
			super("Equipment");
	}

	getData():any {
		return this.instrument;
	}

	setData(data:any) {
		this.instrument = data;
	}
	
}