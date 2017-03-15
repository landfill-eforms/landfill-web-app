import { WarmspotData } from '../instantaneous/warmspot-data.class';
import { UnverifiedDataSet } from './unverified-data-set.class';
import { Instrument } from '../instrument/instrument.class';
import { ImeNumber } from '../instantaneous/ime-number.class';
import { MonitoringPoint } from '../../enums/monitoring-point.enum';

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
	warmspotData:WarmspotData[];
	unverifiedDataSet:UnverifiedDataSet;
	verified:boolean;
}