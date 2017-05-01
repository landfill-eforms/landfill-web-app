import { UserGroupDialogComponent } from './dialog/user-group-dialog/user-group-dialog.component';
import { UserGroupListSideinfoComponent } from './user-group-list-sideinfo/user-group-list-sideinfo.component';
import { DirectivesModule } from './../directives/directives.module';
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
        UserGroupDialogComponent,
		UserGroupListComponent,
		UserGroupComponent,
		UserPermissionSelectorComponent,
        UserGroupListSideinfoComponent
	]
})

export class UserGroupModule {

}