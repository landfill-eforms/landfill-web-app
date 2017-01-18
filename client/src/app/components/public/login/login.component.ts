import { Component } from '@angular/core';
import { AuthService } from './../../../services/auth/auth.service';

@Component({
	selector: 'login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent {

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