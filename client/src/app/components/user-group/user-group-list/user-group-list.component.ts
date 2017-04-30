import { Router, ActivatedRoute } from '@angular/router';
import { UserGroupListSideinfoComponent } from './../user-group-list-sideinfo/user-group-list-sideinfo.component';
import { Paginfo, PaginationComponent } from './../../directives/pagination/pagination.component';
import { InputStatus, InputUtils } from './../../../utils/input.utils';
import { SortUtils } from './../../../utils/sort.utils';
import { Subscription } from 'rxjs/Subscription';
import { NavigationService } from './../../../services/app/navigation.service';
import { UserGroup } from './../../../model/server/persistence/entity/user/user-group.class';
import { NewUserGroupDialogComponent } from './../new-user-group-dialog/new-user-group-dialog.component';
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
export class UserGroupListComponent implements OnInit, OnDestroy {

	@ViewChild('pagination') pagination:PaginationComponent;

	fabActionSubscriber:Subscription;

	isDataLoaded:boolean;
	loadingMessage:string;
	userGroups:UserGroup[];

	sort:any = {
		current: "id",
		reversed: false
	}

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

	showFilters:boolean = false;
	filteredRowsCount:number = 0;
	filteredUserGroups:UserGroup[] = [];
	filters:{text:string} = {
		text: ""
	};
	textFilterStatus:InputStatus = {
		valid: true,
		errorMessage: ""
	};

	paginfo:Paginfo = new Paginfo();
	paginatedUserGroups:UserGroup[] = [];

	showSideInfo:boolean = false;
	selectedUserGroup:UserGroup;

	constructor(
		private router:Router,
		private activatedRoute:ActivatedRoute,
		private userGroupService:UserGroupService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = true;
			navigationService.getSideinfoComponent().setDirective(UserGroupListSideinfoComponent, {userGroup: null});
			navigationService.getSideinfoComponent().enable();
	}

	ngOnInit() {
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
					this.openNewUserGroupDialog();
				}
			});
		this.loadingMessage = "Loading User Groups...";
		this.loadUserGroups();
	}
	
	ngOnDestroy() {
		this.navigationService.getSideinfoComponent().disable();
		this.navigationService.getNavbarComponent().resetFabInfo();
		this.navigationService.getNavbarComponent().resetFabActionSource();
		this.fabActionSubscriber.unsubscribe();
	}

	loadUserGroups() {
		this.userGroupService.getAll((data) => {
			console.log(data);
			this.userGroups = data;
			this.applyFilters();
			this.paginfo.totalRows = this.userGroups.length;
			this.navigationService.getSideinfoComponent().open();
			this.isDataLoaded = true;
		});
	}

	openNewUserGroupDialog() {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
			//dialogConfig.height = '480px';
		let dialogRef:MdDialogRef<NewUserGroupDialogComponent> = this.dialog.open(NewUserGroupDialogComponent, dialogConfig);
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.snackBar.open("New user group has been created.", "OK", {duration: 2000});
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading User Groups..."
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
					this.snackBar.open("User group has been deleted.", "OK", {duration: 2000});
					this.isDataLoaded = false;
					this.loadingMessage = "Reloading User Groups..."
					this.loadUserGroups();
				}
			);
		}
	}

	sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.userGroups, this.sortProperties[sortBy]);
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

		// TODO Implement this.
		this.filteredUserGroups = this.userGroups.filter(o => true);

		this.paginfo.totalRows = this.filteredUserGroups.length;
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
		this.paginatedUserGroups = this.filteredUserGroups.filter((o, i) => {
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
	}

	navigateToUserGroup(userGroup:UserGroup) {
		this.router.navigate([userGroup.id], {relativeTo: this.activatedRoute});
	}

}