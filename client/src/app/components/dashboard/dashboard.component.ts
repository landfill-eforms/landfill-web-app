import { NavigationService } from './../../services/app/navigation.service';
import { AppConstant } from './../../app.constant';
import { TitleService } from './../../services/app/title.service';
import { Component } from '@angular/core';

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

	constructor(private titleService:TitleService, private navigationService:NavigationService) {
		titleService.setTitle("Dashboard");
		navigationService.getNavbarComponent().expanded = false;
	}
	
}