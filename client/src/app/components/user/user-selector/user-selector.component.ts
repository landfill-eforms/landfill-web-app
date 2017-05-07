import { AppNavLink } from './../../../model/client/app-nav-link';
import { NavigationService } from './../../../services/app/navigation.service';
import { RestrictedRoute } from './../../../routes/restricted.route';
import { Component } from '@angular/core';

@Component({
	selector: 'app-user-selector',
	templateUrl: './user-selector.component.html'
})
export class UserSelectorComponent {

	readonly title = "Users";
	readonly subtitle = "Manage user accounts and user groups. User access and permissions can be configured through user groups."

    readonly cards:AppNavLink[] = [
		{
			title: "User Accounts",
			subtitle: "Manage User Accounts",
			// img: "https://www.qdtricks.net/wp-content/uploads/2016/05/latest-1080-wallpaper.jpg",
			route: RestrictedRoute.USER_LIST
		},
		{
			title: "User Groups",
			subtitle: "Manage User Groups",
			// img: "https://wallpaperscraft.com/image/sea_coast_rocks_underwater_world_vegetation_fish_53966_1920x1080.jpg",
			route: RestrictedRoute.USER_GROUP_LIST
		}
    ];

	constructor(private navigationService:NavigationService) {
		navigationService.getNavbarComponent().expanded = false;
		navigationService.getSideinfoComponent().disable();
	}

}