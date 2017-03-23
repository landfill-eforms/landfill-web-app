import { UserGroup } from './../../../model/server/persistence/entity/user/user-group.class';
import { MdSnackBar } from '@angular/material';
import { EnumUtils } from './../../../utils/enum.utils';
import { UserPermission } from './../../../model/server/persistence/enums/user-permission.enum';
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
	selectedPermissions:UserPermission[];

	constructor(
		private userGroupService:UserGroupService,
		private activatedRoute:ActivatedRoute,
		private snackBar:MdSnackBar,
	) {}

	ngOnInit() {
		this.userGroupId = this.activatedRoute.params['_value']['id'];
		console.log(this.userGroupId);
		this.userGroupService.getById(this.userGroupId, 
			(data) => {
				this.processLoadedData(data);
				this.isDataLoaded = true;
			}
		);
	}

	save() {
		this.userGroup.userPermissions = this.selectedPermissions;
		this.userGroup.users = null;
		this.userGroupService.update(this.userGroup,
			(data) => {
				this.processLoadedData(data);
				this.snackBar.open("User group saved.", "OK", {duration: 3000});
			},
			(err) => {
				this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
			}
		);
	}

	private processLoadedData(data) {
		console.log(data);
		this.userGroup = data;
		this.selectedPermissions = EnumUtils.convertToEnums(UserPermission, this.userGroup.userPermissions);
	}

}