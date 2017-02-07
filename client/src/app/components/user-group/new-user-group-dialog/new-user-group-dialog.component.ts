import { UserGroupService } from './../../../services/user-group.service';
import { UserGroup } from './../../../model/server/persistence/entity/user-group.class';
import { Person } from './../../../model/server/persistence/entity/person.class';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef } from "@angular/material";

@Component({
	selector: 'app-new-user-group-dialog',
	templateUrl: './new-user-group-dialog.component.html',
	styleUrls: ['./new-user-group-dialog.component.scss']
})
export class NewUserGroupDialogComponent implements OnInit {

	userGroup:UserGroup;

	constructor(
		public dialogRef:MdDialogRef<NewUserGroupDialogComponent>,
		private userGroupService:UserGroupService
	) {}

	ngOnInit() {
		this.userGroup = new UserGroup();
	}

	confirm() {
		// TODO Perform data verification before saving.
		this.userGroupService.create((data) => {
			console.log(data);
			this.dialogRef.close(data);
		}, this.userGroup);
	}

	cancel() {
		this.dialogRef.close();
	}
	
}