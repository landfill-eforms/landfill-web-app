import { TitleService } from './../../services/app/title.service';
import { Component } from '@angular/core';

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

	constructor(private titleService:TitleService) {
		titleService.setTitle("Dashboard");
	}
	
}