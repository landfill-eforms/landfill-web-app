import { Component, OnInit } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Router } from '@angular/router';
import { JwtHelper } from 'angular2-jwt';
import { AuthService } from './../../../services/auth/auth.service';

@Component({
	selector: 'login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

	jwtHelper:JwtHelper = new JwtHelper();

	credentials:{username:string, password:string} = {
		username: "",
		password: ""
	};

	constructor (
		private router:Router,
		private http:Http,
		private authService:AuthService
	) {}

	ngOnInit() {

	}

	login() {
		this.authService.login(this.credentials.username, this.credentials.password);
	}

}