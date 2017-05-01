import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { UserService } from './../../../../services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef } from "@angular/material";
import { MdSnackBar } from '@angular/material';

@Component({
	selector: 'app-user-dialog',
	templateUrl: './user-dialog.component.html'
})
export class UserDialogComponent implements OnInit {

	user:User;
	password:any = {
		chosen: "",
		verify: "",
		match: false
		// TODO Minimum password lengths.
	}

	constructor(
		private userService:UserService,
		public dialogRef:MdDialogRef<UserDialogComponent>,
		private snackBar:MdSnackBar
	) {}

	ngOnInit() {
		this.user = new User();
	}

	verifyPassword() {
		if (!this.password.verify) {
			return;
		}
	}

	confirm() {
		// TODO Perform data verification before saving.
		this.userService.create(this.user, 
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