import { User } from './../../model/server/persistence/entity/user/user.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class UserService {

	readonly baseUrl:string = environment.resourceUrl + '/user';

	constructor(private authHttp: AuthHttp) {}

	getByUsername(username:string, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/unique/username/" + username).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getAll(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/all").map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}
	
	create(user:User, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/create', user).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	changeUsername(user:User, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update/username', user).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	changePassword(user:User, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update/password', user).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	updateProfile(user:User, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update/profile', user).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	updateStatus(user:User, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update/status', user).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}