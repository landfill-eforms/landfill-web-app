import { UserStatusDialogComponent } from './dialog/user-status-dialog/user-status-dialog.component';
import { UserPasswordDialogComponent } from './dialog/user-password-dialog/user-password-dialog.component';
import { UserGroupListSideinfoComponent } from './user-group-list-sideinfo/user-group-list-sideinfo.component';
import { UserSelectorComponent } from './user-selector/user-selector.component';
import { UserGroupListComponent } from './user-group-list/user-group-list.component';
import { UserGroupDialogComponent } from './dialog/user-group-dialog/user-group-dialog.component';
import { UserDialogComponent } from './dialog/user-dialog/user-dialog.component';
import { UserListSideinfoComponent } from './user-list-sideinfo/user-list-sideinfo.component';
import { DirectivesModule } from './../directives/directives.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { UserListComponent } from './user-list/user-list.component';

@NgModule({
    imports: [
        CommonModule,
        DirectivesModule
    ],
    providers: [],
    declarations: [
        UserSelectorComponent,
        UserDialogComponent,
        UserPasswordDialogComponent,
        UserStatusDialogComponent,
		UserListComponent,
        UserListSideinfoComponent,
        UserGroupDialogComponent,
		UserGroupListComponent,
        UserGroupListSideinfoComponent
    ]
})

export class UserModule {

}