import { Site } from '../../model/site.enum';
import { MonitoringPointType } from '../../model/monitoring-point-type.enum';

/**
 * This class was automatically generated from MonitoringPoint.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class MonitoringPoint {
	id:number;
	monitoringPointType:MonitoringPointType;
	monitoringPointName:string;
	site:Site;
}