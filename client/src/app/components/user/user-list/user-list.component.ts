import { UserGroup } from './../../../model/server/persistence/entity/user/user-group.class';
import { UserGroupService } from './../../../services/user/user-group.service';
import { UserPermission } from './../../../model/server/persistence/enums/user/user-permission.enum';
import { AuthService } from './../../../services/auth/auth.service';
import { EnumUtils } from './../../../utils/enum.utils';
import { UserDialogComponent } from './../dialog/user-dialog/user-dialog.component';
import { AbstractDataTableComponent } from './../../../model/client/abstract-components/abstract-data-table.component';
import { Router, ActivatedRoute } from '@angular/router';
import { UserListSideinfoComponent } from './../user-list-sideinfo/user-list-sideinfo.component';
import { NavigationService } from './../../../services/app/navigation.service';
import { InputUtils, InputStatus } from './../../../utils/input.utils';
import { Paginfo, PaginationComponent } from './../../directives/pagination/pagination.component';
import { StringUtils } from './../../../utils/string.utils';
import { Sort, SortUtils } from './../../../utils/sort.utils';
import { User } from './../../../model/server/persistence/entity/user/user.class';
import { Component, OnInit, OnDestroy, ViewChild, ElementRef } from '@angular/core';
import { UserService } from './../../../services/user/user.service';
import { MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar } from "@angular/material";
import { Title } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';

@Component({
	selector: 'app-user-list',
	templateUrl: './user-list.component.html'
})
export class UserListComponent extends AbstractDataTableComponent<User> implements OnInit, OnDestroy {

	// Utilities
	StringUtils = StringUtils;

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	isUserGroupsLoaded:boolean;
	loadingMessage:string;
	userGroups:UserGroup[];

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
	};

	filters:{text:string, status:number} = {
		text: "",
		status: 0
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

	showSideInfo:boolean = false;
	selectedUser:User;

	isAdmin:boolean;
	canChangeUsername:boolean;
	canChangePassword:boolean;
	canChangeStatus:boolean;

	constructor(
		private authService:AuthService,
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private userService:UserService,
		private userGroupService:UserGroupService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			super();
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(UserListSideinfoComponent, {user: null});
			navigationService.getSideinfoComponent().enable();
	}

	ngOnInit() {
		this.isAdmin = this.authService.canAccess([UserPermission.ADMIN]);
		console.log(this.isAdmin ? "is admin" : "not admin")
		this.canChangeUsername = this.authService.canAccess([UserPermission.RESET_USER_USERNAMES]);
		this.canChangePassword = this.authService.canAccess([UserPermission.RESET_USER_PASSWORDS]);
		this.canChangeStatus = this.authService.canAccess([UserPermission.CHANGE_USER_STATUS]);
		this.canEdit = this.authService.canAccess([UserPermission.EDIT_USER_PROFILES]);
		if(this.authService.canAccess([UserPermission.CREATE_USERS])) {
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
						this.openUserDialog(null);
					}
				});
		}
		this.loadingMessage = "Loading Users...";
		this.loadUsers();
		this.loadUserGroups();
	}

	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
		if (this.fabActionSubscriber) {
			this.navigationService.getNavbarComponent().resetFabInfo();
			this.navigationService.getNavbarComponent().resetFabActionSource();
			this.fabActionSubscriber.unsubscribe();
		}
	}

	private loadUsers() {
		this.userService.getAll((data) => {
			console.log(data);
			this.data = data;
			this.applyFilters();
			this.paginfo.totalRows = this.data.length;
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
			this.isDataLoaded = true;
		});
	}

	private loadUserGroups() {
		this.userGroupService.getAll((data) => {
			console.log(data);
			this.userGroups = data;
			this.isUserGroupsLoaded = true;
		});
	}

	openUserDialog(user:User) {
		if (!this.isDataLoaded || !this.isUserGroupsLoaded) {
			return;
		}
		let isNew:boolean = !user;
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '800px';
		let dialogRef:MdDialogRef<UserDialogComponent> = this.dialog.open(UserDialogComponent, dialogConfig);
		dialogRef.componentInstance.userGroups = this.userGroups;
		dialogRef.componentInstance.isAdmin = this.isAdmin;
		if (isNew) {
			dialogRef.componentInstance.isNew = true;
		}
		else {
			dialogRef.componentInstance.user = user;
		}
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				console.log("RESULT", result)
				if (isNew) {
					this.snackBar.open("New user has been registered.", "OK", {duration: 2000});
				}
				else {
					this.snackBar.open("User profile updated.", "OK", {duration: 2000});
				}
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading Users..."
				this.loadUsers();
			}
		});
	}

	applyFilters() {

		// Validate the text search string.
		InputUtils.isAlphanumeric(this.filters.text, this.textFilterStatus, "Cannot have special characters in search.");

		// If the text search string is invalid, then return.
		if (!this.textFilterStatus.valid) {
			return;
		}

		this.filteredData = this.data.filter(o => {
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

		this.paginfo.totalRows = this.filteredData.length;
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
		this.navigationService.getSideinfoComponent().getDirective().setData({
			user: this.selectedUser,
			canChangeUsername: this.canChangeUsername,
			canChangePassword: this.canChangePassword,
			canChangeStatus: this.canChangeStatus,
		});
	}

	deselectUser() {
		this.selectedUser = null;
	}

}