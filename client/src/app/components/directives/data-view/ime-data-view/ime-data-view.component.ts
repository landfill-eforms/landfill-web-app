import { ImeRepairData } from './../../../../model/server/persistence/entity/instantaneous/ime-repair-data.class';
import { ImeRepairDialogComponent } from './../../dialogs/ime-repair-dialog/ime-repair-dialog.component';
import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { UserService } from './../../../../services/user/user.service';
import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { StringUtils } from './../../../../utils/string.utils';
import { ImeRecheckDialogComponent } from './../../dialogs/ime-recheck-dialog/ime-recheck-dialog.component';
import { SortUtils } from './../../../../utils/sort.utils';
import { MdDialog } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { MdDialogConfig } from '@angular/material';
import { ImeData } from './../../../../model/server/persistence/entity/instantaneous/ime-data.class';
import { Component, OnChanges, Input, OnInit } from '@angular/core';

@Component({
	selector: 'app-ime-data-view',
	templateUrl: './ime-data-view.component.html',
	styleUrls: ['./ime-data-view.component.scss']
})
export class ImeDataViewComponent implements OnChanges, OnInit {

	@Input() data:ImeData[];

	@Input() edit:boolean;

	stringUtils = StringUtils;
	dateTimeUtils = DateTimeUtils;

	isUsersLoaded:boolean;
	users:User[] = [];

	constructor(
		private dialog:MdDialog,
		private userService:UserService
	) {}

	ngOnInit() {

		if (this.edit) {
			this.userService.getAll((data) => {
				this.users = data;
				this.isUsersLoaded = true;
			});
		}
		
	}

	ngOnChanges() {
		this.sortData()
	}

	sortData() {
		if (this.data) {
			SortUtils.sort(this.data, ["dataTime"], false);
		}
	}

	openNewRecheckDialog() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		//dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<ImeRecheckDialogComponent> = this.dialog.open(ImeRecheckDialogComponent, dialogConfig);
		dialogRef.componentInstance.users = this.users;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				result.methaneLevel *= 100;
				this.data.push(result);
				this.sortData();
				console.log(this.data);
			}
		});
	}

	openNewRepairDialog(data:ImeData) {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		//dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<ImeRepairDialogComponent> = this.dialog.open(ImeRepairDialogComponent, dialogConfig);
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				console.log(result);
				data.imeRepairData = result;
			}
		});
	}

}