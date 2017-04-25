import { TitleService } from './../../../services/app/title.service';
import { NavigationService } from './../../../services/app/navigation.service';
import { AuthService } from './../../../services/auth/auth.service';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { environment } from './../../../../environments/environment';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Component({
	selector: 'app-navigation-toolbar',
	templateUrl: './navigation-toolbar.component.html',
	styleUrls: ['./navigation-toolbar.component.scss']
})
export class NavigationToolbarComponent implements OnInit { 

	@Input() navdrawer;

	private fabActionSource:BehaviorSubject<any> = new BehaviorSubject({});
	fabInfo:FabInfo;

	expanded:boolean = false;

	title:string;

	readonly logoUrl:string = environment.assetsUrl + "/images/la-san-logo-lite-bright.png";

	constructor (
		private authService:AuthService,
		private router:Router,
		private route:ActivatedRoute,
		private navigationService:NavigationService) {
			navigationService.setNavbarComponent(this);
	}

	ngOnInit() {

	}

	getFabActionSource():BehaviorSubject<any> {
		return this.fabActionSource;
	}

	// Does this cause a memory leak?
	resetFabActionSource() {
		this.fabActionSource.complete();
		this.fabActionSource = new BehaviorSubject({});
	}

	setFabInfo(info:FabInfo) {
		this.fabInfo = info;
	}

	resetFabInfo() {
		this.fabInfo = null;
	}

	fabAction(event:any) {
		this.fabActionSource.next(event);
		// this.fabActionSource.complete();
	}

	action() {
		this.navdrawer.toggle();
	}

	logout() {
		this.authService.logout();
	}
	
}

export class FabInfo {
	icon:string;
	tooltip:string;
}