import { EnumUtils } from './../../utils/enum.utils';
import { MonitoringPoint } from './../../model/server/persistence/enums/monitoring-point.enum';
import { UnverifiedDataSet } from './../../model/server/persistence/entity/unverified/unverified-data-set.class';
import { Response } from '@angular/http';
import { environment } from './../../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';

@Injectable()
export class UnverifiedDataService {

	readonly baseUrl:string = environment.resourceUrl + '/unverified-data';

	constructor(private authHttp:AuthHttp) {}

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

	update(callback:(data) => void, dataSet:UnverifiedDataSet) {
		this.authHttp.post(this.baseUrl, dataSet).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	create(callback:(data) => void, dataSet:UnverifiedDataSet) {
		this.authHttp.post(this.baseUrl + '/new', dataSet).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	commit(callback:(data) => void, dataSet:UnverifiedDataSet) {
		this.authHttp.post(this.baseUrl + '/commit', dataSet).map((res:Response) => res.json()).subscribe(
				data => callback(data),
				err => console.log(err)
			);
	}

	checkForErrors(dataSet:UnverifiedDataSet):UnverifiedDataSet {

		let dataSetErrors:string[] = [];
		if (!dataSet.barometricPressure) {
			dataSetErrors.push("The barometric pressure is not set.")
		}

		let instantaneousErrors:string[] = [];
		for (let i = 0; i < dataSet.unverifiedInstantaneousData.length; i++) {
			let data = dataSet.unverifiedInstantaneousData[i];
			if (data.monitoringPoint.site != dataSet.site) {
				instantaneousErrors.push("The instantaneous reading of " + data.methaneLevel / 100 + "ppm has a grid on the wrong site (" + data.monitoringPoint.site.name + ")!");
			}
			if (data.methaneLevel >= 50000 && (!data.imeNumbers || data.imeNumbers.length == 0)) {
				instantaneousErrors.push("The instantaneous reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " is missing an IME number.");
			}
			if (data.methaneLevel < 50000 && (data.imeNumbers && data.imeNumbers.length > 0)) {
				instantaneousErrors.push("The instantaneous reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " should not have an IME number.");
			}
			if (!data.instrument) {
				instantaneousErrors.push("The instantaneous reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " does not have an instrument specified.");
			}
		}

		dataSet.errors = {
			dataSet: dataSetErrors,
			instantaneous: instantaneousErrors
		}

		return dataSet;
	}

}