import { Router, ActivatedRoute } from '@angular/router';
import { UserListSideinfoComponent } from './../user-list-sideinfo/user-list-sideinfo.component';
import { NavigationService } from './../../../services/app/navigation.service';
import { InputUtils, InputStatus } from './../../../utils/input.utils';
import { Paginfo, PaginationComponent } from './../../directives/pagination/pagination.component';
import { StringUtils } from './../../../utils/string.utils';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { User } from './../../../model/server/persistence/entity/user/user.class';
import { NewUserDialogComponent } from './../new-user-dialog/new-user-dialog.component';
import { Component, OnInit, OnDestroy, ViewChild, ElementRef } from '@angular/core';
import { UserService } from './../../../services/user/user.service';
import { MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar } from "@angular/material";
import { Title } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';

@Component({
	selector: 'app-user-list',
	templateUrl: './user-list.component.html',
	styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit, OnDestroy {

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

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
	filters:{text:string, status:number} = {
		text: "",
		status: 0
	};
	textFilterStatus:InputStatus = {
		valid: true,
		errorMessage: ""
	};
	statusFilterChoices:{value:number, label:string}[] = [
		{
			value: 0,
			label: "Any"
		},
		{
			value: 1,
			label: "Enabled"
		},
		{
			value: 2,
			label: "Disabled"
		}
	];

	paginfo:Paginfo = new Paginfo();
	paginatedUsers:User[] = [];

	showSideInfo:boolean = false;
	selectedUser:User;

	constructor(
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private userService:UserService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(UserListSideinfoComponent, {user: null});
	}

	ngOnInit() {
		this.navigationService.getNavbarComponent().setFabInfo({
			icon: "add",
			tooltip: "New User"
		});
		this.fabActionSubscriber = this.navigationService
			.getNavbarComponent()
			.getFabActionSource()
			.asObservable()
			.subscribe((event) => {
				console.log(event)
				if (event instanceof MouseEvent) {
					this.openNewUserDialog();
				}
			});
		this.loadingMessage = "Loading Users...";
		this.loadUsers();
	}

	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
		this.navigationService.getNavbarComponent().resetFabInfo();
		this.navigationService.getNavbarComponent().resetFabActionSource();
		this.fabActionSubscriber.unsubscribe();
	}

	loadUsers() {
		this.userService.getAll((data) => {
			console.log(data);
			this.users = data;
			this.applyFilters();
			this.paginfo.totalRows = this.users.length;
			this.navigationService.getSideinfoComponent().open();
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

		this.filteredUsers = this.users.filter(o => {
			let textMatch:boolean = true;
			if (this.filters.text) {
				let search:RegExp = new RegExp(this.filters.text, 'i');
				textMatch = search.test(o.username) || search.test(o.firstname) || search.test(o.lastname) || search.test(o.emailAddress) || search.test(o.employeeId);
			}
			let statusMatch:boolean = true;
			if (this.filters.status == 1) {
				statusMatch = o.enabled;
			}
			else if (this.filters.status == 2) {
				statusMatch = !o.enabled;
			}
			return textMatch && statusMatch;
		});

		this.paginfo.totalRows = this.filteredUsers.length;
		if (this.pagination) {
			this.pagination.update();
		}
		this.applyPagination();
	}

	resetFilters() {
		this.filters.text = "";
		this.filters.status = 0;
		this.applyFilters();
	}

	applyPagination() {
		this.paginatedUsers = this.filteredUsers.filter((o, i) => {
			return i >= (this.paginfo.currentPage - 1) * this.paginfo.displayedRows && i < this.paginfo.currentPage * this.paginfo.displayedRows;
		});
	}

	toggleSideInfo() {
		if (this.showSideInfo) {
			this.navigationService.getSideinfoComponent().close();
			this.showSideInfo = false;
		}
		else {
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
		}
	}

	selectUser(user:User) {
		if (!this.selectedUser) {
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
		}
		this.selectedUser = user;
		this.navigationService.getSideinfoComponent().subtitle = this.selectedUser.firstname + " " + this.selectedUser.middlename + " " + this.selectedUser.lastname; 
		this.navigationService.getSideinfoComponent().getDirective().setData(this.selectedUser);
	}

	deselectUser() {
		this.selectedUser = null;
	}

	navigateToUser(user:User) {
		this.router.navigate([user.username], {relativeTo: this.activatedRoute});
	}

}