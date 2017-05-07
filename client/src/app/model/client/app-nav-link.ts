import { Route } from '@angular/router';

export class AppNavLink {
	title:string;
	subtitle:string;
	img?:string;
	route?:Route;
	visible?:boolean;
	disabled?:boolean;
}