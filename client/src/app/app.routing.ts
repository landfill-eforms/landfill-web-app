import { InstrumentSelectorComponent } from './components/instrument/instrument-selector/instrument-selector.component';
import { UnverifiedDataSetComponent } from './components/unverified-data/unverified-data-set/unverified-data-set.component';
import { ReportSelectorComponent } from './components/report/report-selector/report-selector.component';
import { UnverifiedDataSetListComponent } from './components/unverified-data/unverified-data-set-list/unverified-data-set-list.component';
import { InstrumentTypeComponent } from './components/instrument/instrument-type/instrument-type.component';
import { InstrumentTypeListComponent } from './components/instrument/instrument-type-list/instrument-type-list.component';
import { InstrumentComponent } from './components/instrument/instrument/instrument.component';
import { InstrumentListComponent } from './components/instrument/instrument-list/instrument-list.component';
import { ImeNumberComponent } from './components/exceedance/ime-number/ime-number.component';
import { ExceedanceSelectorComponent } from './components/exceedance/exceedance-selector/exceedance-selector.component';
import { ImeNumberListComponent } from './components/exceedance/ime-number-list/ime-number-list.component';
import { UserGroupComponent } from './components/user-group/user-group/user-group.component';
import { UserGroupListComponent } from './components/user-group/user-group-list/user-group-list.component';
import { UserBaseComponent } from './components/user/user-base/user-base.component';
import { UserListComponent } from './components/user/user-list/user-list.component';
import { MobileUploadComponent } from './components/mobile/mobile-upload/mobile-upload.component';
import { MobileSyncSelectorComponent } from './components/mobile/mobile-sync-selector/mobile-sync-selector.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AuthGuard } from './services/auth/authguard';
import { ModuleWithProviders } from '@angular/core';
import { UserPermission } from './model/server/persistence/enums/user/user-permission.enum';
import { AppConstant } from './app.constant';
import { NavigationBaseComponent } from './components/navigation/navigation-base/navigation-base.component';
import { RestrictedRoute } from './routes/restricted.route';
import { PublicRoute } from './routes/public.route';
import { StatusRoute } from './routes/status.route';
import { Route, Routes, RouterModule } from '@angular/router';


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
	PublicRoute.INFO,
	...StatusRoutes
];

/** 
 * Routes that can only be accessed by authorized users.
 * We define the components (and canActivate) for the restricted routes here,
 * so that the restricted routes' paths and data can be used inside components.
 */
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
			{
				path: RestrictedRoute.DASHBOARD.path,
				component: DashboardComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.MOBILE_SYNC_SELECTOR.path,
				data: RestrictedRoute.MOBILE_SYNC_SELECTOR.data,
				component: MobileSyncSelectorComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.MOBILE_UPLOAD.path,
				data: RestrictedRoute.MOBILE_UPLOAD.data,
				component: MobileUploadComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.EXCEEDANCE_SELECTOR.path,
				data: RestrictedRoute.EXCEEDANCE_SELECTOR.data,
				component: ExceedanceSelectorComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.IME_NUMBER_LIST.path,
				data: RestrictedRoute.IME_NUMBER_LIST.data,
				component: ImeNumberListComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.IME_NUMBER.path,
				data: RestrictedRoute.IME_NUMBER.data,
				component: ImeNumberComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.INSTRUMENT_SELECTOR.path,
				data: RestrictedRoute.INSTRUMENT_SELECTOR.data,
				component: InstrumentSelectorComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.INSTRUMENT_LIST.path,
				data: RestrictedRoute.INSTRUMENT_LIST.data,
				component: InstrumentListComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.INSTRUMENT.path,
				data: RestrictedRoute.INSTRUMENT.data,
				component: InstrumentComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.INSTRUMENT_TYPE_LIST.path,
				data: RestrictedRoute.INSTRUMENT_TYPE_LIST.data,
				component: InstrumentTypeListComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.INSTRUMENT_TYPE.path,
				data: RestrictedRoute.INSTRUMENT_TYPE.data,
				component: InstrumentTypeComponent,
				canActivate: [AuthGuard]
			},

			{
				path: RestrictedRoute.REPORT_SELECTOR.path,
				data: RestrictedRoute.REPORT_SELECTOR.data,
				component: ReportSelectorComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.INSTANTANEOUS_REPORT.path,
				data: RestrictedRoute.INSTANTANEOUS_REPORT.data,
				component: DashboardComponent, // TODO Change
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.EXCEEDENCE_REPORT.path,
				data: RestrictedRoute.EXCEEDENCE_REPORT.data,
				component: DashboardComponent, // TODO Change
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.UNVERIFIED_DATA_SET_LIST.path,
				data: RestrictedRoute.UNVERIFIED_DATA_SET_LIST.data,
				component: UnverifiedDataSetListComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.UNVERIFIED_DATA_SET.path,
				data: RestrictedRoute.UNVERIFIED_DATA_SET.data,
				component: UnverifiedDataSetComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.USER_LIST.path,
				data: RestrictedRoute.USER_LIST.data,
				component: UserListComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.USER.path,
				data: RestrictedRoute.USER.data,
				component: UserBaseComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.USER_GROUP_LIST.path,
				data: RestrictedRoute.USER_GROUP_LIST.data,
				component: UserGroupListComponent,
				canActivate: [AuthGuard]
			},
			{
				path: RestrictedRoute.USER_GROUP.path,
				data: RestrictedRoute.USER_GROUP.data,
				component: UserGroupComponent,
				canActivate: [AuthGuard]
			},
			...StatusRoutes,
			PublicRoute.INFO
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