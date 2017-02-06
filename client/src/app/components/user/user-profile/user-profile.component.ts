import { Component, Input, OnInit } from '@angular/core';
import { UserService } from './../../../services/user.service';
import { User } from './../../../model/server/persistence/entity/user.class';
import { Person } from './../../../model/server/persistence/entity/person.class';

@Component({
	selector: 'app-user-profile',
	templateUrl: './user-profile.component.html',
	styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
	
	@Input() user:User;

	isNew:boolean = false;

	constructor(private userService:UserService) {}

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