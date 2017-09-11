import { AbstractEntity } from '../abstract-entity.class';
import { Instrument } from '../instrument/instrument.class';
import { User } from '../user/user.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from ProbeData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ProbeData extends AbstractEntity {
	monitoringPoint: MonitoringPoint;
	date: number;
	methaneLevel: number;
	pressureLevel: number;
	description: string;
	instrument: Instrument;
	barometricPressure: number;
	accessible: boolean;
	inspectors: User[];
}