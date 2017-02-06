import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../environments/environment';
import { User } from './../model/server/persistence/entity/user.class';

@Injectable()
export class UserService {

	readonly baseUrl:string = environment.resourceUrl + '/user';

	constructor(private authHttp: AuthHttp) {}

	getByUsername(callback:(data) => void, username:string) {
		this.authHttp.get(this.baseUrl + "/unique/username/" + username).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	getAll(callback:(data) => void) {
		this.authHttp.get(this.baseUrl + "/list/all").map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	update(callback:(data) => void, user:User) {
		this.authHttp.post(this.baseUrl, user).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	create(callback:(data) => void, user:User) {
		this.authHttp.post(this.baseUrl + '/new', user).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

}