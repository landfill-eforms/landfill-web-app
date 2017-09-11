import { SurfaceEmissionExceedanceNumber } from '../surface-emission-exceedance-number.class';
import { IseData } from './ise-data.class';
import { MonitoringPoint } from '../../../enums/location/monitoring-point.enum';

/**
 * This class was automatically generated from IseNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IseNumber extends SurfaceEmissionExceedanceNumber {
	monitoringPoint: MonitoringPoint;
	iseData: IseData[];
	iseNumber?: string;
}