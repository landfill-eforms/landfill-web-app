import { NewUserGroupDialogComponent } from './new-user-group-dialog/new-user-group-dialog.component';
import { UserPermissionSelectorComponent } from './user-permission-selector/user-permission-selector.component';
import { UserGroupComponent } from './user-group/user-group.component';
import { UserGroupsComponent } from './user-group-list/user-group-list.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'

@NgModule({
    imports: [
        CommonModule
    ],
    providers: [],
    declarations: [
		UserGroupsComponent,
		UserGroupComponent,
		UserPermissionSelectorComponent,
        NewUserGroupDialogComponent
	]
})

export class UserGroupModule {

}