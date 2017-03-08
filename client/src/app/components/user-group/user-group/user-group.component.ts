import { UserGroup } from './../../../model/server/persistence/entity/user/user-group.class';
import { MdSnackBar } from '@angular/material';
import { EnumUtils } from './../../../utils/enum.utils';
import { UserRole } from './../../../model/server/persistence/enums/user-role.enum';
import { UserGroupService } from './../../../services/user/user-group.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
	selector: 'app-user-group',
	templateUrl: './user-group.component.html',
	styleUrls: ['./user-group.component.scss']
})
export class UserGroupComponent implements OnInit {

	isDataLoaded:boolean;
	userGroupId:string;
	userGroup:UserGroup = new UserGroup();
	selectedRoles:UserRole[];

	constructor(
		private userGroupService:UserGroupService,
		private activatedRoute:ActivatedRoute,
		private snackBar:MdSnackBar,
	) {}

	ngOnInit() {
		this.userGroupId = this.activatedRoute.params['_value']['id'];
		console.log(this.userGroupId);
		this.userGroupService.getById((data) => {
			console.log(data);
			this.userGroup = data;
			this.isDataLoaded = true;
			this.selectedRoles = EnumUtils.convertToEnums(UserRole, this.userGroup.userRoles);
		}, this.userGroupId);
	}

	save() {
		this.userGroup.userRoles = EnumUtils.convertToStrings(this.selectedRoles);
		this.userGroupService.update((data) => {
			console.log(data);
			this.snackBar.open("User group saved.", "OK", {duration: 2000});
		}, this.userGroup);
	}

	consoleLog() {
		console.log(this.selectedRoles);
	}

}