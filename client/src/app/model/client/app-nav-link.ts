import { Route } from '@angular/router';

export class AppNavLink {
	title:string;
	subtitle:string;
	img?:string;
	route?:Route;
	action?:any;
	visible?:boolean;
	disabled?:boolean;
}