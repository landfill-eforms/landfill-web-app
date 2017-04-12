import { UserListSideinfoComponent } from './../../user/user-list-sideinfo/user-list-sideinfo.component';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-sideinfo.component';
import { MdSidenav } from '@angular/material';
import { NavigationService } from './../../../services/app/navigation.service';
import { Component, OnInit, Input, ElementRef, ViewChild, ViewContainerRef, ComponentFactoryResolver, ReflectiveInjector, ComponentRef } from '@angular/core';

@Component({
	selector: 'app-navigation-sideinfo',
	templateUrl: './navigation-sideinfo.component.html',
	styleUrls: ['./navigation-sideinfo.component.scss'],
	entryComponents: [UserListSideinfoComponent]
})
export class NavigationSideinfoComponent implements OnInit {

	@ViewChild('directive', {read: ViewContainerRef}) directiveRef:ViewContainerRef;
	
	@Input() sidenav:MdSidenav;

	private isOpened:boolean;
	private currentDirective:ComponentRef<AbstractSideinfoComponent>;
	
	public ready:boolean = false;
	public disabled:boolean = false;
	public title:string;
	public subtitle:string;

	constructor(private navigationService:NavigationService, private resolver: ComponentFactoryResolver) {}

	ngOnInit() {
		this.navigationService.setSideinfoComponent(this);
		this.forceClose(); // Start with info sidenav closed.
		this.ready = true;
	}

	getDirective():AbstractSideinfoComponent {
		return this.currentDirective.instance;
	}

	setDirective(directiveType, fields:any) {
		// Reference: http://blog.rangle.io/dynamically-creating-components-with-angular-2/
		let inputProviders = Object.keys(fields).map((key) => {
			return {
				provide: key, 
				useValue: fields[key]
			};
		});
    	let resolvedInputs = ReflectiveInjector.resolve(inputProviders);
		let injector = ReflectiveInjector.fromResolvedProviders(resolvedInputs, this.directiveRef.parentInjector);
		let factory = this.resolver.resolveComponentFactory<AbstractSideinfoComponent>(directiveType);
		let component = factory.create(injector);
		this.directiveRef.clear();
		this.directiveRef.insert(component.hostView);
		if (this.currentDirective) {
			this.currentDirective.destroy();
		}
		this.currentDirective = component;
	}

	close() {
		this.sidenav.close();
		this.isOpened = false;
	}

	open() {
		this.disabled = false;
		this.sidenav.open();
		this.isOpened = true;
	}

	forceClose() {
		this.disabled = true;
		this.close();
		this.currentDirective = null;
		this.ready = false;
	}

	test() {
		return this.navigationService.test;
	}

	isOpen():boolean {
		return this.isOpened;
	}

}