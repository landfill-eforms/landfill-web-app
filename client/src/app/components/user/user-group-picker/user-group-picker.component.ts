import { UserGroup } from './../../../model/server/persistence/entity/user/user-group.class';
import { UserGroupService } from './../../../services/user/user-group.service';
import { Component, OnChanges, Input, OnInit } from '@angular/core';

@Component({
	selector: 'app-user-group-picker',
	templateUrl: './user-group-picker.component.html',
	styleUrls: ['./user-group-picker.component.scss']
})
export class UserGroupPickerComponent implements OnChanges, OnInit {

	//@Input() availableGroupos:UserGroup[] = [];
	@Input() selectedGroups:UserGroup[] = [];

	availableGroups:UserGroup[];
	updatingGroups:boolean = false;

	constructor(private userGroupService:UserGroupService) {}

	ngOnInit() {
		this.userGroupService.getAll((data) => {
			console.log(data);
			this.availableGroups = data;
			this.initGroups();
		});
	}

	ngOnChanges() {
		if (!this.selectedGroups || !this.availableGroups) {
			return;
		}
		if (this.updatingGroups) {
			this.updatingGroups = false;
			return;
		}
		this.initGroups();
	}

	initGroups() {
		for (let i = 0; i < this.selectedGroups.length; i++) {
			this.addGroup(this.selectedGroups[i], false);
		}
		this.sortGroups(this.availableGroups);
		this.sortGroups(this.selectedGroups);
	}

	addGroup(group:UserGroup, sort:boolean = true) {
		this.updatingGroups = true;
		let idx:number = this.availableGroups.map(group => group.id).indexOf(group.id);
		if (idx > -1) {
			this.availableGroups.splice(idx, 1);
		}
		idx = this.selectedGroups.map(group => group.id).indexOf(group.id);
		if (idx < 0) {
			this.selectedGroups.push(group);
			if (sort) this.sortGroups(this.selectedGroups);
		}
	}

	removeGroup(group:UserGroup, sort:boolean = true) {
		this.updatingGroups = true;
		let idx:number = this.selectedGroups.map(group => group.id).indexOf(group.id);
		if (idx > -1) {
			this.selectedGroups.splice(idx, 1);
		}
		idx = this.availableGroups.map(group => group.id).indexOf(group.id);
		if (idx < 0) {
			this.availableGroups.push(group);
			if (sort) this.sortGroups(this.availableGroups);
		}
	}

	// TODO Allow other types of sorting
	private sortGroups(groups:UserGroup[]):UserGroup[] {
		groups.sort((a, b) => {
			if (a.name == b.name) return 0;
			return a.name > b.name ? 1 : -1;
		});
		return groups;
	}

	consoleLog() {
		console.log(this.availableGroups, this.selectedGroups);
	}

}