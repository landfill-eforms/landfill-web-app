import { Instrument } from '../instrument/instrument.class';
import { InstantaneousData } from './instantaneous-data.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';
import { User } from '../user/user.class';

/**
 * This class was automatically generated from WarmspotData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class WarmspotData {
	id:number;
	monitoringPoint:MonitoringPoint;
	instrument:Instrument;
	inspector:User;
	methaneLevel:number;
	date:number;
	description:string;
	size:string;
	instantaneousData:InstantaneousData[];
}