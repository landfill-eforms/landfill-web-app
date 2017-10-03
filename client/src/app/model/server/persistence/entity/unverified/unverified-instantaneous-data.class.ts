import { Instrument } from '../instrument/instrument.class';
import { ImeNumber } from '../surfaceemission/instantaneous/ime-number.class';
import { AbstractUnverifiedData } from './abstract-unverified-data.class';
import { UnverifiedDataSet } from './unverified-data-set.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from UnverifiedInstantaneousData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedInstantaneousData extends AbstractUnverifiedData {
	monitoringPoint: MonitoringPoint;
	instrument: Instrument;
	barometricPressure: number;
	methaneLevel: number;
	startTime: number;
	endTime: number;
	date: number; //#42 adding this for date
	imeNumbers: ImeNumber[];
	unverifiedDataSet: UnverifiedDataSet;
}