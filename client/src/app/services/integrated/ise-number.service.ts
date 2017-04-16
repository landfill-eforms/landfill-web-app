import { IseNumber } from './../../model/server/persistence/entity/integrated/ise-number.class';
import { Site } from './../../model/server/persistence/enums/location/site.enum';
import { Response } from '@angular/http';
import { environment } from './../../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';

/** Handles the logical operations for IseNumber objects. */
@Injectable()
export class IseNumberService {

	readonly baseUrl:string = environment.resourceUrl + '/ise-number';

	constructor(private authHttp:AuthHttp) {}

	getAll(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/all").map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}
	
	getBySite(site:Site, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/site/" + site.constantName).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getByIseNumber(imeNumber:string, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/unique/imeNumber/" + imeNumber).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	create(imeNumber:IseNumber, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/create', imeNumber).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	update(imeNumber:IseNumber, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update', imeNumber).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getStringFromIseNumber(imeNumber:IseNumber):string {
		let date:Date = new Date(imeNumber.dateCode);
		let month = date.getMonth() + 1;
		let dayOfMonth = date.getDate();
        return imeNumber.site.shortName + "-" + (date.getFullYear() % 2000) + (month < 10 ? "0" + month : month) + "-" + (imeNumber.sequence < 10 ? "0" : "") + imeNumber.sequence;
	}

}