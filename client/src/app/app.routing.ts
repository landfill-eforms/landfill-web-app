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

// THIS IS A MESS RIGHT NOW

export const RestrictedRouteBase:string = 'app';

const UserRoutes:Routes = [
	{
		path: 'users',
		component: UserListComponent,
	},
	{
		path: 'user/:username',
		component: UserBaseComponent,
	},
	{
		path: 'new-user',
		component: UserProfileComponent
	}
];

/** Contains Routes that could be accessed within the web-app via links, buttons, etc. */
export class DefinedRoutes {

	// static readonly LOGIN:Route = {
	// 	path: 'login',
	// 	component: LoginComponent
	// }

	static readonly PLACEHOLDER:Route = {
		path: 'coming-soon',
		component: ComingSoonComponent,
	}

	static readonly DASHBOARD:Route = {
		path: 'dashboard',
		component: DashboardComponent,
	}

	///// USER ROUTES /////
	static readonly USER_LIST:Route = {
		path: 'users',
		component: UserListComponent,
		canActivate: [AuthGuard],
		data: {
			name: "Users",
			permissions: [
				UserPermission.VIEW_USERS
			]
		}
	}

	static readonly USER:Route = {
		path: 'users/:username',
		component: UserBaseComponent,
		canActivate: [AuthGuard],
		data: {
			permissions: [
				UserPermission.VIEW_USERS
			]
		}
	}

	static readonly NEW_USER:Route = {
		path: 'new-user',
		component: UserProfileComponent,
	}

	static readonly USER_GROUP_LIST:Route = {
		path: 'user-groups',
		component: UserGroupListComponent,
		canActivate: [AuthGuard],
		data: {
			name: "User Groups",
			permissions: [
				UserPermission.VIEW_USER_GROUPS
			]
		}
	}

	static readonly USER_GROUP:Route = {
		path: 'user-groups/:id',
		component: UserGroupComponent,
	}

	///// INSTRUMENT ROUTES /////
	static readonly INSTRUMENT_LIST:Route = {
		path: 'equipment',
		component: InstrumentListComponent,
		canActivate: [AuthGuard],
		data: {
			name: "Equipment",
			permissions: [
				UserPermission.VIEW_INSTRUMENTS
			]
		}
	}

	static readonly INSTRUMENT:Route = {
		path: 'equipment/:id',
		component: InstrumentComponent,
		canActivate: [AuthGuard],
		data: {
			permissions: [
				UserPermission.VIEW_INSTRUMENTS
			]
		}
	}

	static readonly INSTRUMENT_TYPE_LIST:Route = {
		path: 'equipment-types',
		component: InstrumentTypeListComponent,
		canActivate: [AuthGuard],
		data: {
			name: "Equipment Types",
			permissions: [
				UserPermission.VIEW_INSTRUMENT_TYPES
			]
		}
	}

	static readonly INSTRUMENT_TYPE:Route = {
		path: 'equipment-types/:id',
		component: InstrumentTypeComponent,
		canActivate: [AuthGuard],
		data: {
			permissions: [
				UserPermission.VIEW_INSTRUMENT_TYPES
			]
		}
	}

	///// UNVERIFIED DATA ROUTES /////
	static readonly UNVERIFIED_DATA_SET_LIST:Route = {
		path: 'unverified-data-sets',
		component: UnverifiedDataSetsComponent,
		data: {
			name: "Unverified Data",
			permissions: [
				UserPermission.VIEW_UNVERIFIED_DATA_SETS
			]
		}
	}

	static readonly UNVERIFIED_DATA_SET:Route = {
		path: 'unverified-data-sets/:id',
		component: UnverifiedDataSetComponent,
		data: {
			name: "Unverified Data",
			permissions: [
				UserPermission.VIEW_UNVERIFIED_DATA_SET
			]
		}
	}

	///// INSTANTANEOUS ROUTES /////
	static readonly IME_NUMBER_LIST:Route = {
		path: 'ime-number-list',
		component: ImeNumberListComponent
	}

	static readonly IME_NUMBER:Route = {
		path: 'ime-number/:imeNumber',
		component: ImeNumberComponent
	}

	///// REPORT ROUTES /////
	static readonly REPORTS:Route = {
		path: 'reports',
		component: ReportsComponent
	}

	static readonly INSTANTANEOUS_REPORT:Route = {
		path: 'instantaneous-report',
		component: InstantaneousReportComponent
	}

	static readonly EXCEEDENCE_REPORT:Route = {
		path: 'exceedance-report',
		component: ImeReportComponent
	}

	///// DATA SYNC ROUTES /////
	static readonly MOBILE_UPLOAD:Route = {
		path: 'mobile-upload',
		component: MobileUploadComponent
	}

}

/** Routes that are activated when an HTTP error status is received. */
const StatusRoutes:Routes = [
	{
		path: 'coming-soon',
		component: ComingSoonComponent
	},
	{
		path: 'forbidden',
		component: ForbiddenComponent
	},
];

/** Routes that are accessible without authorization. */
const PublicRoutes:Routes = [
	{
		path: '', 
		redirectTo: 'login',
		pathMatch: 'full',
	},
	{
		path: 'login',
		component: LoginComponent
	},
	...StatusRoutes
];

/** Routes that can only be accessed by authorized users. */
export const RestrictedRoutes:Routes = [
	{
		path: RestrictedRouteBase,
		component: NavigationBaseComponent,
		children: [
			{
				path: '',
				redirectTo: 'dashboard',
				pathMatch: 'full',
			},
			DefinedRoutes.DASHBOARD,
			DefinedRoutes.USER_LIST,
			DefinedRoutes.USER,
			DefinedRoutes.USER_GROUP_LIST,
			DefinedRoutes.USER_GROUP,
			DefinedRoutes.INSTRUMENT_LIST,
			DefinedRoutes.INSTRUMENT,
			DefinedRoutes.INSTRUMENT_TYPE_LIST,
			DefinedRoutes.INSTRUMENT_TYPE,
			DefinedRoutes.UNVERIFIED_DATA_SET_LIST,
			DefinedRoutes.UNVERIFIED_DATA_SET,
			DefinedRoutes.IME_NUMBER_LIST,
			DefinedRoutes.IME_NUMBER,
			DefinedRoutes.REPORTS,
			DefinedRoutes.INSTANTANEOUS_REPORT,
			DefinedRoutes.EXCEEDENCE_REPORT,
			DefinedRoutes.MOBILE_UPLOAD,
			...StatusRoutes,
			...UserRoutes,
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