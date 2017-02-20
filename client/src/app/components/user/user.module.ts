import { UserGroupSelectorComponent } from './user-group-selector/user-group-selector.component';
import { NewUserDialogComponent } from './new-user-dialog/new-user-dialog.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { UserBaseComponent } from './user-base/user-base.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserOverviewComponent } from './user-overview/user-overview.component';

@NgModule({
    imports: [
        CommonModule
    ],
    providers: [],
    declarations: [
		UserListComponent,
		UserBaseComponent,
        UserOverviewComponent,
		UserProfileComponent,
        UserGroupSelectorComponent,
        NewUserDialogComponent
    ]
})

export class UserModule {

}