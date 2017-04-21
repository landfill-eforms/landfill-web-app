import { UserGroupListSideinfoComponent } from './user-group-list-sideinfo/user-group-list-sideinfo.component';
import { DirectivesModule } from './../directives/directives.module';
import { NewUserGroupDialogComponent } from './new-user-group-dialog/new-user-group-dialog.component';
import { UserPermissionSelectorComponent } from './user-permission-selector/user-permission-selector.component';
import { UserGroupComponent } from './user-group/user-group.component';
import { UserGroupListComponent } from './user-group-list/user-group-list.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'

@NgModule({
    imports: [
        CommonModule,
        DirectivesModule
    ],
    providers: [],
    declarations: [
		UserGroupListComponent,
		UserGroupComponent,
		UserPermissionSelectorComponent,
        NewUserGroupDialogComponent,
        UserGroupListSideinfoComponent
	]
})

export class UserGroupModule {

}