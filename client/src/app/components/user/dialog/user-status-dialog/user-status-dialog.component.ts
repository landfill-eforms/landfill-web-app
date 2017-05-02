import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { UserService } from './../../../../services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { MdSnackBar, MdDialogRef } from '@angular/material';

@Component({
	selector: 'app-user-status-dialog',
	templateUrl: './user-status-dialog.component.html'
})
export class UserStatusDialogComponent implements OnInit {

	user:User;
	status:boolean = false;

	constructor(
		private userService:UserService,
		public dialogRef:MdDialogRef<UserStatusDialogComponent>,
		private snackBar:MdSnackBar
	) {}

	ngOnInit() {
		if (this.user) {
			this.status = this.user.enabled;
		}
	}

	confirm() {
		if (!this.user) {
			this.cancel();
			return;
		}
		this.user.enabled = this.status;
		this.userService.updateStatus(this.user, 
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