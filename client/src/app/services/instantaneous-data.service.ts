import { MonitoringPoint } from './../model/server/model/monitoring-point.enum';
import { InstantaneousData } from './../model/server/persistence/entity/instantaneous-data.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../environments/environment';

@Injectable()
export class InstantaneousDataService {

	readonly baseUrl:string = environment.resourceUrl + '/instantaneous-data';

	constructor(private authHttp: AuthHttp) {}

	getBySiteName(callback:(data) => void, siteName:string) {
		this.authHttp.get(this.baseUrl + "/" + siteName).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	getAll(callback:(data) => void) {
		this.authHttp.get(this.baseUrl).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	save(callback:(data) => void, input:any[]) {
		this.authHttp.post(this.baseUrl, input).map((res:Response) => res.json()).subscribe(
			data => callback(data),
			err => console.log(err),
		);
	}

	upload(callback:(data) => void, file:any) {
		this.authHttp.post(this.baseUrl + "/upload", file).map((res:Response) => res.json()).subscribe(
			data => callback(data),
			err => console.log(err),
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