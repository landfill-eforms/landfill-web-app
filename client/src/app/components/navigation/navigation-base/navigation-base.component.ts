import { NavigationService } from './../../../services/app/navigation.service';
import { Component } from '@angular/core';

@Component({
	selector: 'app-navigation-base',
	templateUrl: './navigation-base.component.html',
	styleUrls: ['./navigation-base.component.scss']
})
export class NavigationBaseComponent {

	constructor(private navigationService:NavigationService) {
		
	}

	isSideinfoDisabled():boolean {
		return this.navigationService.isSideinfoDisabled();
	}

}