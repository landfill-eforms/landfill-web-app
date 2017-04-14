import { InstrumentListSideinfoComponent } from './../../instrument/instrument-list-sideinfo/instrument-list-sideinfo.component';
import { InstrumentTypeListSideinfoComponent } from './../../instrument/instrument-type-list-sideinfo/instrument-type-list-sideinfo.component';
import { UserGroupListSideinfoComponent } from './../../user-group/user-group-list-sideinfo/user-group-list-sideinfo.component';
import { UserListSideinfoComponent } from './../../user/user-list-sideinfo/user-list-sideinfo.component';
import { AbstractSideinfoComponent } from './../../../model/client/abstract-sideinfo.component';
import { MdSidenav } from '@angular/material';
import { NavigationService } from './../../../services/app/navigation.service';
import { Component, OnInit, Input, ElementRef, ViewChild, ViewContainerRef, ComponentFactoryResolver, ReflectiveInjector, ComponentRef } from '@angular/core';

@Component({
	selector: 'app-navigation-sideinfo',
	templateUrl: './navigation-sideinfo.component.html',
	styleUrls: ['./navigation-sideinfo.component.scss'],
	entryComponents: [
		UserListSideinfoComponent,
		UserGroupListSideinfoComponent,
		InstrumentListSideinfoComponent,
		InstrumentTypeListSideinfoComponent
	]
})
export class NavigationSideinfoComponent implements OnInit {

	@ViewChild('directive', {read: ViewContainerRef}) directiveRef:ViewContainerRef;
	
	@Input() sidenav:MdSidenav;

	private isOpened:boolean;
	private currentDirective:ComponentRef<AbstractSideinfoComponent>;
	
	private disabled:boolean = true;
	public title:string;
	public subtitle:string;

	constructor(private navigationService:NavigationService, private resolver: ComponentFactoryResolver) {}

	ngOnInit() {
		this.navigationService.setSideinfoComponent(this);
		this.disable(); // Start with info sidenav closed.
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
		this.destroyCurrent();
		this.currentDirective = component;
		this.title = component.instance.title;
	}

	isDisabled() {
		return this.disabled;
	}

	disable() {
		this.disabled = true;
		this.close();
		this.destroyCurrent();
	}


	private destroyCurrent() {
		if (this.currentDirective) {
			this.currentDirective.destroy();
		}
	}

	close() {
		this.sidenav.close();
		this.isOpened = false;
	}

	/** 
	 * Opens the info sidenav. 
	 * Will automatically enable the info sidenav if it's disabled. 
	 */
	open() {
		this.disabled = false;
		this.sidenav.open();
		this.isOpened = true;
	}

	isOpen():boolean {
		return this.isOpened;
	}

}