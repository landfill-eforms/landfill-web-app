import { AbstractHttpService } from './../abstract/abstract-http.service';
import { EnumUtils } from './../../utils/enum.utils';
import { MonitoringPoint } from './../../model/server/persistence/enums/location/monitoring-point.enum';
import { UnverifiedDataSet } from './../../model/server/persistence/entity/unverified/unverified-data-set.class';
import { Response } from '@angular/http';
import { environment } from './../../../environments/environment';
import { AuthHttp } from 'angular2-jwt';
import { Injectable } from '@angular/core';

@Injectable()
export class UnverifiedDataService extends AbstractHttpService<UnverifiedDataSet> {

	constructor(authHttp:AuthHttp) {
		super("/unverified-data", authHttp);
	}

	commit(dataSet:UnverifiedDataSet, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/commit', dataSet).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	checkForErrors(dataSet:UnverifiedDataSet):UnverifiedDataSet {
		let dataSetErrors:string[] = [];
		let instantaneousErrors:string[] = [];
		let integratedErrors:string[] = [];
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
			if (!data.instrument || !data.instrument.id) {
				instantaneousErrors.push("The instantaneous reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " does not have an instrument specified.");
			}
			if (!data.barometricPressure) {
				instantaneousErrors.push("The instantaneous reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " does not have an barometric pressure reading.");
			}
		}
		for (let i = 0; i < dataSet.unverifiedIntegratedData.length; i++) {
			let data = dataSet.unverifiedIntegratedData[i];
			if (data.monitoringPoint.site != dataSet.site) {
				integratedErrors.push("The integrated reading of " + data.methaneLevel / 100 + "ppm has a grid on the wrong site (" + data.monitoringPoint.site.name + ")!");
			}
			if (!data.instrument || !data.instrument.id) {
				integratedErrors.push("The integrated reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " does not have an instrument specified.");
			}
			if (!data.barometricPressure) {
				integratedErrors.push("The integrated reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " does not have an barometric pressure reading.");
			}
		}
		dataSet.errors = {
			dataSet: dataSetErrors,
			instantaneous: instantaneousErrors,
			integrated: integratedErrors
		}
		console.log(dataSet);
		return dataSet;
	}

}