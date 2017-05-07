import { InfoComponent } from './../components/public/info/info.component';
import { LoginComponent } from './../components/public/login/login.component';
import { Route } from '@angular/router';

/** Routes that are accessible without authorization. */
export class PublicRoute {

	static readonly LOGIN:Route = {
		path: 'login',
		component: LoginComponent
	};

	static readonly INFO:Route = {
		path: 'info',
		component: InfoComponent,
		data: {
			name: "About"
		}
	}

}