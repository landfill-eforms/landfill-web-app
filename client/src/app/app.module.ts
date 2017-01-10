import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '@angular/material'
import { AppRoutes, Routing, AppRouterProviders } from './app.routing';
import { AppComponent } from './app.component';
import { AuthGuard } from './services/auth/authguard';
import { AuthService } from './services/auth/auth.service';

import { PublicModule } from './components/public/public.module'
import { NavigationModule } from './components/navigation/navigation.module'
import { TestModule } from './components/test/test.module';

import { InstantaneousDataService } from './services/instantaneous-data.service';
import { SitesService } from './services/sites.service';

@NgModule({
	declarations: [
		AppComponent
	],
	imports: [
		// BrowserModule,
		// FormsModule,
		// HttpModule,
		// RouterModule,
		//MaterialModule.forRoot(),
		//AppRoutes,
		Routing,
		PublicModule,
		NavigationModule,
		TestModule,
	],
	providers: [
		//AppRouterProviders,
		AuthGuard,
		AuthService,
		InstantaneousDataService,
		SitesService,
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
