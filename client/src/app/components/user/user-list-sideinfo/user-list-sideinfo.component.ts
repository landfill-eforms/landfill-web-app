import { UserStatusDialogComponent } from './../dialog/user-status-dialog/user-status-dialog.component';
import { UserPasswordDialogComponent } from './../dialog/user-password-dialog/user-password-dialog.component';
import { MdDialogConfig, MdDialogRef, MdDialog, MdSnackBar } from '@angular/material';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { UserActivity } from './../../../model/server/persistence/entity/user/user-activity.class';
import { UserActivityService } from './../../../services/user/user-activity.service';
import { User } from './../../../model/server/persistence/entity/user/user.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-components/abstract-sideinfo.component';
import { Component } from '@angular/core';

@Component({
	selector: 'app-user-list-sideinfo',
	templateUrl: './user-list-sideinfo.component.html'
})
export class UserListSideinfoComponent extends AbstractSideinfoComponent<User> {

	DateTimeUtils = DateTimeUtils;

	user:User;
	userActivity:UserActivity[];
	isUserActivityLoaded:boolean;

	canChangeUsername:boolean; // TODO Implement ability to change usernames.
	canChangePassword:boolean;
	canChangeStatus:boolean;

	constructor(
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private userActivityService:UserActivityService,
		private navigationService:NavigationService) {
			super("User");
	}

	getData():User {
		return this.user;
	}

	setData(data:any) {

		// Get user and flags from data object.
		if (data) {

			// Only update the user data if a different user was selected.
			if (!this.user || this.user.id != data.user.id) {
				this.user = data.user;
				this.isUserActivityLoaded = false;
				this.userActivityService.getByUserId(this.user.id, (data) => {
					console.log(data);
					this.userActivity = data;
					this.isUserActivityLoaded = true;
				});
			}

			// Update flags regardless
			this.canChangePassword = data.canChangePassword;
			this.canChangeUsername = data.canChangeUsername;
			this.canChangeStatus = data.canChangeStatus;

		}

		// If null was passed in, then that means we want to deselect the user.
		else {
			this.user = null;
			this.userActivity = null;
			this.isUserActivityLoaded = false;
		}

	}
	
	editPassword() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef:MdDialogRef<UserPasswordDialogComponent> = this.dialog.open(UserPasswordDialogComponent, dialogConfig);
		dialogRef.componentInstance.user = this.user;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("Password updated.", "OK", {duration: 2000});
			}
		});
	}

	editStatus() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '480px';
		let dialogRef:MdDialogRef<UserStatusDialogComponent> = this.dialog.open(UserStatusDialogComponent, dialogConfig);
		dialogRef.componentInstance.user = this.user;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("User account status updated.", "OK", {duration: 2000});
			}
		});
	}

}