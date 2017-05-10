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

    applicationSettings:any = {};
    isDataLoaded:boolean;
    loadingMessage:string;

    constructor(
        private applicationSettingsService:ApplicationSettingsService,
        private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private titleService:TitleService,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
    }

    ngOnInit() {
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

    resetToDefault() {
        for (let key of Object.keys(this.applicationSettings)) {
            let setting = this.applicationSettings[key];
            setting.value = setting.defaultValue;
        }
        this.convertFromStrings();
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

    private convertToStrings() {
        for (let key of Object.keys(this.applicationSettings)) {
            let setting = this.applicationSettings[key];
            setting.value = String(setting.value);
        }
    }

}