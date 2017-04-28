import { AbstractHttpService } from './../abstract/abstract-http.service';
import { ProbeData } from './../../model/server/persistence/entity/probe/probe-data.class';
import { environment } from './../../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';

@Injectable()
export class ProbeDataService extends AbstractHttpService<ProbeData> {

	constructor(authHttp:AuthHttp) {
		super('/probe-data', authHttp);
	}

}