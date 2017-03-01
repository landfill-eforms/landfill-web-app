import { User } from '../user/user.class';
import { Instrument } from '../instrument/instrument.class';
import { MonitoringPoint } from '../../../model/monitoring-point.enum';

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
}