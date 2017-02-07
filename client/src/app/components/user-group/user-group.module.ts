import { NewUserGroupDialogComponent } from './new-user-group-dialog/new-user-group-dialog.component';
import { UserRoleSelectorComponent } from './user-role-selector/user-role-selector.component';
import { UserGroupComponent } from './user-group/user-group.component';
import { UserGroupsComponent } from './user-groups/user-groups.component';
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
		UserRoleSelectorComponent,
        NewUserGroupDialogComponent
	]
})

export class UserGroupModule {

}