import { EnumUtils } from './../../../utils/enum.utils';
import { UserPermission } from './../../../model/server/persistence/enums/user/user-permission.enum';
import { AbstractDataTableComponent } from './../../../model/client/abstract-components/abstract-data-table.component';
import { AuthService } from './../../../services/auth/auth.service';
import { UserGroupDialogComponent } from './../dialog/user-group-dialog/user-group-dialog.component';
import { Router, ActivatedRoute } from '@angular/router';
import { UserGroupListSideinfoComponent } from './../user-group-list-sideinfo/user-group-list-sideinfo.component';
import { Paginfo, PaginationComponent } from './../../directives/pagination/pagination.component';
import { InputStatus, InputUtils } from './../../../utils/input.utils';
import { SortUtils } from './../../../utils/sort.utils';
import { Subscription } from 'rxjs/Subscription';
import { NavigationService } from './../../../services/app/navigation.service';
import { UserGroup } from './../../../model/server/persistence/entity/user/user-group.class';
import { MdDialogConfig } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { MdDialog } from '@angular/material';
import { MdSnackBar } from '@angular/material';
import { async } from '@angular/core/testing';
import { UserGroupService } from './../../../services/user/user-group.service';
import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';

@Component({
	selector: 'app-user-group-list',
	templateUrl: './user-group-list.component.html'
})
export class UserGroupListComponent extends AbstractDataTableComponent<UserGroup> implements OnInit, OnDestroy {

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	loadingMessage:string;

	sortProperties:any = {
		name: [
			"name",
			"id"
		],
		userCount: [
			"users.length",
			"name",
			"id"
		]
	}

	filters:{text:string} = {
		text: ""
	};

	showSideInfo:boolean = false;
	selectedUserGroup:UserGroup;

	canDelete:boolean;

	constructor(
		private authService:AuthService,
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private userGroupService:UserGroupService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			super();
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(UserGroupListSideinfoComponent, {userGroup: null});
			navigationService.getSideinfoComponent().enable();
	}

	ngOnInit() {
		this.canDelete = this.authService.canAccess([UserPermission.DELETE_USER_GROUPS]);
		this.canEdit = this.authService.canAccess([UserPermission.EDIT_USER_PROFILES]);
		if(this.authService.canAccess([UserPermission.CREATE_USERS])) {
			this.navigationService.getNavbarComponent().setFabInfo({
				icon: "add",
				tooltip: "New User Group"
			});
			this.fabActionSubscriber = this.navigationService
				.getNavbarComponent()
				.getFabActionSource()
				.asObservable()
				.subscribe((event) => {
					if (event instanceof MouseEvent) {
						this.openUserGroupDialog(null);
					}
				});
		}
		this.loadingMessage = "Loading User Groups...";
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

	private loadUserGroups() {
		this.userGroupService.getAll((data) => {
			console.log(data);
			this.data = data;
			for (let userGroup of this.data) {
				let permissions:UserPermission[] = userGroup.userPermissions;
				userGroup.userPermissions = [];
				for (let permission of permissions) {
					userGroup.userPermissions.push(EnumUtils.convertToEnum(UserPermission, permission));
				}
			}
			this.applyFilters();
			this.paginfo.totalRows = this.data.length;
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
			this.isDataLoaded = true;
		});
	}

	openUserGroupDialog(userGroup:UserGroup) {
		let isNew:boolean = !userGroup;
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '800px';
		dialogConfig.height = '540px';
		let dialogRef:MdDialogRef<UserGroupDialogComponent> = this.dialog.open(UserGroupDialogComponent, dialogConfig);
		dialogRef.componentInstance.adminGroupExists = this.adminGroupExists(this.data);
		if (isNew) {
			dialogRef.componentInstance.isNew = true;
		}
		else {
			dialogRef.componentInstance.userGroup = userGroup;
		}
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				if (isNew) {
					this.snackBar.open("New user group has been created.", "OK", {duration: 3000});
				}
				else {
					this.snackBar.open("User group updated.", "OK", {duration: 3000});
				}
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading User Groups..."
				this.deselectUserGroup();
				this.loadUserGroups();
			}
		});
	}

	delete(userGroup:UserGroup) {
		if (userGroup.users.length) {
			this.snackBar.open("This user group cannot be deleted because it is assigned to user(s).", "OK", {duration: 3000});
		}
		else {
			this.userGroupService.delete(userGroup,
				(data) => {
					console.log(data);
					this.snackBar.open("User group has been deleted.", "OK", {duration: 3000});
					this.isDataLoaded = false;
					this.loadingMessage = "Reloading User Groups..."
					this.loadUserGroups();
				}
			);
		}
	}

	applyFilters() {

		// Validate the text search string.
		InputUtils.isAlphanumeric(this.filters.text, this.textFilterStatus, "Cannot have special characters in search.");

		// If the text search string is invalid, then return.
		if (!this.textFilterStatus.valid) {
			return;
		}

		this.filteredData = this.data.filter(o => {
			if (this.filters.text) {
				let search:RegExp = new RegExp(this.filters.text, 'i');
				return search.test(o.name) || search.test(o.description);
			}
			return true;
		});

		this.paginfo.totalRows = this.filteredData.length;
		if (this.pagination) {
			this.pagination.update();
		}
		this.applyPagination();
	}

	resetFilters() {
		this.filters.text = "";
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

	selectUserGroup(userGroup:UserGroup) {
		if (!this.selectedUserGroup) {
			this.navigationService.getSideinfoComponent().open();
			this.showSideInfo = true;
		}
		this.selectedUserGroup = userGroup;
		this.navigationService.getSideinfoComponent().subtitle = this.selectedUserGroup.name; 
		this.navigationService.getSideinfoComponent().getDirective().setData(this.selectedUserGroup);
	}

	deselectUserGroup() {
		this.selectedUserGroup = null;
		this.navigationService.getSideinfoComponent().subtitle = ""; 
		this.navigationService.getSideinfoComponent().getDirective().setData(null);
	}

	private adminGroupExists(userGroups:UserGroup[]):boolean {
		for (let userGroup of userGroups) {
			for (let permission of userGroup.userPermissions) {
				if (permission.ordinal == UserPermission.ADMIN.ordinal) {
					return true;
				}
			}
		}
		return false;
	}
	
	isNavDrawerOpen():boolean {
		return this.navigationService.isNavDrawerOpened();
	}

}