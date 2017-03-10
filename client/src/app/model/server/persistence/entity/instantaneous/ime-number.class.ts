import { InstantaneousData } from './instantaneous-data.class';
import { ImeNumberStatus } from '../../enums/ime-number-status.enum';
import { UnverifiedInstantaneousData } from '../unverified/unverified-instantaneous-data.class';
import { ImeData } from './ime-data.class';
import { MonitoringPoint } from '../../enums/monitoring-point.enum';
import { Site } from '../../enums/site.enum';

/**
 * This class was automatically generated from ImeNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ImeNumber {
	id:number;
	site:Site;
	dateCode:number;
	sequence:number;
	status:ImeNumberStatus;
	monitoringPoints:MonitoringPoint[];
	instantaneousData:InstantaneousData[];
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	imeData:ImeData[];
	imeNumber:string;
}