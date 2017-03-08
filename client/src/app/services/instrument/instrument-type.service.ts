import { InstrumentType } from './../../model/server/persistence/entity/instrument/instrument-type.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class InstrumentTypeService {

	readonly baseUrl:string = environment.resourceUrl + '/instrument-type';

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

	update(callback:(data) => void, instrumentType:InstrumentType) {
		this.authHttp.post(this.baseUrl, instrumentType).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	create(callback:(data) => void, instrumentType:InstrumentType) {
		this.authHttp.post(this.baseUrl + '/new', instrumentType).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	// TODO Find out why delete request method is giving cors error.
	delete(callback:(data) => void, instrumentType:InstrumentType) {
		this.authHttp.post(this.baseUrl + '/delete', instrumentType).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

}