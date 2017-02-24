import { MonitoringPoint } from '../../../model/monitoring-point.enum';
import { IMENumberStatus } from '../../../model/ime-number-status.enum';
import { InstantaneousData } from './instantaneous-data.class';
import { IMEData } from './ime-data.class';
import { IMERepairData } from './ime-repair-data.class';
import { UnverifiedInstantaneousData } from '../unverified/unverified-instantaneous-data.class';
import { Site } from '../../../model/site.enum';

/**
 * This class was automatically generated from IMENumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IMENumber {
	id:number;
	site:Site;
	dateCode:number;
	sequence:number;
	status:IMENumberStatus;
	monitoringPoints:MonitoringPoint[];
	instantaneousData:InstantaneousData[];
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	imeData:IMEData[];
	imeNumber:string;
}