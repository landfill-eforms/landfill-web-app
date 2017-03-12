import { environment } from './../../../../environments/environment';
import { Component } from '@angular/core';
import { AuthService } from './../../../services/auth/auth.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent {

	readonly backgroundUrl:string = environment.assetsUrl + "/images/login-background.jpg";
	readonly logoUrl:string = environment.assetsUrl + "/images/la-san-logo-outline.PNG";

	credentials:{username:string, password:string} = {
		username: "",
		password: ""
	};

	constructor (
		private authService:AuthService
	) {}

	login() {
		this.authService.login(this.credentials.username, this.credentials.password);
	}

}