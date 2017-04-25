import { ServiceEmissionExceedanceNumber } from '../service-emission-exceedance-number.class';
import { IseData } from './ise-data.class';

/**
 * This class was automatically generated from IseNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IseNumber extends ServiceEmissionExceedanceNumber {
	iseData:IseData[];
	iseNumber:string;
}