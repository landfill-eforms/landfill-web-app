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

	listGrids(imeNumber:ImeNumber):string {
		let result:string = "";
		for (let i = 0; i < imeNumber.monitoringPoints.length; i++) {
			if (i > 0) {
				result += ", ";
			}
			result += imeNumber.monitoringPoints[i].name;
		}
		return result;
	}

	/** Formats a list of IME numbers into a string. */
	formatList(imeNumbers:ImeNumber[]):string {
		console.log("CALLED")
		imeNumbers.sort((a, b) => a.sequence - b.sequence);
		let result:string = "";
		for (let i = 0; i < imeNumbers.length; i++) {
			if (i == 0) {
				result += imeNumbers[i].imeNumber;
			}
			else {
				result += ", " + (imeNumbers[i].sequence < 10 ? "0" : "") + imeNumbers[i].sequence;
			}
		}
		return result;
	}

}