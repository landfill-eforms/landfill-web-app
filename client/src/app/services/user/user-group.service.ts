import { UserGroup } from './../../model/server/persistence/entity/user/user-group.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class UserGroupService {

	readonly baseUrl:string = environment.resourceUrl + '/user-group';

	constructor(private authHttp: AuthHttp) {}

	getAll(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/all").map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getById(id:string, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/unique/id/" + id).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	create(userGroup:UserGroup, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/create', userGroup).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	update(userGroup:UserGroup, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update', userGroup).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	// TODO Find out why delete request method is giving cors error.
	delete(userGroup:UserGroup, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/delete', userGroup).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}