import { NewUserDialogComponent } from './new-user-dialog/new-user-dialog.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { UserBaseComponent } from './user-base/user-base.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UsersComponent } from './users/users.component';
import { UserOverviewComponent } from './user-overview/user-overview.component';

@NgModule({
    imports: [
        CommonModule
    ],
    providers: [],
    declarations: [
		UsersComponent,
		UserBaseComponent,
        UserOverviewComponent,
		UserProfileComponent,
        NewUserDialogComponent
    ]
})

export class UserModule {

}