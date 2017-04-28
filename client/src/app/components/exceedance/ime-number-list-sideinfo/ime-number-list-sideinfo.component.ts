import { ImeNumberService } from './../../../services/instantaneous/ime-number.service';
import { ImeNumber } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-components/abstract-sideinfo.component';
import { Component } from '@angular/core';

@Component({
	selector: 'app-ime-number-list-sideinfo',
	templateUrl: './ime-number-list-sideinfo.component.html'
})
export class ImeNumberListSideinfoComponent extends AbstractSideinfoComponent<ImeNumber> {

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
	}
	
}