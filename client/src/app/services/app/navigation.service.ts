import { NavigationToolbarComponent } from './../../components/navigation/navigation-toolbar/navigation-toolbar.component';
import { NavigationSideinfoComponent } from './../../components/navigation/navigation-sideinfo/navigation-sideinfo.component';
import { element } from 'protractor';
import { Router, ActivatedRouteSnapshot } from '@angular/router';
import { TitleService } from './title.service';
import { Injectable } from '@angular/core';

@Injectable()
export class NavigationService {

	private navbarComponent:NavigationToolbarComponent;
	private sideinfoComponent:NavigationSideinfoComponent;

	public isNavbarExpanded:boolean = false;

	public test:string = "HELLO!"

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
			this.titleService.setTitle(root.data['name']);
		}

		// Temporary.
		if (this.navbarComponent) {
			this.navbarComponent.title = this.titleService.getBaseTitle();
		}

	}

	isSideinfoDisabled():boolean {
		return this.getSideinfoComponent() && this.getSideinfoComponent().disabled;
	}

}