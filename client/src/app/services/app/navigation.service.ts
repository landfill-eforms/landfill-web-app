import { NavigationToolbarComponent } from './../../components/navigation/navigation-toolbar/navigation-toolbar.component';
import { NavigationSideinfoComponent } from './../../components/navigation/navigation-sideinfo/navigation-sideinfo.component';
import { element } from 'protractor';
import { Route, Router, ActivatedRouteSnapshot } from '@angular/router';
import { TitleService } from './title.service';
import { Injectable } from '@angular/core';

@Injectable()
export class NavigationService {

	private navbarComponent:NavigationToolbarComponent;
	private sideinfoComponent:NavigationSideinfoComponent;
	private navDrawerOpened:boolean = false;

	constructor(
		private titleService:TitleService, 
		private router:Router) {
			
		}

	getNavbarComponent():NavigationToolbarComponent {
		return this.navbarComponent;
	}

	setNavbarComponent(component:NavigationToolbarComponent) {
		console.log("Navbar component set.", component);
		this.navbarComponent = component;
	}

	getSideinfoComponent():NavigationSideinfoComponent {
		return this.sideinfoComponent;
	}

	setSideinfoComponent(component:NavigationSideinfoComponent) {
		console.log("Side info compnent set.", component);
		this.sideinfoComponent = component;
	}

	processRouteChange():void {

		// Get the root of the route hierarchy.
		let root:ActivatedRouteSnapshot = this.router.routerState.snapshot.root;
		console.log(root);

		// Find the last child in the route hierarchy.
		while (true) {
			if (!root.firstChild) {
				break;
			}
			root = root.firstChild;
		}

		// Set page title using the activated route's name, if it is defined.
		// If the name is not defined, the route's component probably has a dynamic name that it will set on init.
		if (root.data['name']) {
			this.titleService.setTitle(root.data.name);
		}

		// If the navbar exists for the current view.
		if (this.navbarComponent) {

			// Set title in the navbar.
			if (root.data['name']) {
				this.navbarComponent.title = root.data.name;
			}

			// Generate breadcrumb trail.
			let breadcrumb:Route[] = [];
			let previous:Route = root.data['previous'];
			while (previous) {
				breadcrumb.splice(0, 0, previous);
				previous = previous.data && previous.data['previous'];
			}
			this.navbarComponent.breadcrumb = breadcrumb;
			console.log("Breadcrumb", breadcrumb);

		}
		
	}

	isSideinfoDisabled():boolean {
		return this.getSideinfoComponent() && this.getSideinfoComponent().isDisabled();
	}

	isNavDrawerOpened():boolean {
		return this.navDrawerOpened;
	}

	updateNavDrawerStatus(status:boolean) {
		this.navDrawerOpened = status;
	}

}