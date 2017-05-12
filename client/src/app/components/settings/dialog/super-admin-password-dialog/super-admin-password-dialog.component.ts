import { AuthService } from './../../../../services/auth/auth.service';
import { ApplicationSettingsService } from './../../../../services/app/application-settings.service';
import { Component } from '@angular/core';
import { MdSnackBar, MdDialogRef } from '@angular/material';

@Component({
	selector: 'app-super-admin-password-dialog',
	templateUrl: './super-admin-password-dialog.component.html'
})
export class SuperAdminPasswordDialogComponent {

	password:any = {
		chosen: "",
		verify: "",
		match: false
		// TODO Minimum password lengths.
	}

	constructor(
		public dialogRef:MdDialogRef<SuperAdminPasswordDialogComponent>,
        private applicationSettingsService:ApplicationSettingsService,
		private snackBar:MdSnackBar
	) {}

	confirm() {
		if (this.password.chosen !== this.password.verify) {
			this.snackBar.open("Passwords do not match.", "OK", {duration: 4000});
			return;
		}
		this.applicationSettingsService.updateSuperAdminPassword(this.password.chosen, 
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