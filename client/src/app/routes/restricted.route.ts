import { IseNumberListComponent } from './../components/exceedance/ise-number-list/ise-number-list.component';
import { ImeNumberComponent } from './../components/exceedance/ime-number/ime-number.component';
import { MobileUploadComponent } from './../components/mobile/mobile-upload/mobile-upload.component';
import { ImeReportComponent } from './../components/report/ime-report/ime-report.component';
import { InstantaneousReportComponent } from './../components/report/instantaneous-report/instantaneous-report.component';
import { ReportsComponent } from './../components/report/report-selector/report-selector.component';
import { ImeNumberListComponent } from './../components/exceedance/ime-number-list/ime-number-list.component';
import { UnverifiedDataSetComponent } from './../components/unverified-data/unverified-data-set/unverified-data-set.component';
import { UnverifiedDataSetsComponent } from './../components/unverified-data/unverified-data-set-list/unverified-data-set-list.component';
import { InstrumentTypeComponent } from './../components/instrument/instrument-type/instrument-type.component';
import { InstrumentTypeListComponent } from './../components/instrument/instrument-type-list/instrument-type-list.component';
import { InstrumentComponent } from './../components/instrument/instrument/instrument.component';
import { InstrumentListComponent } from './../components/instrument/instrument-list/instrument-list.component';
import { ExceedanceSelectorComponent } from './../components/exceedance/exceedance-selector/exceedance-selector.component';
import { UserGroupComponent } from './../components/user-group/user-group/user-group.component';
import { UserGroupListComponent } from './../components/user-group/user-group-list/user-group-list.component';
import { UserProfileComponent } from './../components/user/user-profile/user-profile.component';
import { UserBaseComponent } from './../components/user/user-base/user-base.component';
import { UserPermission } from './../model/server/persistence/enums/user/user-permission.enum';
import { AuthGuard } from './../services/auth/authguard';
import { UserListComponent } from './../components/user/user-list/user-list.component';
import { DashboardComponent } from './../components/dashboard/dashboard.component';
import { ComingSoonComponent } from './../components/public/coming-soon/coming-soon.component';
import { Route } from '@angular/router';

/** Routes that can only be accessed by authorized users. */
export class RestrictedRoute {

	/***** HOME PAGE *****/
	static readonly DASHBOARD:Route = {
		path: 'dashboard',
		component: DashboardComponent,
	};

	/***** DATA SYNC ROUTES *****/
	static readonly MOBILE_UPLOAD:Route = {
		path: 'mobile-upload',
		component: MobileUploadComponent
	};

	/***** EXCEEDANCE ROUTES *****/
	static readonly EXCEEDANCE_SELECTOR:Route = {
		path: 'exceedance',
		component: ExceedanceSelectorComponent,
	};

	static readonly IME_NUMBER_LIST:Route = {
		path: 'exceedance/ime',
		component: ImeNumberListComponent,
		data: {
			name: "IME Number List",
		}
	}

	static readonly IME_NUMBER:Route = {
		path: 'exceedance/ime/:imeNumber',
		component: ImeNumberComponent
	}

	static readonly ISE_NUMBER_LIST:Route = {
		path: 'exceedance/ise',
		component: IseNumberListComponent,
		data: {
			name: "ISE Number List",
		}
	}

	/***** INSTRUMENT ROUTES *****/
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
	};

	static readonly INSTRUMENT:Route = {
		path: 'equipment/:id',
		component: InstrumentComponent,
		canActivate: [AuthGuard],
		data: {
			permissions: [
				UserPermission.VIEW_INSTRUMENTS
			]
		}
	};

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
	};

	static readonly INSTRUMENT_TYPE:Route = {
		path: 'equipment-types/:id',
		component: InstrumentTypeComponent,
		canActivate: [AuthGuard],
		data: {
			permissions: [
				UserPermission.VIEW_INSTRUMENT_TYPES
			]
		}
	};

	/***** REPORT ROUTES *****/
	static readonly REPORTS:Route = {
		path: 'reports',
		component: ReportsComponent
	};

	static readonly INSTANTANEOUS_REPORT:Route = {
		path: 'instantaneous-report',
		component: InstantaneousReportComponent
	}

	static readonly EXCEEDENCE_REPORT:Route = {
		path: 'exceedance-report',
		component: ImeReportComponent
	};

	/***** UNVERIFIED DATA ROUTES *****/
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
	};

	/***** USER ROUTES *****/
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
	};

	static readonly NEW_USER:Route = {
		path: 'new-user',
		component: UserProfileComponent,
	};

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
	};

	static readonly USER_GROUP:Route = {
		path: 'user-groups/:id',
		component: UserGroupComponent,
	};
	
}