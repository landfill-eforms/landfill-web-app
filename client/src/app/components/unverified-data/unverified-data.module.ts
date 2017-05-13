import { EditUnverifiedProbeDialogComponent } from './dialog/edit-unverified-probe-dialog/edit-unverified-probe-dialog.component';
import { EditUnverifiedIntegratedDialogComponent } from './dialog/edit-unverified-integrated-dialog/edit-unverified-integrated-dialog.component';
import { EditUnverifiedWarmspotDialogComponent } from './dialog/edit-unverified-warmspot-dialog/edit-unverified-warmspot-dialog.component';
import { EditUnverifiedInstantaneousDialogComponent } from './dialog/edit-unverified-instantaneous-dialog/edit-unverified-instantaneous-dialog.component';
import { UnverifiedDataSetListSideinfoComponent } from './unverified-data-set-list-sideinfo/unverified-data-set-list-sideinfo.component';
import { DirectivesModule } from './../directives/directives.module';
import { AssignImeNumberDialogComponent } from './assign-ime-number-dialog/assign-ime-number-dialog.component';
import { UnverifiedDataSetComponent } from './unverified-data-set/unverified-data-set.component';
import { NgModule } from '@angular/core';
import { CommonModule } from './../common/common.module';
import { UnverifiedDataSetListComponent } from './unverified-data-set-list/unverified-data-set-list.component';

@NgModule({
    imports: [
        CommonModule,
        DirectivesModule
    ],
    providers: [],
    declarations: [
		AssignImeNumberDialogComponent,
		UnverifiedDataSetListComponent,
		UnverifiedDataSetComponent,
        UnverifiedDataSetListSideinfoComponent,
        EditUnverifiedInstantaneousDialogComponent,
        EditUnverifiedWarmspotDialogComponent,
        EditUnverifiedIntegratedDialogComponent,
        EditUnverifiedProbeDialogComponent
	]
})

export class UnverifiedDataModule {

}