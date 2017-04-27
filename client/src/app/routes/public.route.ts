import { LoginComponent } from './../components/public/login/login.component';
import { Route } from '@angular/router';

/** Routes that are accessible without authorization. */
export class PublicRoute {

	static readonly LOGIN:Route = {
		path: 'login',
		component: LoginComponent
	};

	// TODO Add information route...

}