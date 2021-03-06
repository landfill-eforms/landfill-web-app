import { SuperAdminPasswordDialogComponent } from './../dialog/super-admin-password-dialog/super-admin-password-dialog.component';
import { MdDialogRef } from '@angular/material';
import { MdDialogConfig } from '@angular/material';
import { AuthService } from './../../../services/auth/auth.service';
import { NavigationService } from './../../../services/app/navigation.service';
import { TitleService } from './../../../services/app/title.service';
import { MdSnackBar, MdDialog } from '@angular/material';
import { ApplicationSettingsService } from './../../../services/app/application-settings.service';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-application-settings',
	templateUrl: './application-settings.component.html',
	styleUrls: ['./application-settings.component.scss']
})
export class ApplicationSettingsComponent implements OnInit {

    isSuperAdmin:boolean;

    applicationSettings:any = {};
    isDataLoaded:boolean;
    loadingMessage:string;

    unsavedChanges:boolean;

    constructor(
        private authService:AuthService,
        private applicationSettingsService:ApplicationSettingsService,
        private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private titleService:TitleService,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
    }

    ngOnInit() {
        this.isSuperAdmin = this.authService.isSuperAdmin();
        this.loadingMessage = "Loading Settings..."
        this.loadSettings();
    }

    private loadSettings() {
        this.applicationSettingsService.getMap(
            (data) => {
                console.log(data);
                this.applicationSettings = data;
                this.convertFromStrings();
                this.isDataLoaded = true;
            },
            (err) => {
                this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
            }
        );
    }

    openSuperAdminPasswordDialog() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef:MdDialogRef<SuperAdminPasswordDialogComponent> = this.dialog.open(SuperAdminPasswordDialogComponent, dialogConfig);
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.authService.logout();
                this.snackBar.open("Super admin password updated! Please re-log.", "OK", {duration: 3000});
			}
		});
    }

    resetToDefault() {
        for (let key of Object.keys(this.applicationSettings)) {
            let setting = this.applicationSettings[key];
            setting.value = setting.defaultValue;
        }
        this.convertFromStrings();
    }

    save() {
        this.loadingMessage = "Saving Settings..."
        this.isDataLoaded = false;
        console.log(this.applicationSettings);
        this.applicationSettingsService.update(this.applicationSettings,
            (data) => {
                console.log(data);
                this.applicationSettings = data;
                this.convertFromStrings();
                this.isDataLoaded = true;
                this.snackBar.open("Application settings saved!", "OK", {duration: 3000});
            },
            (err) => {
                this.convertFromStrings();
                this.isDataLoaded = true;
                this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
            }
        );
    }

    changed() {
        this.unsavedChanges = true;
    }

    private convertFromStrings() {
        for (let key of Object.keys(this.applicationSettings)) {
            let setting = this.applicationSettings[key];
            if (setting.type === "INTEGER" || setting.type === "LONG") {
                setting.value = ~~Number(setting.value);
            }
            else if (setting.type === "DOUBLE") {
                setting.value = Number(setting.value);
            }
            else if (setting.type === "BOOLEAN") {
                setting.value = setting.value === "true" ? true : false;
            }
        }
    }

}