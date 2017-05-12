import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { InstrumentTypeService } from './../../../services/instrument/instrument-type.service';
import { InstrumentType } from './../../../model/server/persistence/entity/instrument/instrument-type.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-components/abstract-sideinfo.component';
import { Component } from '@angular/core';

@Component({
	selector: 'app-instrument-type-list-sideinfo',
	templateUrl: './instrument-type-list-sideinfo.component.html'
})
export class InstrumentTypeListSideinfoComponent extends AbstractSideinfoComponent<InstrumentType> {

	instrumentType:InstrumentType;
	tests:string[] = []

	constructor(private instrumentTypeService:InstrumentTypeService) {
		super("Equipment Type");
	}

	getData():InstrumentType {
		return this.instrumentType;
	}

	setData(data:InstrumentType) {
		this.instrumentType = data;
		this.tests = this.instrumentTypeService.generateApplicableTestsList(this.instrumentType);
	}
	
}