import { UserGroup } from './../../../model/server/persistence/entity/user/user-group.class';
import { UserGroupService } from './../../../services/user/user-group.service';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef } from "@angular/material";
import { MdSnackBar } from '@angular/material';

@Component({
	selector: 'app-new-user-group-dialog',
	templateUrl: './new-user-group-dialog.component.html',
	styleUrls: ['./new-user-group-dialog.component.scss']
})
export class NewUserGroupDialogComponent implements OnInit {

	userGroup:UserGroup;

	constructor(
		private dialogRef:MdDialogRef<NewUserGroupDialogComponent>,
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
				this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 2000});
			}
		);
	}

	cancel() {
		this.dialogRef.close();
	}
	
}