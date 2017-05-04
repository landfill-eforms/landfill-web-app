import { AppNavLink } from './../../../model/client/app-nav-link';
import { NavigationService } from './../../../services/app/navigation.service';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { Component } from '@angular/core';

@Component({
	selector: 'app-report-selector',
	templateUrl: './report-selector.component.html'
})
export class ReportSelectorComponent {

	readonly title = "Reports";
	readonly subtitle = "Select a type of report to generate."

    readonly cards:AppNavLink[] = [
		{
			title: "Exceedance",
			subtitle: "Preview and Generate an Exceedance Report",
			route: RestrictedRoute.INSTANTANEOUS_REPORT
		},
		{
			title: "Instantaneous",
			subtitle: "Preview and Generate an Instantaneous Report",
			route: RestrictedRoute.INSTANTANEOUS_REPORT
		},
		{
			title: "Integrated",
			subtitle: "Preview and Generate an Integrated Report",
			route: RestrictedRoute.INSTANTANEOUS_REPORT
		},
		{
			title: "Probe",
			subtitle: "Preview and Generate a Probe Report",
			route: RestrictedRoute.INSTANTANEOUS_REPORT
		}
    ];

	constructor(private navigationService:NavigationService) {
		navigationService.getNavbarComponent().expanded = false;
		navigationService.getSideinfoComponent().disable();
	}

}