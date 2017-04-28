import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';
import { User } from '../user/user.class';
import { UnverifiedDataSet } from './unverified-data-set.class';

/**
 * This class was automatically generated from UnverifiedProbeData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedProbeData {
	id:number;
	monitoringPoint:MonitoringPoint;
	date:number;
	methaneLevel:number;
	pressureLevel:number;
	description:string;
	barometricPressure:number;
	accessible:boolean;
	inspectors:User[];
	unverifiedDataSet:UnverifiedDataSet;
}