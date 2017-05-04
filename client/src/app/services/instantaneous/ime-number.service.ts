import { SurfaceExceedanceNumberService } from './../exceedance/surface-exceedance-number.service';
import { AbstractHttpService } from './../abstract/abstract-http.service';
import { ImeNumber } from './../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { Site } from './../../model/server/persistence/enums/location/site.enum';
import { Response } from '@angular/http';
import { environment } from './../../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';

/** Handles the logical operations for ImeNumber objects. */
@Injectable()
export class ImeNumberService extends SurfaceExceedanceNumberService<ImeNumber> {

	constructor(authHttp:AuthHttp) {
		super('/ime-number', authHttp);
	}

	getByImeNumber(imeNumber:string, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/unique/imeNumber/" + imeNumber).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}