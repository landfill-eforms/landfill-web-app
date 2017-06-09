import { AbstractHttpService } from './../abstract/abstract-http.service';
import { InstrumentType } from './../../model/server/persistence/entity/instrument/instrument-type.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class InstrumentTypeService extends AbstractHttpService<InstrumentType> {

	constructor(authHttp:AuthHttp) {
		super('/instrument-type', authHttp);
	}

	generateApplicableTestsList(instrumentType:InstrumentType):string[] {
		if (!instrumentType) {
			return [];
		}
		let result:string[] = [];
		if (instrumentType.instantaneous) {
			result.push("Instantaneous/Integerated");
		}
		if (instrumentType.probe) {
			result.push("Probe/Gas Wells");
		}
		if (instrumentType.methanePercent) {
			result.push("Methane Percentage");
		}
		if (instrumentType.methanePpm) {
			result.push("Methane ppm");
		}
		if (instrumentType.hydrogenSulfidePpm) {
			result.push("Hydrogen Sulfide ppm");
		}
		if (instrumentType.oxygenPercent) {
			result.push("Oxygen Percentage");
		}
		if (instrumentType.carbonDioxidePercent) {
			result.push("Carbon Dioxide Percentage");
		}
		if (instrumentType.nitrogenPercent) {
			result.push("Nitrogen Percentage");
		}
		if (instrumentType.pressure) {
			result.push("Pressure");
		}
		return result;
	}

}