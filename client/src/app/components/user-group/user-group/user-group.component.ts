import { MdSnackBar } from '@angular/material';
import { EnumUtils } from './../../../utils/enum.utils';
import { UserRole } from './../../../model/server/model/user-role.enum';
import { UserGroupService } from './../../../services/user-group.service';
import { UserGroup } from './../../../model/server/persistence/entity/user-group.class';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
	selector: 'app-user-group',
	templateUrl: './user-group.component.html',
	styleUrls: ['./user-group.component.scss']
})
export class UserGroupComponent implements OnInit {

	isDataLoaded:boolean;
	groupId:string;
	userGroup:UserGroup = new UserGroup();
	selectedRoles:UserRole[];

	constructor(
		private userGroupService:UserGroupService,
		private activatedRoute:ActivatedRoute,
		private snackBar:MdSnackBar,
	) {}

	ngOnInit() {
		this.groupId = this.activatedRoute.params['_value']['id'];
		console.log(this.groupId);
		this.userGroupService.getById((data) => {
			console.log(data);
			this.userGroup = data;
			this.isDataLoaded = true;
			this.selectedRoles = EnumUtils.convertToEnums(UserRole, this.userGroup.userRoles);
		}, this.groupId);
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