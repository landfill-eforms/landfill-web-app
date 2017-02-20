import { AssignIMENumberDialogComponent } from './assign-ime-number-dialog/assign-ime-number-dialog.component';
import { UnverifiedDataSetComponent } from './unverified-data-set/unverified-data-set.component';
import { NgModule } from '@angular/core';
import { CommonModule } from './../common/common.module';
import { UnverifiedDataSetsComponent } from './unverified-data-set-list/unverified-data-set-list.component';

@NgModule({
    imports: [
        CommonModule
    ],
    providers: [],
    declarations: [
		AssignIMENumberDialogComponent,
		UnverifiedDataSetsComponent,
		UnverifiedDataSetComponent
	]
})

export class UnverifiedDataModule {

}