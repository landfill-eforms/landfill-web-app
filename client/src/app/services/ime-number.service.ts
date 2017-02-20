import { IMENumber } from './../model/server/persistence/entity/instantaneous/ime-number.class';
import { Site } from './../model/server/model/site.enum';
import { Response } from '@angular/http';
import { environment } from './../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';

/** Handles the logical operations for IMENumber objects. */
@Injectable()
export class IMENumberService {

	readonly baseUrl:string = environment.resourceUrl + '/ime-number';

	constructor(private authHttp:AuthHttp) {}

	getAll(callback:(data) => void) {
		this.authHttp.get(this.baseUrl + "/list/all").map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	getBySite(callback:(data) => void, site:Site) {
		this.authHttp.get(this.baseUrl + "/list/site/" + site.constantName).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	update(callback:(data) => void, imeNumber:IMENumber) {
		this.authHttp.post(this.baseUrl, imeNumber).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	create(callback:(data) => void, imeNumber:IMENumber) {
		this.authHttp.post(this.baseUrl + '/new', imeNumber).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	getStringFromImeNumber(imeNumber:IMENumber):string {
		let date:Date = new Date(imeNumber.discoveryDate);
		let month = date.getMonth() + 1;
		let dayOfMonth = date.getDate();
        return imeNumber.site.shortName + "-" + (date.getFullYear() % 2000) + (month < 10 ? "0" + month : month) + "-" + (imeNumber.sequence < 10 ? "0" : "") + imeNumber.sequence;
	}

}