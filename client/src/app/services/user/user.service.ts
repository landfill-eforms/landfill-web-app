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

	update(user:User, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update', user).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}