import { IseNumber } from './../../../model/server/persistence/entity/surfaceemission/integrated/ise-number.class';
import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-components/abstract-sideinfo.component';
import { Component } from '@angular/core';

@Component({
	selector: 'app-ise-number-list-sideinfo',
	templateUrl: './ise-number-list-sideinfo.component.html'
})
export class IseNumberListSideinfoComponent extends AbstractSideinfoComponent<IseNumber> {

	iseNumber:IseNumber;

	constructor() {
		super("ISE Number");
	}

	getData():IseNumber {
		return this.iseNumber;
	}

	setData(data:IseNumber) {
		this.iseNumber = data;
		this.iseNumber.iseData.sort((a, b) => {
			return a.dateTime - b.dateTime;
		});
	}
	
}