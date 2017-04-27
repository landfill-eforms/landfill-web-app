import { IseData } from './ise-data.class';
import { SurfaceEmissionExceedanceNumber } from '../surface-emission-exceedance-number.class';

/**
 * This class was automatically generated from IseNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IseNumber extends SurfaceEmissionExceedanceNumber {
	iseData:IseData[];
	iseNumber:string;
}