import { UnverifiedDataSet } from './unverified-data-set.class';
import { AbstractEntity } from '../abstract-entity.class';
import { User } from '../user/user.class';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from UnverifiedProbeData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedProbeData extends AbstractEntity {
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