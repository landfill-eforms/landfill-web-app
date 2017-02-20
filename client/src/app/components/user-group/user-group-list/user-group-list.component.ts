import { UserGroup } from './../../../model/server/persistence/entity/user/user-group.class';
import { NewUserGroupDialogComponent } from './../new-user-group-dialog/new-user-group-dialog.component';
import { MdDialogConfig } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { MdDialog } from '@angular/material';
import { MdSnackBar } from '@angular/material';
import { async } from '@angular/core/testing';
import { UserGroupService } from './../../../services/user-group.service';
import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-user-group-list',
	templateUrl: './user-group-list.component.html',
	styleUrls: ['./user-group-list.component.scss']
})
export class UserGroupsComponent implements OnInit {

	isDataLoaded:boolean;
	loadingMessage:string;
	userGroups:UserGroup[];
	sort:any = {
		current: "id",
		reversed: false
	}

	constructor(
		private userGroupService:UserGroupService,
		private dialog:MdDialog,
		private snackBar:MdSnackBar
	) {}

	ngOnInit() {
		this.loadingMessage = "Loading User Groups...";
		this.loadUserGroups();
	}

	loadUserGroups() {
		this.userGroupService.getAll((data) => {
			console.log(data);
			this.userGroups = data;
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
			this.userGroupService.delete((data) => {
				console.log(data);
				this.snackBar.open("User group has been deleted.", "OK", {duration: 2000});
				this.isDataLoaded = false;
				this.loadingMessage = "Reloading User Groups..."
				this.loadUserGroups();
			}, userGroup);
		}
	}

	sortByGroupName() {
		if (this.sort.current === "name") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "name";
			this.sort.reversed = false;
		}
		this.userGroups.sort((a, b) => {
			return this.groupNameSortFunction(a.name.toLowerCase(), b.name.toLowerCase(), this.sort.reversed);
		});
	}

	sortByUserCount() {
		if (this.sort.current === "users") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "users";
			this.sort.reversed = false;
		}
		this.userGroups.sort((a, b) => {
			let compareCount:number = (a.users.length - b.users.length) * (this.sort.reversed ? 1 : -1);
			if (compareCount != 0) {
				return compareCount;
			}
			return this.groupNameSortFunction(a.name.toLowerCase(), b.name.toLowerCase(), false);
		});
	}

	// TODO Move this to a util class.
	private groupNameSortFunction(a:string, b:string, reversed:boolean):number {
		if (a == b) return 0;
		return (a > b ? 1 : -1) * (reversed ? -1 : 1);
	}

}