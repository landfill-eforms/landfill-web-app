import { ModuleWithProviders } from '@angular/core';
import { UserPermission } from './model/server/persistence/enums/user/user-permission.enum';
import { AppConstant } from './app.constant';
import { NavigationBaseComponent } from './components/navigation/navigation-base/navigation-base.component';
import { RestrictedRoute } from './routes/restricted.route';
import { PublicRoute } from './routes/public.route';
import { StatusRoute } from './routes/status.route';
import { Routes, RouterModule } from '@angular/router';


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