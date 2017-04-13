import { User } from './../../../model/server/persistence/entity/user/user.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-sideinfo.component';

import { Component, OnInit, AfterViewInit, Input, OnChanges } from '@angular/core';

@Component({
	selector: 'app-user-list-sideinfo',
	templateUrl: './user-list-sideinfo.component.html'
})
export class UserListSideinfoComponent extends AbstractSideinfoComponent {

	user:User;

	constructor(
		private navigationService:NavigationService) {
			super("User Details");
	}

	getData():any {
		return this.user;
	}

	setData(data:any) {
		this.user = data;
	}
	
}