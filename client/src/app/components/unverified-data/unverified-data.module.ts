import { UnverifiedDataSetListSideinfoComponent } from './unverified-data-set-list-sideinfo/unverified-data-set-list-sideinfo.component';
import { DirectivesModule } from './../directives/directives.module';
import { AssignImeNumberDialogComponent } from './assign-ime-number-dialog/assign-ime-number-dialog.component';
import { UnverifiedDataSetComponent } from './unverified-data-set/unverified-data-set.component';
import { NgModule } from '@angular/core';
import { CommonModule } from './../common/common.module';
import { UnverifiedDataSetsComponent } from './unverified-data-set-list/unverified-data-set-list.component';

@NgModule({
    imports: [
        CommonModule,
        DirectivesModule
    ],
    providers: [],
    declarations: [
		AssignImeNumberDialogComponent,
		UnverifiedDataSetsComponent,
		UnverifiedDataSetComponent,
        UnverifiedDataSetListSideinfoComponent
	]
})

export class UnverifiedDataModule {

}