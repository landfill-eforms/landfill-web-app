import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { UserService } from './../../../../services/user/user.service';
import { Component } from '@angular/core';
import { MdSnackBar, MdDialogRef } from '@angular/material';

@Component({
	selector: 'app-user-password-dialog',
	templateUrl: './user-password-dialog.component.html'
})
export class UserPasswordDialogComponent {

	user:User;
	password:any = {
		chosen: "",
		verify: "",
		match: false
		// TODO Minimum password lengths.
	}

	constructor(
		private userService:UserService,
		public dialogRef:MdDialogRef<UserPasswordDialogComponent>,
		private snackBar:MdSnackBar
	) {}

	confirm() {
		if (!this.user) {
			this.cancel();
			return;
		}
		if (this.password.chosen !== this.password.verify) {
			this.snackBar.open("Passwords do not match.", "OK", {duration: 4000});
			return;
		}
		this.user.password = this.password.chosen;
		this.userService.changePassword(this.user, 
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