import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
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