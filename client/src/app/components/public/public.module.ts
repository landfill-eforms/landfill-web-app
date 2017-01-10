import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { LoginComponent } from './login/login.component';

@NgModule({
	imports: [
		CommonModule
	],
	providers: [],
	declarations: [
		LoginComponent,
	]
})

export class PublicModule {
	
}