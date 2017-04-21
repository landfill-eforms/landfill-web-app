import { environment } from './../../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';

@Injectable()
export class WarmspotDataService {

	readonly baseUrl:string = environment.resourceUrl + '/warmspot-data';

	constructor(private authHttp:AuthHttp) {}
	
	getAll(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/all").map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}