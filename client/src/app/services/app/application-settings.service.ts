import { AuthHttp } from 'angular2-jwt';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';

@Injectable()
export class ApplicationSettingsService {

	readonly baseUrl:string = environment.resourceUrl + '/appvars';

	constructor(private authHttp: AuthHttp) {}

    getMap(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

    update(map:any, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + "/update", map).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	updateSuperAdminPassword(password:string, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + "/update/sa-pw", password).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}