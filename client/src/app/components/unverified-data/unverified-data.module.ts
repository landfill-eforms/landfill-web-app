import { NgModule } from '@angular/core';
import { CommonModule } from './../common/common.module';
import { UnverifiedDataSetsComponent } from './unverified-data-sets/unverified-data-sets.component';

@NgModule({
    imports: [
        CommonModule
    ],
    providers: [],
    declarations: [
		UnverifiedDataSetsComponent
	]
})

export class UnverifiedDataModule {

}