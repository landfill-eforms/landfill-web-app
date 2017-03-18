import { Instrument } from './../../model/server/persistence/entity/instrument/instrument.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class InstrumentService {

	readonly baseUrl:string = environment.resourceUrl + '/instrument';

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

	create(instrument:Instrument, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/create', instrument).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	update(instrument:Instrument, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update', instrument).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	// TODO Find out why delete request method is giving cors error.
	delete(instrument:Instrument, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/delete', instrument).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}