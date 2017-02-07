import { UserRole } from './../../../model/server/model/user-role.enum';
import { Component, OnChanges, Input } from '@angular/core';

@Component({
	selector: 'app-user-role-selector',
	templateUrl: './user-role-selector.component.html',
	styleUrls: ['./user-role-selector.component.scss']
})
export class UserRoleSelectorComponent implements OnChanges {

	@Input() selectedRoles:UserRole[] = [];

	availableRoles:UserRole[] = [];
	updatingRoles:boolean = false;

	ngOnChanges() {
		if (!this.selectedRoles) {
			return;
		}
		if (this.updatingRoles) {
			this.updatingRoles = false;
			return;
		}
		this.availableRoles = UserRole.values();
		for (let i = 0; i < this.selectedRoles.length; i++) {
			this.addRole(this.selectedRoles[i], false);
		}
		this.sortRoles(this.availableRoles);
		this.sortRoles(this.selectedRoles);
	}

	addRole(role:UserRole, sort:boolean = true) {
		this.updatingRoles = true;
		let idx:number = this.availableRoles.indexOf(role);
		if (idx > -1) {
			this.availableRoles.splice(idx, 1);
		}
		idx = this.selectedRoles.indexOf(role);
		if (idx < 0) {
			this.selectedRoles.push(role);
			if (sort) this.sortRoles(this.selectedRoles);
		}
	}

	removeRole(role:UserRole, sort:boolean = true) {
		this.updatingRoles = true;
		let idx:number = this.selectedRoles.indexOf(role);
		if (idx > -1) {
			this.selectedRoles.splice(idx, 1);
		}
		idx = this.availableRoles.indexOf(role);
		if (idx < 0) {
			this.availableRoles.push(role);
			if (sort) this.sortRoles(this.availableRoles);
		}
	}

	// TODO Allow other types of sorting
	private sortRoles(roles:UserRole[]):UserRole[] {
		roles.sort((a, b) => {
			let stringA:string = a.group ? a.group + a.groupAction : a.name;
			let stringB:string = b.group ? b.group + b.groupAction : b.name;
			if (stringA == stringB) return 0;
			return stringA > stringB ? 1 : -1;
		});
		return roles;
	}

	consoleLog() {
		console.log(this.availableRoles, this.selectedRoles);
	}

}