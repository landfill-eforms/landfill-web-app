import { ExceedanceStatus } from '../../enums/exceedance/exceedance-status.enum';
import { IseData } from './ise-data.class';
import { Site } from '../../enums/location/site.enum';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from IseNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IseNumber {
	id:number;
	site:Site;
	dateCode:number;
	sequence:number;
	status:ExceedanceStatus;
	monitoringPoints:MonitoringPoint[];
	iseData:IseData[];
	iseNumber:string;
}