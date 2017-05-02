import { DateTimeUtils } from './../../../utils/date-time.utils';
import { UserGroup } from './../../../model/server/persistence/entity/user/user-group.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-components/abstract-sideinfo.component';
import { Component } from '@angular/core';

@Component({
	selector: 'app-user-group-list-sideinfo',
	templateUrl: './user-group-list-sideinfo.component.html'
})
export class UserGroupListSideinfoComponent extends AbstractSideinfoComponent<UserGroup> {

	DateTimeUtils = DateTimeUtils;

	userGroup:UserGroup;

	constructor(
		private navigationService:NavigationService) {
			super("User Group");
	}

	getData():UserGroup {
		return this.userGroup;
	}

	setData(data:UserGroup) {
		this.userGroup = data;
	}
	
}