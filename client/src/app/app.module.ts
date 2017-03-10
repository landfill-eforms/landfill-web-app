import { NewInstrumentDialogComponent } from './components/instrument/dialog/new-instrument-dialog/new-instrument-dialog.component';
import { NewInstrumentTypeDialogComponent } from './components/instrument/dialog/new-instrument-type-dialog/new-instrument-type-dialog.component';
import { InstrumentTypeService } from './services/instrument/instrument-type.service';
import { InstrumentModule } from './components/instrument/instrument.module';
import { UnverifiedDataService } from './services/unverified/unverified-data-set.service';
import { UserService } from './services/user/user.service';
import { UserGroupService } from './services/user/user-group.service';
import { SitesService } from './services/monitoring-point/site.service';
import { InstrumentService } from './services/instrument/instrument.service';
import { InstantaneousDataService } from './services/instantaneous/instantaneous-data.service';
import { IMENumberService } from './services/instantaneous/ime-number.service';
import { IMEDataService } from './services/instantaneous/ime-data.service';
import { IMERepairDialogComponent } from './components/directives/dialog/ime-repair-dialog/ime-repair-dialog.component';
import { IMERecheckDialogComponent } from './components/directives/dialog/ime-recheck-dialog/ime-recheck-dialog.component';
import { CommonModule } from './components/common/common.module';
import { AssignIMENumberDialogComponent } from './components/unverified-data/assign-ime-number-dialog/assign-ime-number-dialog.component';
import { UnverifiedDataModule } from './components/unverified-data/unverified-data.module';
import { InstantaneousModule } from './components/instantaneous/instantaneous.module';
import { NewUserGroupDialogComponent } from './components/user-group/new-user-group-dialog/new-user-group-dialog.component';
import { ReportModule } from './components/report/report.module';
import { NewUserDialogComponent } from './components/user/new-user-dialog/new-user-dialog.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '@angular/material'
import { AppRoutes, Routing, AppRouterProviders } from './app.routing';
import { AppComponent } from './app.component';
import { AuthGuard } from './services/auth/authguard';
import { AuthService, AuthProvider } from './services/auth/auth.service';
import { AuthHttp, AuthConfig, AUTH_PROVIDERS, provideAuth } from 'angular2-jwt';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PublicModule } from './components/public/public.module'
import { NavigationModule } from './components/navigation/navigation.module'
import { DirectivesModule } from './components/directives/directives.module';
import { UserModule } from './components/user/user.module';
import { UserGroupModule } from './components/user-group/user-group.module';
import { TestModule } from './components/test/test.module';
import { FileUploadService } from './services/file/file-upload.service';

@NgModule({
	declarations: [
		AppComponent,
		DashboardComponent
	],
	imports: [
		// BrowserModule,
		// FormsModule,
		// HttpModule,
		// RouterModule,
		//MaterialModule.forRoot(),
		//AppRoutes,
		CommonModule,
		Routing,
		PublicModule,
		NavigationModule,
		DirectivesModule,
		InstantaneousModule,
		InstrumentModule,
		UserModule,
		UserGroupModule,
		UnverifiedDataModule,
		ReportModule,
		TestModule,
	],
	entryComponents: [
		NewUserDialogComponent,
		NewUserGroupDialogComponent,
		AssignIMENumberDialogComponent,
		IMERecheckDialogComponent,
		IMERepairDialogComponent,
		NewInstrumentDialogComponent,
		NewInstrumentTypeDialogComponent,
	],
	providers: [
		//AppRouterProviders,
		AuthHttp,
		AuthProvider,
		AuthGuard,
		AuthService,
		FileUploadService,
		IMEDataService,
		IMENumberService,
		InstantaneousDataService,
		InstrumentService,
		InstrumentTypeService,
		SitesService,
		UnverifiedDataService,
		UserGroupService,
		UserService
	],
	bootstrap: [AppComponent]
})

export class AppModule {

}
