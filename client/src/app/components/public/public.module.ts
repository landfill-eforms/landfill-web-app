import { InfoComponent } from './info/info.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { LoginComponent } from './login/login.component';
import { ComingSoonComponent } from './coming-soon/coming-soon.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';

@NgModule({
	imports: [
		CommonModule
	],
	providers: [],
	declarations: [
		LoginComponent,
		ComingSoonComponent,
		ForbiddenComponent,
		InfoComponent
	]
})

export class PublicModule {
	
}