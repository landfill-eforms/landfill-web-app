import { User } from './../../../model/server/persistence/entity/user/user.class';
import { Person } from './../../../model/server/persistence/entity/user/person.class';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from './../../../services/user.service';

@Component({
	selector: 'app-user-base',
	templateUrl: './user-base.component.html',
	styleUrls: ['./user-base.component.scss']
})
export class UserBaseComponent implements OnInit {

	isDataLoaded:boolean;
	username:string;
	user:User = new User();

	constructor(
		private userService:UserService,
		private activatedRoute:ActivatedRoute,
	) {}

	ngOnInit() {
		this.user = new User();
		this.user.person = new Person();
		this.username = this.activatedRoute.params['_value']['username'];
		console.log(this.username);
		this.userService.getByUsername((data) => {
			console.log(data);
			this.user = data;
			//this.user.userGroups.map(g => {g.users = undefined}); // Need to remove the list of users; it will cause deserialization erros when saving.
			this.isDataLoaded = true;
		}, this.username);
	}
	
}