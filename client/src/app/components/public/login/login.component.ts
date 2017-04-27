import { AppConstant } from './../../../app.constant';
import { Component } from '@angular/core';
import { TitleService } from './../../../services/app/title.service';
import { environment } from './../../../../environments/environment';
import { AuthService } from './../../../services/auth/auth.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent {

	readonly versionNumber:string = AppConstant.VERSION_NUMBER;
	readonly backgroundUrl:string = environment.assetsUrl + "/images/login-background.jpg";
	readonly logoUrl:string = environment.assetsUrl + "/images/la-san-logo-lite.png";

	credentials:{username:string, password:string} = {
		username: "",
		password: ""
	};

	constructor (
		private titleService:TitleService,
		private authService:AuthService
	) {
		titleService.setTitle("Login");
	}

	login() {
		this.authService.login(this.credentials.username, this.credentials.password);
	}

}