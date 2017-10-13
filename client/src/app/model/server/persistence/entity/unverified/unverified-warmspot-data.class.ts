import { Instrument } from '../instrument/instrument.class';
import { AbstractUnverifiedData } from './abstract-unverified-data.class';
import { UnverifiedDataSet } from './unverified-data-set.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from UnverifiedWarmspotData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedWarmspotData extends AbstractUnverifiedData {
	monitoringPoints: MonitoringPoint[];
<<<<<<< HEAD
	// monitoringPoint: MonitoringPoint;
=======
>>>>>>> a655e60406492ac1859a4af7833312c86a861331
	instrument: Instrument;
	methaneLevel: number;
	date: number;
	description: string;
	size: string;
	unverifiedDataSet: UnverifiedDataSet;
}