import { UnverifiedDataSet } from '../unverified/unverified-data-set.class';
import { Site } from '../../enums/location/site.enum';
import { ExceedanceStatus } from '../../enums/exceedance/exceedance-status.enum';

/**
 * This class was automatically generated from SurfaceEmissionExceedanceNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export abstract class SurfaceEmissionExceedanceNumber {
	id:number;
	site:Site;
	dateCode:number;
	sequence:number;
	status:ExceedanceStatus;
	unverifiedDataSet:UnverifiedDataSet;
}