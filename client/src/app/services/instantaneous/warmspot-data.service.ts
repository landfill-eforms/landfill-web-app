import { WarmspotData } from './../../model/server/persistence/entity/surfaceemission/instantaneous/warmspot-data.class';
import { AbstractHttpService } from './../abstract/abstract-http.service';
import { environment } from './../../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';

@Injectable()
export class WarmspotDataService extends AbstractHttpService<WarmspotData> {

	constructor(authHttp:AuthHttp) {
		super('/warmspot-data', authHttp);
	}

}