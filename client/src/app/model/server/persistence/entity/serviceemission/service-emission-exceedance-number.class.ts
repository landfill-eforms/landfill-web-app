import { Site } from '../../enums/location/site.enum';
import { ExceedanceStatus } from '../../enums/exceedance/exceedance-status.enum';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from ServiceEmissionExceedanceNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export abstract class ServiceEmissionExceedanceNumber {
	id:number;
	site:Site;
	dateCode:number;
	sequence:number;
	status:ExceedanceStatus;
	monitoringPoints:MonitoringPoint[];
}