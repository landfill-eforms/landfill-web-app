import { ExceedanceStatus } from '../../enums/exceedance/exceedance-status.enum';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';
import { Site } from '../../enums/location/site.enum';

/**
 * This class was automatically generated from ServiceEmissionsExceedanceNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export abstract class ServiceEmissionsExceedanceNumber {
	id:number;
	site:Site;
	dateCode:number;
	sequence:number;
	status:ExceedanceStatus;
	monitoringPoints:MonitoringPoint[];
}