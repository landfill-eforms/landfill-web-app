import { InputUtils, InputStatus } from './../../../utils/input.utils';
import { Paginfo, PaginationComponent } from './../../directives/pagination/pagination.component';
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
	@ViewChild('pagination') pagination:PaginationComponent;

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

	showFilters:boolean = false;
	filteredRowsCount:number = 0;
	filteredUsers:User[] = [];
	filters:{text:string} = {
		text: ""
	};
	textFilterStatus:InputStatus = {
		valid: true,
		errorMessage: ""
	}

	paginfo:Paginfo = new Paginfo();
	paginatedUsers:User[] = [];

	showSideInfo:boolean = false;
	selectedUser:User;

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
			this.applyFilters();
			this.paginfo.totalRows = this.users.length;
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
		this.applyFilters();
	}

	toggleFilters() {
		if (this.showFilters) {
			this.showFilters = false;
			this.resetFilters();
		}
		else {
			this.showFilters = true;
		}
	}

	applyFilters() {

		// Validate the text search string.
		InputUtils.isAlphanumeric(this.filters.text, this.textFilterStatus, "Cannot have special characters in search.");

		// If the text search string is invalid, then return.
		if (!this.textFilterStatus.valid) {
			return;
		}

		console.log(this.textFilterStatus);

		this.filteredUsers = this.users.filter(o => {
			if (!this.filters.text) {
				return true;
			}
			let search:RegExp = new RegExp(this.filters.text, 'i');
			return search.test(o.username) || search.test(o.firstname) || search.test(o.lastname) || search.test(o.emailAddress) || search.test(o.employeeId);
		});

		this.paginfo.totalRows = this.filteredUsers.length;
		if (this.pagination) {
			this.pagination.update();
		}
		this.applyPagination();
	}

	resetFilters() {
		this.filters.text = "";
		this.applyFilters();
	}

	applyPagination() {
		this.paginatedUsers = this.filteredUsers.filter((o, i) => {
			return i >= (this.paginfo.currentPage - 1) * this.paginfo.displayedRows && i < this.paginfo.currentPage * this.paginfo.displayedRows;
		});
	}

	toggleSideInfo() {
		if (this.showSideInfo) {
			this.sideInfo.close();
			this.showSideInfo = false;
		}
		else {
			this.sideInfo.open();
			this.showSideInfo = true;
		}
	}

	selectUser(user:User) {
		if (!this.selectedUser) {
			this.sideInfo.open();
			this.showSideInfo = true;
		}
		this.selectedUser = user;
	}

	deselectUser() {
		this.selectedUser = null;
	}

}