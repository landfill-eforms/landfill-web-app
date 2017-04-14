import { IntegratedData } from './../../model/server/persistence/entity/integrated/integrated-data.class';
import { MonitoringPoint } from './../../model/server/persistence/enums/monitoring-point.enum';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class IntegratedDataService {

	readonly baseUrl:string = environment.resourceUrl + '/integrated-data';

	constructor(private authHttp:AuthHttp) {}

	getBySite(siteName:string, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/" + siteName).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getBySiteAndDate(siteName:string, start:number, end:number, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/" + siteName + "/" + start + "/" + end).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getAll(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	// TODO Rename this function.
	processDataPoint(data:IntegratedData):IntegratedData {
		if (typeof data.monitoringPoint == "string") {
			let monitoringPointName:any = data.monitoringPoint;
			data.monitoringPoint = MonitoringPoint[monitoringPointName];
		}
		return data;
	}

}