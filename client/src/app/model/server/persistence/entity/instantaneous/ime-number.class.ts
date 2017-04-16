import { ExceedanceStatus } from '../../enums/exceedance/exceedance-status.enum';
import { InstantaneousData } from './instantaneous-data.class';
import { Site } from '../../enums/location/site.enum';
import { MonitoringPoint } from '../../enums/location/monitoring-point.enum';
import { UnverifiedInstantaneousData } from '../unverified/unverified-instantaneous-data.class';
import { ImeData } from './ime-data.class';

/**
 * This class was automatically generated from ImeNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ImeNumber {
	id:number;
	site:Site;
	dateCode:number;
	sequence:number;
	status:ExceedanceStatus;
	monitoringPoints:MonitoringPoint[];
	instantaneousData:InstantaneousData[];
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	imeData:ImeData[];
	imeNumber:string;
}