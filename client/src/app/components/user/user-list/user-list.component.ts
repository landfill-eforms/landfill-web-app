import { User } from './../../../model/server/persistence/entity/user/user.class';
import { NewUserDialogComponent } from './../new-user-dialog/new-user-dialog.component';
import { Component, OnInit } from '@angular/core';
import { UserService } from './../../../services/user.service';
import { MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar } from "@angular/material";

@Component({
	selector: 'app-users',
	templateUrl: './user-list.component.html',
	styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

	isDataLoaded:boolean;
	loadingMessage:string;
	users:User[];
	sort:any = {
		current: "id",
		reversed: false
	}

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
	
	sortByUsername() {
		if (this.sort.current === "username") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "username";
			this.sort.reversed = false;
		}
		this.users.sort((a, b) => {
			return this.stringSortFunction(a.username, b.username, this.sort.reversed);
		});
	}

	sortByName() {
		if (this.sort.current === "name") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "name";
			this.sort.reversed = false;
		}
		this.users.sort((a, b) => {
			return this.stringSortFunction(a.person.lastname + a.person.firstname, b.person.lastname + b.person.firstname, this.sort.reversed);
		});
	}

	sortByEmail() {
		if (this.sort.current === "email") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "email";
			this.sort.reversed = false;
		}
		this.users.sort((a, b) => {
			return this.stringSortFunction(a.person.emailAddress, b.person.emailAddress, this.sort.reversed);
		});
	}

	sortByEmployeeId() {
		if (this.sort.current === "employeeId") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "employeeId";
			this.sort.reversed = false;
		}
		this.users.sort((a, b) => {
			return this.stringSortFunction(a.person.employeeId, b.person.employeeId, this.sort.reversed);
		});
	}

	// TODO Move this to a util class.
	private stringSortFunction(a:string, b:string, reversed:boolean):number {
		if (a == b) return 0;
		return (a > b ? 1 : -1) * (reversed ? -1 : 1);
	}

}