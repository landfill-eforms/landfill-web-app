import { AbstractHttpService } from './../abstract/abstract-http.service';
import { Site } from './../../model/server/persistence/enums/location/site.enum';
import { InstantaneousData } from './../../model/server/persistence/entity/surfaceemission/instantaneous/instantaneous-data.class';
import { MonitoringPoint } from './../../model/server/persistence/enums/location/monitoring-point.enum';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class InstantaneousDataService extends AbstractHttpService<InstantaneousData> {

	constructor(authHttp:AuthHttp) {
		super('/instantaneous-data', authHttp);
	}

	getBySite(site:Site, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/" + site.constantName).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getBySiteAndDate(site:Site, start:number, end:number, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/" + site.constantName + "/" + start + "/" + end).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	// TODO Rename this function.
	processDataPoint(data:InstantaneousData):InstantaneousData {
		if (typeof data.monitoringPoint == "string") {
			let monitoringPointName:any = data.monitoringPoint;
			data.monitoringPoint = MonitoringPoint[monitoringPointName];
		}
		return data;
	}

}