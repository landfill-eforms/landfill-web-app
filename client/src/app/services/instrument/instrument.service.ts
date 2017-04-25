import { AbstractHttpService } from './../abstract/abstract-http.service';
import { Instrument } from './../../model/server/persistence/entity/instrument/instrument.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class InstrumentService extends AbstractHttpService<Instrument> {

	constructor(authHttp:AuthHttp) {
		super('/instrument', authHttp);
	}

}