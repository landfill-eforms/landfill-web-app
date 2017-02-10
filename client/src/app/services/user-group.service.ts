import { UserGroup } from './../model/server/persistence/entity/user/user-group.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../environments/environment';

@Injectable()
export class UserGroupService {

	readonly baseUrl:string = environment.resourceUrl + '/user-group';

	constructor(private authHttp: AuthHttp) {}

	getById(callback:(data) => void, id:string) {
		this.authHttp.get(this.baseUrl + "/unique/id/" + id).map((res:Response) => res.json()).subscribe(
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

	update(callback:(data) => void, userGroup:UserGroup) {
		this.authHttp.post(this.baseUrl, userGroup).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	create(callback:(data) => void, userGroup:UserGroup) {
		this.authHttp.post(this.baseUrl + '/new', userGroup).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	// TODO Find out why delete request method is giving cors error.
	delete(callback:(data) => void, userGroup:UserGroup) {
		this.authHttp.post(this.baseUrl + '/delete', userGroup).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

}