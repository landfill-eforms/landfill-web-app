import { UserGroup } from './../../../../model/server/persistence/entity/user/user-group.class';
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

	isNew:boolean;
	user:User;
	userGroups:UserGroup[] = [];
	userGroupsWrapped:{userGroup:UserGroup, selected:boolean}[] = [];
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
		if (!this.user) {
			this.user = new User();
			this.user.userGroups = [];
		}
		else {
			// Clone
			let clone:User = new User();
			for (let key of Object.keys(this.user)) {
				clone[key] = this.user[key];
			}
			clone.userGroups = [];
			for (let userGroup of this.user.userGroups) {
				clone.userGroups.push(userGroup);
			}
			this.user = clone;
		}
		for (let userGroup of this.userGroups) {
			this.userGroupsWrapped.push({
				userGroup: userGroup,
				selected: this.user.userGroups.map(g => g.id).indexOf(userGroup.id) > -1
			});
		}
		console.log(this.userGroupsWrapped)
	}

	confirm() {
		this.user.userGroups = [];
		for (let userGroupWrapped of this.userGroupsWrapped) {
			if (userGroupWrapped.selected) {
				this.user.userGroups.push(userGroupWrapped.userGroup);
			}
		}
		// TODO Perform data verification before saving.
		if (this.isNew) {
			if (this.password.chosen !== this.password.verify) {
				this.snackBar.open("Passwords do not match.", "OK", {duration: 4000});
				return;
			}
			this.user.password = this.password.chosen;
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
		else {
			this.userService.updateProfile(this.user, 
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
	
}