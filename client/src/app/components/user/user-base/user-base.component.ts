import { User } from './../../../model/server/persistence/entity/user/user.class';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from './../../../services/user/user.service';

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
		this.username = this.activatedRoute.params['_value']['username'];
		console.log(this.username);
		this.userService.getByUsername(this.username,
			(data) => {
				console.log(data);
				this.user = data;
				this.isDataLoaded = true;
			}
		);
	}
	
}