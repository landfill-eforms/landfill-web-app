import { User } from './../../../model/server/persistence/entity/user/user.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-sideinfo.component';

import { Component, OnInit, AfterViewInit } from '@angular/core';

@Component({
	selector: 'app-user-list-sideinfo',
	templateUrl: './user-list-sideinfo.component.html',
	// styleUrls: ['./user-list-sideinfo.component.scss']
})
export class UserListSideinfoComponent extends AbstractSideinfoComponent {

	user:User;
	test:string;

	constructor(
		private navigationService:NavigationService) {
			super("User Info");
	}

	getData():any {
		return this.user;
	}

	setData(data:any) {
		this.user = data;
	}
	
}