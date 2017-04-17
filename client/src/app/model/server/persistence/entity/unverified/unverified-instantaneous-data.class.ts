import { UnverifiedDataSet } from './unverified-data-set.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';
import { Instrument } from '../instrument/instrument.class';
import { UnverifiedWarmspotData } from './unverified-warmspot-data.class';
import { ImeNumber } from '../serviceemission/instantaneous/ime-number.class';

/**
 * This class was automatically generated from UnverifiedInstantaneousData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedInstantaneousData {
	id:number;
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	barometricPressure:number;
	methaneLevel:number;
	startTime:number;
	endTime:number;
	imeNumbers:ImeNumber[];
	unverifiedDataSet:UnverifiedDataSet;
	unverifiedWarmspotData:UnverifiedWarmspotData;
}