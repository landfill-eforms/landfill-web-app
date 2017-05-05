import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { ImeRecheckDialogComponent } from './../../directives/dialogs/ime-recheck-dialog/ime-recheck-dialog.component';
import { User } from './../../../model/server/persistence/entity/user/user.class';
import { UserService } from './../../../services/user/user.service';
import { ImeRepairDialogComponent } from './../../directives/dialogs/ime-repair-dialog/ime-repair-dialog.component';
import { MdDialogRef, MdDialog, MdDialogConfig } from '@angular/material';
import { ImeData } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-data.class';
import { NavigationService } from './../../../services/app/navigation.service';
import { ImeNumber } from './../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { MdSnackBar } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { ImeNumberService } from './../../../services/instantaneous/ime-number.service';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-ime-number',
	templateUrl: './ime-number.component.html',
	styleUrls: ['./ime-number.component.scss']

})
export class ImeNumberComponent implements OnInit {

	DateTimeUtils = DateTimeUtils;
	StringUtils = StringUtils;

	isDataLoaded:boolean;
	imeNumber:string;
	imeNumberData:ImeNumber;

	isUsersLoaded:boolean;
	users:User[] = [];

	constructor(
		private imeNumberService:ImeNumberService,
		private dialog:MdDialog,
		private userService:UserService,
		private activatedRoute:ActivatedRoute,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
	}

	ngOnInit() {
		// TODO Display error message if IME number in the URL is invalid.
		this.imeNumber = this.activatedRoute.params['_value']['imeNumber'];
		console.log(this.imeNumber);
		this.imeNumberService.getByImeNumber(this.imeNumber,
			(data) => {
				console.log(data);
				this.imeNumberData = data;
				// Need to sort the data.
				this.isDataLoaded = true;
			}
		);
		this.userService.getAll((data) => {
			this.users = data;
			this.isUsersLoaded = true;
		});

	}

	openNewRepairDialog(data:ImeData) {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef:MdDialogRef<ImeRepairDialogComponent> = this.dialog.open(ImeRepairDialogComponent, dialogConfig);
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				console.log(result);
				data.imeRepairData.push(result);
			}
		});
	}

	openNewRecheckDialog() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef:MdDialogRef<ImeRecheckDialogComponent> = this.dialog.open(ImeRecheckDialogComponent, dialogConfig);
		dialogRef.componentInstance.users = this.users;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				result.methaneLevel *= 100;
				this.imeNumberData.imeData.push(result);
				console.log(this.imeNumberData);
			}
		});
	}

	save() {
		this.imeNumberService.update(this.imeNumberData, 
			(data) => {
				console.log(data);
				this.snackBar.open("IME entries have been updated.", "OK", {duration: 2000});
			}
		);
	}

}