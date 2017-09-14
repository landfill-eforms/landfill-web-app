import { UserPermission } from './../../../../model/server/persistence/enums/user/user-permission.enum';
import { UserGroupService } from './../../../../services/user/user-group.service';
import { UserGroup } from './../../../../model/server/persistence/entity/user/user-group.class';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef } from "@angular/material";
import { MdSnackBar } from '@angular/material';

@Component({
	selector: 'app-user-group-dialog',
	templateUrl: './user-group-dialog.component.html'
})
export class UserGroupDialogComponent implements OnInit {

	isNew:boolean;
	containsAdmin:boolean;
	adminGroupExists:boolean;
	userGroup:UserGroup;
	userPermissionsWrapped:{permission:UserPermission, selected:boolean}[] = [];

	constructor(
		private dialogRef:MdDialogRef<UserGroupDialogComponent>,
		private snackBar:MdSnackBar,
		private userGroupService:UserGroupService
	) {}

	ngOnInit() {
		if (!this.userGroup) {
			this.userGroup = new UserGroup();
			this.userGroup.userPermissions = [];
			this.userGroup.inspectorGroupFlag = false;
		}
		else {
			// Clone
			let clone:UserGroup = new UserGroup();
			for (let key of Object.keys(this.userGroup)) {
				clone[key] = this.userGroup[key];
			}
			clone.userPermissions = [];
			for (let permission of this.userGroup.userPermissions) {
				clone.userPermissions.push(permission);
			}
			this.userGroup = clone;
		}
		this.containsAdmin = this.hasAdmin();
		for (let permission of UserPermission.values()) {
			if (!this.containsAdmin && this.adminGroupExists && permission == UserPermission.ADMIN) {
				continue;
			}
			this.userPermissionsWrapped.push({
				permission: permission,
				selected: this.userGroup.userPermissions.map(g => g.ordinal).indexOf(permission.ordinal) > -1
			});
		}
	}

	confirm() {
		this.userGroup.userPermissions = []
		for (let userPermissionWrapped of this.userPermissionsWrapped) {
			if (userPermissionWrapped.selected) {
				this.userGroup.userPermissions.push(userPermissionWrapped.permission);
			}
		}
		// TODO Perform data verification before saving.
		if (this.isNew) {
			this.userGroupService.create(this.userGroup,
				(data) => {
					console.log(data);
					this.dialogRef.close(data);
				},
				(err) => {
					this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
				}
			);
		}
		else {
			this.userGroupService.update(this.userGroup,
				(data) => {
					console.log(data);
					this.dialogRef.close(data);
				},
				(err) => {
					this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
				}
			);
		}
	}

	cancel() {
		this.dialogRef.close();
	}

	private hasAdmin():boolean {
		for (let permission of this.userGroup.userPermissions) {
			if (permission == UserPermission.ADMIN) {
				return true;
			}
		}
		return false;
	}
	
}