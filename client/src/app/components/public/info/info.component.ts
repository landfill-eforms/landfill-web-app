import { NavigationService } from './../../../services/app/navigation.service';
import { TitleService } from './../../../services/app/title.service';
import { Component } from '@angular/core';

@Component({
	selector: 'app-info',
	templateUrl: './info.component.html',
	styleUrls: ['./info.component.scss']
})
export class InfoComponent {

	constructor(private titleSerice:TitleService, private navigationService:NavigationService) {
		titleSerice.setTitle("Information");
		if (navigationService.getNavbarComponent()) {
			navigationService.getNavbarComponent().expanded = false;
		}
	}

}