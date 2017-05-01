import { UserDialogComponent } from './dialog/user-dialog/user-dialog.component';
import { UserListSideinfoComponent } from './user-list-sideinfo/user-list-sideinfo.component';
import { DirectivesModule } from './../directives/directives.module';
import { UserGroupSelectorComponent } from './user-group-selector/user-group-selector.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { UserBaseComponent } from './user-base/user-base.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserOverviewComponent } from './user-overview/user-overview.component';

@NgModule({
    imports: [
        CommonModule,
        DirectivesModule
    ],
    providers: [],
    declarations: [
        UserDialogComponent,
		UserListComponent,
		UserBaseComponent,
        UserOverviewComponent,
		UserProfileComponent,
        UserGroupSelectorComponent,
        UserListSideinfoComponent
    ]
})

export class UserModule {

}