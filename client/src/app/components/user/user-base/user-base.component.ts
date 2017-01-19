import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from './../../../services/user-service';

@Component({
	selector: 'app-user-base',
	templateUrl: './user-base.component.html',
	styleUrls: ['./user-base.component.scss']
})
export class UserBaseComponent implements OnInit {

	username:string = "aquach";
	user:any = {
		userProfile: {}
	};

	constructor(
		private userService:UserService,
		private activatedRoute:ActivatedRoute,
	) {}

	ngOnInit() {
		this.username = this.activatedRoute.params['_value']['username'];
		console.log(this.username);
		this.userService.getByUsername((data) => {
			console.log(data);
			this.user = data;
		}, this.username);
	}
	
}