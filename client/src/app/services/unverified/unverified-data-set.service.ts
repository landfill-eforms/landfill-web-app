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
		let imeErrors:string[] = [];
		let integratedErrors:string[] = [];
		let probeErrors:string[] = [];
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
		//WARMSPOT
		for (let i = 0; i < dataSet.unverifiedWarmspotData.length; i++) {
			let data = dataSet.unverifiedWarmspotData[i];
			if (data.methaneLevel >= 50000 ) {
				instantaneousErrors.push("The Warmspot reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " is too high for a Warmspot.");
			}
			if (data.methaneLevel < 20000) {
				instantaneousErrors.push("The Warmspot reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " is too low for a Warmspot.");
			}
			if (!data.instrument || !data.instrument.id) {
				instantaneousErrors.push("The Warmspot reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " does not have an instrument specified.");
			}
		}

		for(let i = 0; i < dataSet.imeNumbers.length; i++) {
			let data = dataSet.imeNumbers[i];
			if (data.imeData[0].methaneLevel <= 50000 ) {
				instantaneousErrors.push("The Instantaneous Exceedances reading of " + data.imeData[0].methaneLevel / 100 + "ppm is too low for a IME.");
			}
			if (!data.imeData[0].instrument || !data.imeData[0].instrument.id) {
				instantaneousErrors.push("The Instantaneous Exceedances reading of " + data.imeData[0].methaneLevel / 100 + "ppm does not have an instrument specified.");
			}
			if (data.monitoringPoints.length <= 0) {
				instantaneousErrors.push("The Instantaneous Exceedances reading of " + data.imeData[0].methaneLevel / 100 + "ppm must have at least 1 grid.");
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

		for(let i = 0; i < dataSet.iseNumbers.length; i++) {
			let data = dataSet.iseNumbers[i];
			if (data.iseData[0].methaneLevel <= 2500 ) {
				integratedErrors.push("The Integrated Exceedances reading of " + data.iseData[0].methaneLevel / 100 + "ppm is too low for a IME.");
			}
			if (!data.iseData[0].instrument || !data.iseData[0].instrument.id) {
				integratedErrors.push("The Integrated Exceedances reading of " + data.iseData[0].methaneLevel / 100 + "ppm does not have an instrument specified.");
			}
		}

		for (let i = 0; i < dataSet.unverifiedProbeData.length; i++) {
			let data = dataSet.unverifiedProbeData[i];
			if (!data.instrument || !data.instrument.id) {
				probeErrors.push("The probe reading of " + data.methaneLevel / 100 + "ppm on grid " + data.monitoringPoint.name + " does not have an instrument specified.");
			}
		}
		dataSet.errors = {
			dataSet: dataSetErrors,
			instantaneous: instantaneousErrors,
			integrated: integratedErrors,
			probe: probeErrors
		}
		console.log(dataSet);
		return dataSet;
	}

}