import { MobileSyncComponent } from './mobile-sync/mobile-sync.component';
import { DirectivesModule } from './../directives/directives.module';
import { NgModule } from '@angular/core';
import { CommonModule } from './../common/common.module';

@NgModule({
	imports: [
		CommonModule,
        DirectivesModule
    ],
	providers: [],
	declarations: [
		MobileSyncComponent
    ]
})

export class MobileModule {

}