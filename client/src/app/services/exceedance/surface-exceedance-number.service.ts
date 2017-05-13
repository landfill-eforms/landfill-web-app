import { SurfaceEmissionExceedanceNumber } from './../../model/server/persistence/entity/surfaceemission/surface-emission-exceedance-number.class';
import { AbstractHttpService } from './../abstract/abstract-http.service';
import { ImeNumber } from './../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { Site } from './../../model/server/persistence/enums/location/site.enum';
import { Response } from '@angular/http';
import { environment } from './../../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';

/** Handles the logical operations for ImeNumber objects. */
export abstract class SurfaceExceedanceNumberService<T extends SurfaceEmissionExceedanceNumber> extends AbstractHttpService<T> {

	constructor(resourcePath:string, authHttp:AuthHttp) {
		super(resourcePath, authHttp);
	}

	getAllVerified(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/verified").map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getAllUnverified(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/verified").map((res:Response) => res.json()).subscribe(
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

	clear(entity:T, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/clear', entity).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	generateStringFromExceedanceNumber(exceedanceNumber:T):string {
		let date:Date = new Date(exceedanceNumber.dateCode);
		let month = date.getMonth() + 1;
		let dayOfMonth = date.getDate();
        return exceedanceNumber.site.shortName + "-" + (date.getFullYear() % 2000) + (month < 10 ? "0" + month : month) + "-" + (exceedanceNumber.sequence < 10 ? "0" : "") + exceedanceNumber.sequence;
	}

}