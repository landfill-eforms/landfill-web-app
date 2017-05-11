import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { ImeNumber } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-components/abstract-sideinfo.component';
import { Component } from '@angular/core';

@Component({
	selector: 'app-ime-number-list-sideinfo',
	templateUrl: './ime-number-list-sideinfo.component.html'
})
export class ImeNumberListSideinfoComponent extends AbstractSideinfoComponent<ImeNumber> {

	DateTimeUtils = DateTimeUtils;
	StringUtils = StringUtils;

	imeNumber:ImeNumber;

	constructor(
		private navigationService:NavigationService) {
			super("IME Number");
	}

	getData():ImeNumber {
		return this.imeNumber;
	}

	setData(data:ImeNumber) {
		this.imeNumber = data;
		this.imeNumber.imeData.sort((a, b) => {
			return a.dateTime - b.dateTime;
		});
	}
	
}