import { User } from './../../../model/server/persistence/entity/user/user.class';
import { MdSnackBar } from '@angular/material';
import { Component, Input, OnInit } from '@angular/core';
import { UserService } from './../../../services/user/user.service';

@Component({
	selector: 'app-user-profile',
	templateUrl: './user-profile.component.html',
	styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
	
	@Input() user:User;
	@Input() isDataLoaded:boolean;

	constructor(
		private userService:UserService,
		private snackBar:MdSnackBar,
	) {}

	ngOnInit() {
		if (!this.user) {
			this.user = new User();
			this.user.id = 4;
		}
	}

	save() {
		this.userService.update(this.user,
			(data) => {
				this.snackBar.open("User saved.", "OK", {duration: 2000});
				console.log(data);
			}
		);
	}

}