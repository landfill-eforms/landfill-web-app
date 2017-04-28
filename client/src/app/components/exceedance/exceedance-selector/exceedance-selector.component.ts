import { NavigationService } from './../../../services/app/navigation.service';
import { SelectorCard } from './../../../model/client/selector-card';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { TitleService } from './../../../services/app/title.service';
import { AuthService } from './../../../services/auth/auth.service';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-exceedance-selector',
	templateUrl: './exceedance-selector.component.html'
})
export class ExceedanceSelectorComponent implements OnInit {

	readonly links:SelectorCard[] = [
		{
			title: "Instantaneous",
			subtitle: "Instantaneous Monitoring Exceedances",
			img: "https://www.qdtricks.net/wp-content/uploads/2016/05/latest-1080-wallpaper.jpg",
			route: RestrictedRoute.IME_NUMBER_LIST,
			visible: false,
			disabled: false
		},
		{
			title: "Integrated",
			subtitle: "Integrated Something Exceedances",
			img: "https://wallpaperscraft.com/image/sea_coast_rocks_underwater_world_vegetation_fish_53966_1920x1080.jpg",
			route: RestrictedRoute.IME_NUMBER_LIST,
			visible: false,
			disabled: false
		},
		{
			title: "Probe",
			subtitle: "Perimeter Probe Exceedances",
			img: "https://wallpaperscraft.com/image/stars_sky_shore_84534_1920x1080.jpg",
			route: RestrictedRoute.IME_NUMBER_LIST,
			visible: false,
			disabled: false
		},
		{
			title: "Component",
			subtitle: "Coming Soon",
			img: "https://ak2.picdn.net/shutterstock/videos/3125215/thumb/1.jpg",
			route: RestrictedRoute.IME_NUMBER_LIST,
			visible: false,
			disabled: true
		}
	];

	constructor(
		private titleService:TitleService,
		private authService:AuthService,
		private navigationService:NavigationService) {
			titleService.setTitle("Exceedances");
			navigationService.getNavbarComponent().expanded = false;
	}


	ngOnInit() {
		for (let link of this.links) {
			link.visible = !link.route.data || this.authService.canAccess(link.route.data["permissions"]);
		}
	}

}