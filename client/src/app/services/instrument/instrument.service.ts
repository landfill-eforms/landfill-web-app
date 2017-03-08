import { Instrument } from './../../model/server/persistence/entity/instrument/instrument.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class InstrumentService {

	readonly baseUrl:string = environment.resourceUrl + '/instrument';

	constructor(private authHttp: AuthHttp) {}

	getAll(callback:(data) => void) {
		this.authHttp.get(this.baseUrl + "/list/all").map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	getById(callback:(data) => void, id:string) {
		this.authHttp.get(this.baseUrl + "/unique/id/" + id).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	update(callback:(data) => void, instrument:Instrument) {
		this.authHttp.post(this.baseUrl, instrument).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	create(callback:(data) => void, instrument:Instrument) {
		this.authHttp.post(this.baseUrl + '/new', instrument).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	// TODO Find out why delete request method is giving cors error.
	delete(callback:(data) => void, instrument:Instrument) {
		this.authHttp.post(this.baseUrl + '/delete', instrument).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

}