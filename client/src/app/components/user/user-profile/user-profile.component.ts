import { Person } from './../../../model/server/persistence/entity/user/person.class';
import { User } from './../../../model/server/persistence/entity/user/user.class';
import { MdSnackBar } from '@angular/material';
import { Component, Input, OnInit } from '@angular/core';
import { UserService } from './../../../services/user.service';

@Component({
	selector: 'app-user-profile',
	templateUrl: './user-profile.component.html',
	styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
	
	@Input() user:User;
	@Input() isDataLoaded:boolean;

	isNew:boolean = false;

	constructor(
		private userService:UserService,
		private snackBar:MdSnackBar,
	) {}

	ngOnInit() {
		if (!this.user) {
			this.isNew = true;
			this.user = new User();
			this.user.id = 4;
			this.user.person = new Person();
			this.user.person.id = 4;
		}
	}

	save() {
		console.log("SAVE USER", this.user);
		if (!this.isNew) {
			this.userService.update((data) => {
				this.snackBar.open("User saved.", "OK", {duration: 2000});
				console.log(data);
			}, this.user);
		}
		else {
			this.userService.create((data) => {
				console.log(data);
			}, this.user);
		}
	}

}