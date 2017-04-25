import { AbstractHttpService } from './../abstract/abstract-http.service';
import { ImeNumber } from './../../model/server/persistence/entity/serviceemission/instantaneous/ime-number.class';
import { Site } from './../../model/server/persistence/enums/location/site.enum';
import { Response } from '@angular/http';
import { environment } from './../../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';

/** Handles the logical operations for ImeNumber objects. */
@Injectable()
export class ImeNumberService extends AbstractHttpService<ImeNumber> {

	constructor(authHttp:AuthHttp) {
		super('/ime-number', authHttp);
	}
	
	getBySite(site:Site, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/site/" + site.constantName).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getByImeNumber(imeNumber:string, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/unique/imeNumber/" + imeNumber).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getStringFromImeNumber(imeNumber:ImeNumber):string {
		let date:Date = new Date(imeNumber.dateCode);
		let month = date.getMonth() + 1;
		let dayOfMonth = date.getDate();
        return imeNumber.site.shortName + "-" + (date.getFullYear() % 2000) + (month < 10 ? "0" + month : month) + "-" + (imeNumber.sequence < 10 ? "0" : "") + imeNumber.sequence;
	}

}