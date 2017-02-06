import { UserGroupService } from './../../../services/user-group.service';
import { UserGroup } from './../../../model/server/persistence/entity/user-group.class';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
	selector: 'app-user-group',
	templateUrl: './user-group.component.html',
	styleUrls: ['./user-group.component.scss']
})
export class UserGroupComponent implements OnInit {

isDataLoaded:boolean;
	groupId:string;
	userGroup:UserGroup = new UserGroup();

	constructor(
		private userGroupService:UserGroupService,
		private activatedRoute:ActivatedRoute,
	) {}

	ngOnInit() {
		this.groupId = this.activatedRoute.params['_value']['id'];
		console.log(this.groupId);
		this.userGroupService.getById((data) => {
			console.log(data);
			this.userGroup = data;
			this.isDataLoaded = true;
		}, this.groupId);
	}

	save() {
		this.userGroupService.update((data) => {
			console.log(data);
		}, this.userGroup);
	}

}