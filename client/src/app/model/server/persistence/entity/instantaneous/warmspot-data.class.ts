import { User } from '../user/user.class';
import { InstantaneousData } from './instantaneous-data.class';
import { Instrument } from '../instrument/instrument.class';
import { UnverifiedInstantaneousData } from '../unverified/unverified-instantaneous-data.class';
import { MonitoringPoint } from '../../enums/monitoring-point.enum';

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
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	verified:boolean;
}