import { UserPermission } from './../../../model/server/persistence/enums/user/user-permission.enum';
import { Component, OnChanges, Input } from '@angular/core';

@Component({
	selector: 'app-user-permission-picker',
	templateUrl: './user-permission-picker.component.html',
	styleUrls: ['./user-permission-picker.component.scss']
})
export class UserPermissionPickerComponent implements OnChanges {

	@Input() selectedPermissions:UserPermission[] = [];

	availablePermissions:UserPermission[] = [];
	updatingPermissions:boolean = false;

	ngOnChanges() {
		if (!this.selectedPermissions) {
			return;
		}
		if (this.updatingPermissions) {
			this.updatingPermissions = false;
			return;
		}
		this.availablePermissions = UserPermission.values();
		for (let i = 0; i < this.selectedPermissions.length; i++) {
			this.addPermission(this.selectedPermissions[i], false);
		}
		this.sortPermissions(this.availablePermissions);
		this.sortPermissions(this.selectedPermissions);
	}

	addPermission(permission:UserPermission, sort:boolean = true) {
		this.updatingPermissions = true;
		let idx:number = this.availablePermissions.indexOf(permission);
		if (idx > -1) {
			this.availablePermissions.splice(idx, 1);
		}
		idx = this.selectedPermissions.indexOf(permission);
		if (idx < 0) {
			this.selectedPermissions.push(permission);
			if (sort) this.sortPermissions(this.selectedPermissions);
		}
	}

	removePermission(permission:UserPermission, sort:boolean = true) {
		this.updatingPermissions = true;
		let idx:number = this.selectedPermissions.indexOf(permission);
		if (idx > -1) {
			this.selectedPermissions.splice(idx, 1);
		}
		idx = this.availablePermissions.indexOf(permission);
		if (idx < 0) {
			this.availablePermissions.push(permission);
			if (sort) this.sortPermissions(this.availablePermissions);
		}
	}

	// TODO Allow other types of sorting
	private sortPermissions(permissions:UserPermission[]):UserPermission[] {
		// let properties:string[]  = [
		// 	"category",
		// 	"categoryAction",
		// 	"name"
		// ];
		// SortUtils.sort(permissions, [], false);
		permissions.sort((a, b) => {
			let stringA:string = a.category ? a.category + a.categoryAction : a.name;
			let stringB:string = b.category ? b.category + b.categoryAction : b.name;
			if (stringA == stringB) return 0;
			return stringA > stringB ? 1 : -1;
		});
		return permissions;
	}

	consoleLog() {
		console.log(this.availablePermissions, this.selectedPermissions);
	}

}