import { AppNavLink } from './../../../model/client/app-nav-link';
import { NavigationService } from './../../../services/app/navigation.service';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { Component } from '@angular/core';

@Component({
	selector: 'app-instrument-selector',
	templateUrl: './instrument-selector.component.html'
})
export class InstrumentSelectorComponent {

	readonly title = "Equipment";
	readonly subtitle = "Manage the database of individual equipment, as well as the particular makes and models of equipment."

    readonly cards:AppNavLink[] = [
		{
			title: "Equipment Inventory",
			subtitle: "Manage Equipment Inventory",
			edgeColor: "#7E57C2",
			route: RestrictedRoute.INSTRUMENT_LIST
		},
		{
			title: "Equipment Types",
			subtitle: "Manage Equipment Makes and Models",
			edgeColor: "#7E57C2",
			route: RestrictedRoute.INSTRUMENT_TYPE_LIST
		}
    ];

	constructor(private navigationService:NavigationService) {
		navigationService.getNavbarComponent().expanded = false;
		navigationService.getSideinfoComponent().disable();
	}

}