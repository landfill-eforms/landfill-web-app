import { MonitoringPoint } from '../../../model/monitoring-point.enum';
import { Instrument } from '../instrument/instrument.class';
import { IMENumber } from '../instantaneous/ime-number.class';
import { UnverifiedDataSet } from './unverified-data-set.class';

/**
 * This class was automatically generated from UnverifiedInstantaneousData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedInstantaneousData {
	id:number;
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	methaneLevel:number;
	startTime:number;
	endTime:number;
	imeNumber:IMENumber;
	unverifiedDataSet:UnverifiedDataSet;
}