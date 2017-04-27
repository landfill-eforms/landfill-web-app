import { StatusRoute } from './routes/status.route';
import { PublicRoute } from './routes/public.route';
import { RestrictedRoute } from './routes/restricted.route';
import { AppConstant } from './app.constant';
import { ExceedanceSelectorComponent } from './components/exceedance/exceedance-selector/exceedance-selector.component';
import { MobileUploadComponent } from './components/mobile/mobile-upload/mobile-upload.component';
import { InstrumentTypeComponent } from './components/instrument/instrument-type/instrument-type.component';
import { InstrumentTypeListComponent } from './components/instrument/instrument-type-list/instrument-type-list.component';
import { InstrumentComponent } from './components/instrument/instrument/instrument.component';
import { InstrumentListComponent } from './components/instrument/instrument-list/instrument-list.component';
import { ImeReportComponent } from './components/report/ime-report/ime-report.component';
import { ImeNumberComponent } from './components/instantaneous/ime-number/ime-number.component';
import { ImeNumberListComponent } from './components/instantaneous/ime-number-list/ime-number-list.component';
import { ReportsComponent } from './components/report/report-selector/report-selector.component';
import { UnverifiedDataSetComponent } from './components/unverified-data/unverified-data-set/unverified-data-set.component';
import { UnverifiedDataSetsComponent } from './components/unverified-data/unverified-data-set-list/unverified-data-set-list.component';
import { InstantaneousReportComponent } from './components/report/instantaneous-report/instantaneous-report.component';
import { UserGroupComponent } from './components/user-group/user-group/user-group.component';
import { UserGroupListComponent } from './components/user-group/user-group-list/user-group-list.component';
import { Route, Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from "@angular/core";
import { AuthGuard } from "./services/auth/authguard";
import { UserPermission } from './model/server/persistence/enums/user/user-permission.enum';
import { LoginComponent } from './components/public/login/login.component';
import { ForbiddenComponent } from './components/public/forbidden/forbidden.component';
import { NavigationBaseComponent } from './components/navigation/navigation-base/navigation-base.component';
import { UserOverviewComponent } from './components/user/user-overview/user-overview.component';
import { UserProfileComponent } from './components/user/user-profile/user-profile.component';
import { UserBaseComponent } from './components/user/user-base/user-base.component';
import { UserListComponent } from './components/user/user-list/user-list.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ComingSoonComponent } from './components/public/coming-soon/coming-soon.component';

/** Routes that are activated when an HTTP error status is received. */
const StatusRoutes:Routes = [
	StatusRoute.FORBIDDEN,
	StatusRoute.COMING_SOON
];

/** Routes that are accessible without authorization. */
const PublicRoutes:Routes = [
	{
		path: '', 
		redirectTo: 'login',
		pathMatch: 'full',
	},
	PublicRoute.LOGIN,
	...StatusRoutes
];

/** Routes that can only be accessed by authorized users. */
export const RestrictedRoutes:Routes = [
	{
		path: AppConstant.RESTRICTED_ROUTE_BASE,
		component: NavigationBaseComponent,
		children: [
			{
				path: '',
				redirectTo: 'dashboard',
				pathMatch: 'full',
			},
			RestrictedRoute.DASHBOARD,
			RestrictedRoute.USER_LIST,
			RestrictedRoute.USER,
			RestrictedRoute.USER_GROUP_LIST,
			RestrictedRoute.USER_GROUP,
			RestrictedRoute.EXCEEDANCE_SELECTOR,
			RestrictedRoute.INSTRUMENT_LIST,
			RestrictedRoute.INSTRUMENT,
			RestrictedRoute.INSTRUMENT_TYPE_LIST,
			RestrictedRoute.INSTRUMENT_TYPE,
			RestrictedRoute.UNVERIFIED_DATA_SET_LIST,
			RestrictedRoute.UNVERIFIED_DATA_SET,
			RestrictedRoute.IME_NUMBER_LIST,
			RestrictedRoute.IME_NUMBER,
			RestrictedRoute.REPORTS,
			RestrictedRoute.INSTANTANEOUS_REPORT,
			RestrictedRoute.EXCEEDENCE_REPORT,
			RestrictedRoute.MOBILE_UPLOAD,
			...StatusRoutes,
		]
	}
];

export const AppRoutes:any[] = [
	...PublicRoutes,
	...RestrictedRoutes
];

export const AppRouterProviders:any[] = [
	UserPermission
]

export const Routing:ModuleWithProviders = RouterModule.forRoot(AppRoutes);