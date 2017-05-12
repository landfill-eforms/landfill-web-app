import { SurfaceExceedanceNumberService } from './../exceedance/surface-exceedance-number.service';
import { AbstractHttpService } from './../abstract/abstract-http.service';
import { IseNumber } from './../../model/server/persistence/entity/surfaceemission/integrated/ise-number.class';
import { Site } from './../../model/server/persistence/enums/location/site.enum';
import { Response } from '@angular/http';
import { environment } from './../../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';

/** Handles the logical operations for IseNumber objects. */
@Injectable()
export class IseNumberService extends SurfaceExceedanceNumberService<IseNumber> {

	constructor(authHttp:AuthHttp) {
		super('/ise-number', authHttp);
	}

	getByIseNumber(iseNumber:string, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/unique/iseNumber/" + iseNumber).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}