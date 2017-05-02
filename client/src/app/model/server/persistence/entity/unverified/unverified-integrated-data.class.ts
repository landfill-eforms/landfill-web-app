import { UnverifiedDataSet } from './unverified-data-set.class';
import { AbstractEntity } from '../abstract-entity.class';
import { Instrument } from '../instrument/instrument.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from UnverifiedIntegratedData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedIntegratedData extends AbstractEntity {
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	bagNumber:number;
	volume:number;
	barometricPressure:number;
	methaneLevel:number;
	startTime:number;
	endTime:number;
	unverifiedDataSet:UnverifiedDataSet;
}