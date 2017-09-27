import { Instrument } from '../instrument/instrument.class';
import { AbstractUnverifiedData } from './abstract-unverified-data.class';
import { UnverifiedDataSet } from './unverified-data-set.class';
import { User } from '../user/user.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from UnverifiedProbeData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedProbeData extends AbstractUnverifiedData {
	monitoringPoint: MonitoringPoint; 
	date: number;
	methaneLevel: number;
	pressureLevel: number;
	description: string;
	instrument: Instrument;
	inspectors: User[];
	barometricPressure: number;
	accessible: boolean;
	unverifiedDataSet: UnverifiedDataSet;
}