import { ForbiddenComponent } from './../components/public/forbidden/forbidden.component';
import { ComingSoonComponent } from './../components/public/coming-soon/coming-soon.component';
import { Route } from '@angular/router';

/** Routes that are activated when an HTTP error status is received. */
export class StatusRoute {

	static readonly COMING_SOON:Route = {
		path: 'coming-soon',
		component: ComingSoonComponent
	};

	static readonly FORBIDDEN:Route = {
		path: 'forbidden',
		component: ForbiddenComponent
	};

}