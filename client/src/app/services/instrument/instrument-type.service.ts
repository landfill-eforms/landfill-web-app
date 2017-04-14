import { InstrumentType } from './../../model/server/persistence/entity/instrument/instrument-type.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class InstrumentTypeService {

	readonly baseUrl:string = environment.resourceUrl + '/instrument-type';

	constructor(private authHttp: AuthHttp) {}

	getAll(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/all").map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getById(id:string, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/unique/id/" + id).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	create(instrumentType:InstrumentType, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/create', instrumentType).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	update(instrumentType:InstrumentType, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update', instrumentType).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	// TODO Find out why delete request method is giving cors error.
	delete(instrumentType:InstrumentType, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/delete', instrumentType).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	generateApplicableTestsList(instrumentType:InstrumentType):string[] {
		let result:string[] = [];
		if (instrumentType.instantaneous) {
			result.push("Instantaneous/Integerated");
		}
		if (instrumentType.probe) {
			result.push("Probe/Gas Wells");
		}
		if (instrumentType.methanePercent) {
			result.push("Methane Percentage");
		}
		if (instrumentType.methanePpm) {
			result.push("Methane ppm");
		}
		if (instrumentType.hydrogenSulfidePpm) {
			result.push("Hydrogen Sulfide ppm");
		}
		if (instrumentType.oxygenPercent) {
			result.push("Oxygen Percentage");
		}
		if (instrumentType.carbonDioxidePercent) {
			result.push("Carbon Dioxide Percentage");
		}
		if (instrumentType.nitrogenPercent) {
			result.push("Nitrogen Percentage");
		}
		if (instrumentType.pressure) {
			result.push("Pressure");
		}
		return result;
	}

}