import { Pagination } from './../../directives/pagination/pagination.component';
import { StringUtils } from './../../../utils/string.utils';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { User } from './../../../model/server/persistence/entity/user/user.class';
import { NewUserDialogComponent } from './../new-user-dialog/new-user-dialog.component';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { UserService } from './../../../services/user/user.service';
import { MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar } from "@angular/material";

@Component({
	selector: 'app-users',
	templateUrl: './user-list.component.html',
	styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

	@ViewChild('sideinfo') sideInfo:any;
	@ViewChild('hello') dataTableScrollableArea:ElementRef;

	stringUtils = StringUtils;

	isDataLoaded:boolean;
	loadingMessage:string;
	users:User[];
	
	sort:Sort = {
		current: "id",
		reversed: false
	}

	sortProperties:any = {
		username: [
			"username"
		],
		name: [
			"lastname",
			"firstname"
		],
		emailAddress: [
			"emailAddress"
		],
		employeeId: [
			"employeeId"
		]
	}

	pagination:Pagination = new Pagination();

	sideInfoOpen:boolean = false;
	scrollArea:Element;

	constructor(
		private userService:UserService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar
	) {}

	ngOnInit() {
		this.loadingMessage = "Loading Users...";
		this.loadUsers();
		
	}

	loadUsers() {
		this.userService.getAll((data) => {
			console.log(data);
			this.users = data;
			this.isDataLoaded = true;
		});
	}

	openNewUserDialog() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		//dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<NewUserDialogComponent> = this.dialog.open(NewUserDialogComponent, dialogConfig);
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("New user has been registered.", "OK", {duration: 2000});
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading Users..."
				this.loadUsers();
			}
		});
	}

	sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.users, this.sortProperties[sortBy]);
	}

	toggleSideInfo() {
		if (this.sideInfoOpen) {
			this.sideInfo.close();
			this.sideInfoOpen = false;
		}
		else {
			this.sideInfo.open();
			this.sideInfoOpen = true;
		}
	}

	dataTableHeaderLeftMargin():string {
		console.log("HELLO");
		if (!this.dataTableScrollableArea) {
			return "0px";
		}
		let element:any = this.dataTableScrollableArea.nativeElement;
		if (element.offsetHeight < element.scrollHeight || element.offsetWidth < element.scrollWidth) {
			return "17px"
		}
		else {
			return "0px"
		}
	}

}