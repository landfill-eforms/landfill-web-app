import { Instrument } from '../instrument/instrument.class';
import { AbstractUnverifiedData } from './abstract-unverified-data.class';
import { UnverifiedDataSet } from './unverified-data-set.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from UnverifiedIntegratedData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedIntegratedData extends AbstractUnverifiedData {
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	sampleId:string;
	bagNumber:number;
	volume:number;
	barometricPressure:number;
	methaneLevel:number;
	startTime:number;
	endTime:number;
	unverifiedDataSet:UnverifiedDataSet;
}