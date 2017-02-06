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
		UserGroupComponent
	]
})

export class UserGroupModule {
	
}