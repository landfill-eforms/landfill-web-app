import { AppNavLink } from './../../../model/client/app-nav-link';
import { AppConstant } from './../../../app.constant';
import { AuthService } from './../../../services/auth/auth.service';
import { Route, Router } from '@angular/router';
import { Input, Component, EventEmitter, Output, OnInit } from '@angular/core';

@Component({
	selector: 'app-card-selector-menu',
	templateUrl: './card-selector-menu.component.html',
	styleUrls: ['./card-selector-menu.component.scss']
})
export class CardSelectorMenuComponent implements OnInit {

	@Input() cards:AppNavLink[] = [];

	@Input() title:string;
	@Input() subtitle:string;

	@Output() action:EventEmitter<any> = new EventEmitter();

	constructor(private authService:AuthService, private router:Router) {
		
	}

	ngOnInit() {
		for (let card of this.cards) {
			card.visible = !card.route || !card.route.data || this.authService.canAccess(card.route.data["permissions"]);
		}
	}

	click(card:AppNavLink) {
		if (card.disabled) {
			return;
		}
		if (card.route && card.route.path) {
			this.router.navigate([AppConstant.RESTRICTED_ROUTE_BASE + '/' + card.route.path]);
		}
	}

}

