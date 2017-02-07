import { InstantaneousReportComponent } from './components/report/instantaneous-report/instantaneous-report.component';
import { InstantaneousData } from './model/server/persistence/entity/instantaneous-data.class';
import { UserGroupComponent } from './components/user-group/user-group/user-group.component';
import { UserGroupsComponent } from './components/user-group/user-groups/user-groups.component';
import { Route, Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from "@angular/core";
import { AuthGuard } from "./services/auth/authguard";
import { UserRole } from './model/server/model/user-role.enum';
import { LoginComponent } from './components/public/login/login.component';
import { ForbiddenComponent } from './components/public/forbidden/forbidden.component';
import { NavigationBaseComponent } from './components/navigation/navigation-base/navigation-base.component';
import { InstantaneousTestComponent } from './components/test/instantaneous-test/instantaneous-test.component';
import { InstantaneousReportTestComponent } from './components/test/instantaneous-report-test/instantaneous-report-test.component';
import { InstantaneousUploadTestComponent } from './components/test/instantaneous-upload-test/instantaneous-upload-test.component';
import { UserOverviewComponent } from './components/user/user-overview/user-overview.component';
import { UserProfileComponent } from './components/user/user-profile/user-profile.component';
import { UserBaseComponent } from './components/user/user-base/user-base.component';
import { UsersComponent } from './components/user/users/users.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ComingSoonComponent } from './components/public/coming-soon/coming-soon.component';

export const RestrictedRouteBase:string = 'app';

const UserRoutes:Routes = [
	{
		path: 'users',
		component: UsersComponent,
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

const UserGroupsRoute:Route = {
	path: 'user-groups',
	component: UserGroupsComponent
}

const UserGroupRoute:Route = {
	path: 'user-group/:id',
	component: UserGroupComponent
}

const InstantaneousReportRoute:Route = {
	path: 'instantaneous-report',
	component: InstantaneousReportComponent
}

const TestRoutes:Routes = [
	{
		path: 'instantaneous_test',
		component: InstantaneousTestComponent,
		canActivate: [AuthGuard],
		data: {roles: [
			UserRole["SUPER_ADMIN"],
		]}
	},
	{
		path: 'instantaneous_upload',
		component: InstantaneousUploadTestComponent,
		canActivate: [AuthGuard],
		data: {roles: [
			UserRole["SUPER_ADMIN"],
		]}
	},

];

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
			{
				path: 'dashboard',
				component: DashboardComponent,
			},
			...StatusRoutes,
			...TestRoutes,
			...UserRoutes,
			UserGroupsRoute,
			UserGroupRoute,
			InstantaneousReportRoute,
		]
	}
];

export const AppRoutes:any[] = [
	...PublicRoutes,
	...RestrictedRoutes
];

export const AppRouterProviders:any[] = [

]

export const Routing:ModuleWithProviders = RouterModule.forRoot(AppRoutes);