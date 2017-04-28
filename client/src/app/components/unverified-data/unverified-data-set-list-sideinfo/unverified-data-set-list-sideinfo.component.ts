import { DateTimeUtils } from './../../../utils/date-time.utils';
import { UnverifiedDataSet } from './../../../model/server/persistence/entity/unverified/unverified-data-set.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-components/abstract-sideinfo.component';
import { Component } from '@angular/core';

@Component({
	selector: 'app-unverified-data-set-list-sideinfo',
	templateUrl: './unverified-data-set-list-sideinfo.component.html'
})
export class UnverifiedDataSetListSideinfoComponent extends AbstractSideinfoComponent<UnverifiedDataSet> {

	DateTimeUtils = DateTimeUtils;

	unverifiedDataSet:UnverifiedDataSet;

	constructor(
		private navigationService:NavigationService) {
			super("Unverified Data Set");
	}

	getData():UnverifiedDataSet {
		return this.unverifiedDataSet;
	}

	setData(data:UnverifiedDataSet) {
		this.unverifiedDataSet = data;
	}
	
}