import { MonitoringPoint } from '../../../model/monitoring-point.enum';
import { IMERepairData } from './ime-repair-data.class';
import { InstantaneousData } from './instantaneous-data.class';
import { IMEData } from './ime-data.class';
import { Site } from '../../../model/site.enum';

/**
 * This class was automatically generated from IMENumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IMENumber {
	id:number;
	site:Site;
	discoveryDate:number;
	series:number;
	unverified:boolean;
	monitoringPoints:MonitoringPoint[];
	instantaneousData:InstantaneousData[];
	imeData:IMEData[];
	imeRepairData:IMERepairData[];
	imeNumber:string;
}