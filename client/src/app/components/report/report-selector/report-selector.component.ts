import { StatusRoute } from './../../../routes/status.route';
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
			edgeColor: "#66BB6A",
			route: RestrictedRoute.EXCEEDENCE_REPORT,
			disabled: false
		},
		{
			title: "Instantaneous",
			subtitle: "Preview and Generate an Instantaneous Report",
			edgeColor: "#66BB6A",
			route: RestrictedRoute.INSTANTANEOUS_REPORT,
			disabled: false
		},
		{
			title: "Integrated",
			subtitle: "Preview and Generate an Integrated Report",
			edgeColor: "#66BB6A",
			route: RestrictedRoute.INTEGRATED_REPORT,
			disabled: false
		},
		{
			title: "Warmspot",
			subtitle: "Preview and Generate a Warmspot Report",
			edgeColor: "#66BB6A",
			route: RestrictedRoute.WARMSPOT_REPORT,
			disabled: false
		},
		{
			title: "Probe",
			subtitle: "(Coming Soon) Preview and Generate a Probe Report",
			edgeColor: "#66BB6A",
			route: StatusRoute.COMING_SOON,
			disabled: true
		},
		{
			title: "Scheduled",
			subtitle: "Set-up Automated Reports",
			edgeColor: "#66BB6A",
			route: RestrictedRoute.SCHEDULED_REPORT_LIST,
			disabled: false
		}
    ];

	constructor(private navigationService:NavigationService) {
		navigationService.getNavbarComponent().expanded = false;
		navigationService.getSideinfoComponent().disable();
	}

}