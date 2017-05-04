import { UnverifiedDataSet } from './unverified-data-set.class';
import { AbstractEntity } from '../abstract-entity.class';
import { Instrument } from '../instrument/instrument.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from UnverifiedWarmspotData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedWarmspotData extends AbstractEntity {
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	methaneLevel:number;
	date:number;
	description:string;
	size:string;
	unverifiedDataSet:UnverifiedDataSet;
}