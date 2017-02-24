import { IMERepairData } from './../../../../model/server/persistence/entity/instantaneous/ime-repair-data.class';
import { IMERepairDialogComponent } from './../../dialog/ime-repair-dialog/ime-repair-dialog.component';
import { User } from './../../../../model/server/persistence/entity/user/user.class';
import { UserService } from './../../../../services/user.service';
import { DateTimeUtils } from './../../../../utils/date-time.utils';
import { StringUtils } from './../../../../utils/string.utils';
import { IMERecheckDialogComponent } from './../../dialog/ime-recheck-dialog/ime-recheck-dialog.component';
import { SortUtils } from './../../../../utils/sort.utils';
import { MdDialog } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { MdDialogConfig } from '@angular/material';
import { IMEData } from './../../../../model/server/persistence/entity/instantaneous/ime-data.class';
import { Component, OnChanges, Input, OnInit } from '@angular/core';

@Component({
	selector: 'app-ime-data-view',
	templateUrl: './ime-data-view.component.html',
	styleUrls: ['./ime-data-view.component.scss']
})
export class IMEDataViewComponent implements OnChanges, OnInit {

	@Input() data:IMEData[];

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
		let dialogRef:MdDialogRef<IMERecheckDialogComponent> = this.dialog.open(IMERecheckDialogComponent, dialogConfig);
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

	openNewRepairDialog(data:IMEData) {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		//dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<IMERepairDialogComponent> = this.dialog.open(IMERepairDialogComponent, dialogConfig);
		if (!data.imeRepairData) {
			data.imeRepairData = new IMERepairData();
		}
		dialogRef.componentInstance.data = data.imeRepairData;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				console.log(result);
				data.imeRepairData = result;
			}
		});
	}

}