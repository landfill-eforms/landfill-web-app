import { NavigationService } from './../../../services/app/navigation.service';
import { AuthService } from './../../../services/auth/auth.service';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { SelectorCard } from './../../../model/client/selector-card';
import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-instrument-selector',
	templateUrl: './instrument-selector.component.html'
})
export class InstrumentSelectorComponent implements OnInit {

    readonly cards:SelectorCard[] = [
		{
			title: "Equipment",
			subtitle: "Manage Equipment Inventory",
			img: "https://www.qdtricks.net/wp-content/uploads/2016/05/latest-1080-wallpaper.jpg",
			route: RestrictedRoute.INSTRUMENT_LIST,
			visible: false,
			disabled: false
		},
		{
			title: "Equipment Types",
			subtitle: "Equipment Makes, Models, and Types",
			img: "https://wallpaperscraft.com/image/sea_coast_rocks_underwater_world_vegetation_fish_53966_1920x1080.jpg",
			route: RestrictedRoute.INSTRUMENT_TYPE_LIST,
			visible: false,
			disabled: false
		}
    ];

    	constructor(
		private authService:AuthService,
		private navigationService:NavigationService) {
			navigationService.getNavbarComponent().expanded = false;
			navigationService.getSideinfoComponent().disable();
	}

	ngOnInit() {
		for (let card of this.cards) {
			card.visible = !card.route.data || this.authService.canAccess(card.route.data["permissions"]);
		}
	}

}