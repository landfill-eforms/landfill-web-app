import { Component, Input, OnInit } from '@angular/core';
import { UserService } from './../../../services/user-service';

@Component({
	selector: 'app-user-profile',
	templateUrl: './user-profile.component.html',
	styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
	
	@Input() user:any;

	constructor(private userService:UserService) {}

	ngOnInit() {
		if (!this.user) {
			this.user = {
				id: 0,
				userProfile: {id: 0}
			}
		}
	}

	save() {
		console.log("SAVE")
		this.userService.save((data) => {
			console.log(data);
		}, this.user);
	}

}