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

	fabActionSource:BehaviorSubject<any> = new BehaviorSubject({});
	fabInfo:FabInfo;

	expanded:boolean = false;

	title:string;

	readonly logoUrl:string = environment.assetsUrl + "/images/la-san-logo-lite-bright.png";

	constructor (
		private router:Router,
		private route:ActivatedRoute,
		private navigationService:NavigationService) {
			navigationService.setNavbarComponent(this);
	}

	ngOnInit() {

	}

	setFabInfo(info:FabInfo) {
		this.fabInfo = info;
	}

	resetFabInfo() {
		this.fabInfo = null;
	}

	fabAction(event:any) {
		this.fabActionSource.next(event);
	}

	action() {
		this.navdrawer.toggle();
	}
	
}

export class FabInfo {
	icon:string;
	tooltip:string;
}