import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';

@NgModule({
	imports: [
		CommonModule
	],
	providers: [],
	declarations: [
		LoginComponent,
		ForbiddenComponent
	]
})

export class PublicModule {
	
}