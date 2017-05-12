import { UserPermission } from './../model/server/persistence/enums/user/user-permission.enum';
import { AuthGuard } from './../services/auth/authguard';
import { UserListComponent } from './../components/user/user-list/user-list.component';
import { DashboardComponent } from './../components/dashboard/dashboard.component';
import { ComingSoonComponent } from './../components/public/coming-soon/coming-soon.component';
import { Route } from '@angular/router';

/** 
 * Routes that can only be accessed by authorized users.
 * The components are not defined here, so that the routes can be accessed by components without getting cyclic dependency errors.
 */
export class RestrictedRoute {

	/***** HOME PAGE *****/
	static readonly DASHBOARD:Route = {
		path: 'dashboard'
	};

	/***** DATA SYNC ROUTES *****/
	static readonly MOBILE_SYNC:Route = {
		path: 'mobile',
		data: {
			name: "Android Data Sync",
			permissions: [
				UserPermission.UPLOAD_MOBILE_DATA,
				UserPermission.DOWNLOAD_MOBILE_DATA
			]
		}
	};

	static readonly MOBILE_UPLOAD:Route = {
		path: 'mobile/upload',
		data: {
			name: "Android Data Sync",
			permissions: [
				UserPermission.UPLOAD_MOBILE_DATA,
			]
		}
	};

	/***** EXCEEDANCE ROUTES *****/
	static readonly EXCEEDANCE_SELECTOR:Route = {
		path: 'exceedance',
		data: {
			name: "Exceedances"
		}
	};

	static readonly IME_NUMBER_LIST:Route = {
		path: 'exceedance/ime',
		data: {
			name: "IME Numbers",
			previous: RestrictedRoute.EXCEEDANCE_SELECTOR,
		}
	};

	static readonly IME_NUMBER:Route = {
		path: 'exceedance/ime/:imeNumber',
		data: {	
			previous: RestrictedRoute.IME_NUMBER_LIST,
		}
	};

	static readonly ISE_NUMBER_LIST:Route = {
		path: 'exceedance/ise',
		data: {
			name: "ISE Numbers",
			previous: RestrictedRoute.EXCEEDANCE_SELECTOR,
		}
	};

	static readonly ISE_NUMBER:Route = {
		path: 'exceedance/ise/:iseNumber',
		data: {	
			previous: RestrictedRoute.ISE_NUMBER_LIST,
		}
	};

	/***** INSTRUMENT ROUTES *****/
	static readonly INSTRUMENT_SELECTOR:Route = {
		path: 'equipment',
		data: {
			name: "Equipment",
			permissions: [
				UserPermission.VIEW_INSTRUMENTS,
				UserPermission.VIEW_INSTRUMENT_TYPES
			]
		}
	};

	static readonly INSTRUMENT_LIST:Route = {
		path: 'equipment/equipment',
		data: {
			name: "Equipment Inventory",
			previous: RestrictedRoute.INSTRUMENT_SELECTOR,
			permissions: [
				UserPermission.VIEW_INSTRUMENTS
			]
		}
	};

	static readonly INSTRUMENT_TYPE_LIST:Route = {
		path: 'equipment/equipment-types',
		data: {
			name: "Equipment Types",
			previous: RestrictedRoute.INSTRUMENT_SELECTOR,
			permissions: [
				UserPermission.VIEW_INSTRUMENT_TYPES
			]
		}
	};

	/***** REPORT ROUTES *****/
	static readonly REPORT_SELECTOR:Route = {
		path: 'report',
		data: {
			name: "Reports",
			permissions: [
				UserPermission.GENERATE_REPORTS
			]
		}
	};

	static readonly INSTANTANEOUS_REPORT:Route = {
		path: 'report/instantaneous',
		data: {
			name: "Instantaneous Report",
			previous: RestrictedRoute.REPORT_SELECTOR,
			permissions: [
				UserPermission.GENERATE_REPORTS
			]
		}
	};

	static readonly EXCEEDENCE_REPORT:Route = {
		path: 'report/exceedance',
		data: {
			name: "Exceedance Report",
			previous: RestrictedRoute.REPORT_SELECTOR,
			permissions: [
				UserPermission.GENERATE_REPORTS
			]
		}
	};

	/***** UNVERIFIED DATA ROUTES *****/
	static readonly UNVERIFIED_DATA_SET_LIST:Route = {
		path: 'unverified-data-sets',
		data: {
			name: "Unverified Data",
			permissions: [
				UserPermission.VIEW_UNVERIFIED_DATA_SETS
			]
		}
	}

	static readonly UNVERIFIED_DATA_SET:Route = {
		path: 'unverified-data-sets/:id',
		data: {
			name: "Unverified Data Set",
			previous: RestrictedRoute.UNVERIFIED_DATA_SET_LIST,
			permissions: [
				UserPermission.VIEW_UNVERIFIED_DATA_SET
			]
		}
	};

	/***** USER ROUTES *****/
	static readonly USER_SELECTOR:Route = {
		path: 'user',
		data: {
			name: "Users",
			permissions: [
				UserPermission.VIEW_USERS,
				UserPermission.VIEW_USER_GROUPS
			]
		}
	};

	static readonly USER_LIST:Route = {
		path: 'user/users',
		data: {
			name: "User Accounts",
			previous: RestrictedRoute.USER_SELECTOR,
			permissions: [
				UserPermission.VIEW_USERS
			]
		}
	};

	static readonly USER_GROUP_LIST:Route = {
		path: 'user/user-groups',
		data: {
			name: "User Groups",
			previous: RestrictedRoute.USER_SELECTOR,
			permissions: [
				UserPermission.VIEW_USER_GROUPS
			]
		}
	};

	/***** APPLICATION SETTINGS *****/
	static readonly APPLICATION_SETTINGS:Route = {
		path: 'settings',
		data: {
			name: "Application Settings",
			permissions: [
				UserPermission.ADMIN
			]
		}
	}

}