import { AbstractEntity } from '../../abstract-entity.class';
import { Instrument } from '../../instrument/instrument.class';
import { User } from '../../user/user.class';
import { MonitoringPoint } from '../../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from WarmspotData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class WarmspotData extends AbstractEntity {
	monitoringPoint: MonitoringPoint;
	instrument: Instrument;
	inspector: User;
	methaneLevel: number;
	date: number;
	description: string;
	size: string;
}