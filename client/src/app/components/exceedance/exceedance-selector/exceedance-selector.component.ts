import { AppNavLink } from './../../../model/client/app-nav-link';
import { NavigationService } from './../../../services/app/navigation.service';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { TitleService } from './../../../services/app/title.service';
import { Component } from '@angular/core';

@Component({
	selector: 'app-exceedance-selector',
	templateUrl: './exceedance-selector.component.html'
})
export class ExceedanceSelectorComponent {

	readonly title = "Exceedance Types";
	readonly subtitle = "Select an exceedance type to log repairs and rechecks, and to download PDF summaries."

	readonly cards:AppNavLink[] = [
		{
			title: "Instantaneous",
			subtitle: "Instantaneous Monitoring Exceedances",
			// img: "https://www.qdtricks.net/wp-content/uploads/2016/05/latest-1080-wallpaper.jpg",
			route: RestrictedRoute.IME_NUMBER_LIST,
			visible: false,
			disabled: false
		},
		{
			title: "Integrated",
			subtitle: "Integrated Sampling Exceedances",
			// img: "https://wallpaperscraft.com/image/sea_coast_rocks_underwater_world_vegetation_fish_53966_1920x1080.jpg",
			route: RestrictedRoute.ISE_NUMBER_LIST,
			visible: false,
			disabled: false
		},
		{
			title: "Probe",
			subtitle: "Probe Exceedances",
			// img: "https://wallpaperscraft.com/image/stars_sky_shore_84534_1920x1080.jpg",
			route: RestrictedRoute.IME_NUMBER_LIST,
			visible: false,
			disabled: true
		},
		{
			title: "Component",
			subtitle: "Coming Soon",
			// img: "https://ak2.picdn.net/shutterstock/videos/3125215/thumb/1.jpg",
			route: RestrictedRoute.IME_NUMBER_LIST,
			visible: false,
			disabled: true
		}
	];

	constructor(private navigationService:NavigationService) {
		navigationService.getNavbarComponent().expanded = false;
		navigationService.getSideinfoComponent().disable();
	}

}