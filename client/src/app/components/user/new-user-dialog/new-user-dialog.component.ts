import { Person } from './../../../model/server/persistence/entity/user/person.class';
import { User } from './../../../model/server/persistence/entity/user/user.class';
import { UserService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef } from "@angular/material";

@Component({
	selector: 'app-new-user-dialog',
	templateUrl: './new-user-dialog.component.html',
	styleUrls: ['./new-user-dialog.component.scss']
})
export class NewUserDialogComponent implements OnInit {

	user:User;
	password:any = {
		chosen: "",
		verify: "",
		match: false
		// TODO Minimum password lengths.
	}

	constructor(
		public dialogRef:MdDialogRef<NewUserDialogComponent>,
		private userService:UserService
	) {}

	ngOnInit() {
		this.user = new User();
		this.user.person = new Person();
	}

	verifyPassword() {
		if (!this.password.verify) {
			return;
		}
	}

	confirm() {
		// TODO Perform data verification before saving.
		this.userService.create((data) => {
			console.log(data);
			this.dialogRef.close(data);
		}, this.user);
	}

	cancel() {
		this.dialogRef.close();
	}
	
}