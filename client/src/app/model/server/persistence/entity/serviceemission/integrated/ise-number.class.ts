import { IseData } from './ise-data.class';
import { ServiceEmissionsExceedanceNumber } from '../service-emissions-exceedance-number.class';

/**
 * This class was automatically generated from IseNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IseNumber extends ServiceEmissionsExceedanceNumber {
	iseData:IseData[];
	iseNumber:string;
}