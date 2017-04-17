import { UnverifiedDataSet } from './unverified-data-set.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';
import { Instrument } from '../instrument/instrument.class';

/**
 * This class was automatically generated from UnverifiedIntegratedData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedIntegratedData {
	id:number;
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