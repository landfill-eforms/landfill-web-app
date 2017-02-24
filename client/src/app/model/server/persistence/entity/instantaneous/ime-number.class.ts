import { IMENumberStatus } from '../../../model/ime-number-status.enum';
import { IMERepairData } from './ime-repair-data.class';
import { UnverifiedInstantaneousData } from '../unverified/unverified-instantaneous-data.class';
import { InstantaneousData } from './instantaneous-data.class';
import { IMEData } from './ime-data.class';
import { MonitoringPoint } from '../../../model/monitoring-point.enum';
import { Site } from '../../../model/site.enum';

/**
 * This class was automatically generated from IMENumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IMENumber {
	id:number;
	site:Site;
	discoveryDate:number;
	sequence:number;
	status:IMENumberStatus;
	monitoringPoints:MonitoringPoint[];
	instantaneousData:InstantaneousData[];
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	imeData:IMEData[];
	imeRepairData:IMERepairData[];
	imeNumber:string;
}