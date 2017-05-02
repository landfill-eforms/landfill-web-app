import { ImeNumber } from '../surfaceemission/instantaneous/ime-number.class';
import { UnverifiedDataSet } from './unverified-data-set.class';
import { AbstractEntity } from '../abstract-entity.class';
import { Instrument } from '../instrument/instrument.class';
import { UnverifiedWarmspotData } from './unverified-warmspot-data.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from UnverifiedInstantaneousData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedInstantaneousData extends AbstractEntity {
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	barometricPressure:number;
	methaneLevel:number;
	startTime:number;
	endTime:number;
	imeNumbers:ImeNumber[];
	unverifiedWarmspotData:UnverifiedWarmspotData;
	unverifiedDataSet:UnverifiedDataSet;
}