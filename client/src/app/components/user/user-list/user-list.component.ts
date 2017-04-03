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

	stringUtils = StringUtils;

	isDataLoaded:boolean;
	loadingMessage:string;
	users:User[] = [];
	
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

	filterResultCount:number = 0;
	pagination:Pagination = new Pagination();
	filteredUsers:User[] = [];

	sideInfoOpen:boolean = false;

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

	applyFilters() {

		if (this.filteredUsers.length !=0) {
			this.filteredUsers = [];
		}

		// TODO Search filters
		this.pagination.totalRows = this.users.length;

		// Pagination filters
		let filtered:User[] = this.users.filter((x, i) => {
			return i >= (this.pagination.currentPage - 1) * this.pagination.displayedRows && i < this.pagination.currentPage * this.pagination.displayedRows;
		});

		for (let a of filtered) {
			this.filteredUsers.push(a);
		}
	}

}