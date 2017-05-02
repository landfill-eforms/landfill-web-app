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

	userGroup:UserGroup;

	constructor(
		private dialogRef:MdDialogRef<UserGroupDialogComponent>,
		private snackBar:MdSnackBar,
		private userGroupService:UserGroupService
	) {}

	ngOnInit() {
		this.userGroup = new UserGroup();
	}

	confirm() {
		// TODO Perform data verification before saving.
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

	cancel() {
		this.dialogRef.close();
	}
	
}