import { UnverifiedInstantaneousData } from './unverified-instantaneous-data.class';
import { UnverifiedDataSet } from './unverified-data-set.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';
import { Instrument } from '../instrument/instrument.class';

/**
 * This class was automatically generated from UnverifiedWarmspotData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedWarmspotData {
	id:number;
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	methaneLevel:number;
	date:number;
	description:string;
	size:string;
	unverifiedInstantaneousData:UnverifiedInstantaneousData;
	unverifiedDataSet:UnverifiedDataSet;
}