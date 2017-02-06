import { UserGroupService } from './../../../services/user-group.service';
import { UserGroup } from './../../../model/server/persistence/entity/user-group.class';
import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-user-groups',
	templateUrl: './user-groups.component.html',
	styleUrls: ['./user-groups.component.scss']
})
export class UserGroupsComponent implements OnInit {

	isDataLoaded:boolean;
	loadingMessage:string;
	userGroups:UserGroup[];
	sort:any = {
		current: "id",
		reversed: false
	}

	constructor(private userGroupService:UserGroupService) {}

	ngOnInit() {
		this.loadingMessage = "Loading User Groups...";
		this.userGroupService.getAll((data) => {
			console.log(data);
			this.userGroups = data;
			this.isDataLoaded = true;
		});
	}

	test() {
		this.sort.current = "users";
		console.log(this.userGroups.sort((a, b) => a.users.length - b.users.length));
	}

	sortByGroupName() {
		if (this.sort.current === "name") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "name";
			this.sort.reversed = false;
		}
		this.userGroups.sort((a, b) => {
			let aName = a.name.toLowerCase();
			let bName = b.name.toLowerCase();
			if (aName > bName) return this.sort.reversed ? -1 : 1;
			if (aName === bName) return 0;
			if (aName < bName) return this.sort.reversed ? 1 : -1;
		});
	}

	sortByUserCount() {
		if (this.sort.current === "users") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "users";
			this.sort.reversed = false;
		}
		this.userGroups.sort((a, b) => {
			return (a.users.length - b.users.length) * (this.sort.reversed ? 1 : -1);
		});
	}

}