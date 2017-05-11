import { StatusRoute } from './../../../routes/status.route';
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
			edgeColor: "#F44336",
			route: RestrictedRoute.IME_NUMBER_LIST,
			disabled: false
		},
		{
			title: "Integrated",
			subtitle: "Integrated Sampling Exceedances",
			edgeColor: "#F44336",
			route: RestrictedRoute.ISE_NUMBER_LIST,
			disabled: false
		},
		{
			title: "Probe",
			subtitle: "(Coming Soon) Probe Exceedances",
			edgeColor: "#F44336",
			route: StatusRoute.COMING_SOON,
			disabled: true
		},
		{
			title: "Component",
			subtitle: "(Coming Soon) Component Leaks",
			edgeColor: "#F44336",
			route: StatusRoute.COMING_SOON,
			disabled: true
		}
	];

	constructor(private navigationService:NavigationService) {
		navigationService.getNavbarComponent().expanded = false;
		navigationService.getSideinfoComponent().disable();
	}

}