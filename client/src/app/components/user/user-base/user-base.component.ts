import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from './../../../services/user.service';
import { User } from './../../../model/server/persistence/entity/user.class';
import { Person } from './../../../model/server/persistence/entity/person.class';

@Component({
	selector: 'app-user-base',
	templateUrl: './user-base.component.html',
	styleUrls: ['./user-base.component.scss']
})
export class UserBaseComponent implements OnInit {

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
		}, this.username);
	}
	
}