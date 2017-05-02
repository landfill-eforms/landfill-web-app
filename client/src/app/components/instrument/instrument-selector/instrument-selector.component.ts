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
	readonly subtitle = "Chris, please put something here."

    readonly cards:AppNavLink[] = [
		{
			title: "Equipment Inventory",
			subtitle: "Manage Equipment Inventory",
			img: "https://www.qdtricks.net/wp-content/uploads/2016/05/latest-1080-wallpaper.jpg",
			route: RestrictedRoute.INSTRUMENT_LIST
		},
		{
			title: "Equipment Types",
			subtitle: "Manage Equipment Makes and Models",
			img: "https://wallpaperscraft.com/image/sea_coast_rocks_underwater_world_vegetation_fish_53966_1920x1080.jpg",
			route: RestrictedRoute.INSTRUMENT_TYPE_LIST
		}
    ];

	constructor(private navigationService:NavigationService) {
		navigationService.getNavbarComponent().expanded = false;
		navigationService.getSideinfoComponent().disable();
	}

}