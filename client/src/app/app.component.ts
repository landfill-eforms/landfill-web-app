import { Router, NavigationEnd } from '@angular/router';
import { NavigationService } from './services/app/navigation.service';
import { Component } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import 'rxjs/Rx';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent {

	constructor (private router:Router, private navigationService:NavigationService) {
		router.events.filter((event: any) => event instanceof NavigationEnd).subscribe(() => {
			this.navigationService.processRouteChange();
		});
	}

}
