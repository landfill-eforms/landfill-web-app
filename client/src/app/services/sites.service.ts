import { Component, OnInit, Injectable } from '@angular/core';
import { Response, Headers, RequestOptions } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../environments/environment';

@Injectable()
export class SitesService {

	readonly baseUrl:string = environment.resourceUrl + '/sites';

	constructor (private authHttp:AuthHttp) {}

	ngOnInit() {}

	getVisible(callback:(data) => void) {
		this.authHttp.get(this.baseUrl).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

}