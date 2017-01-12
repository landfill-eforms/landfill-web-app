import { Component, OnInit, Injectable } from '@angular/core';
import { Response, Headers, RequestOptions } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../environments/environment';

@Injectable()
export class InstantaneousDataService {

	readonly baseUrl:string = environment.resourceUrl + '/instantaneous-data';

	constructor(private authHttp: AuthHttp) {}

	getBySiteName(callback:(data) => void, siteName:string) {
		this.authHttp.get(this.baseUrl + "/" + siteName).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	getAll(callback:(data) => void) {
		this.authHttp.get(this.baseUrl).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	save(callback:(data) => void, input:any[]) {
		this.authHttp.post(this.baseUrl, input).map((res:Response) => res.json()).subscribe(
			data => callback(data),
			err => console.log(err),
		);
	}

	upload(callback:(data) => void, file:any) {
		this.authHttp.post(this.baseUrl + "/upload", file).map((res:Response) => res.json()).subscribe(
			data => callback(data),
			err => console.log(err),
		);
	}

}