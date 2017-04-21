import { InstantaneousData } from './instantaneous-data.class';
import { ServiceEmissionExceedanceNumber } from '../service-emission-exceedance-number.class';
import { UnverifiedInstantaneousData } from '../../unverified/unverified-instantaneous-data.class';
import { ImeData } from './ime-data.class';

/**
 * This class was automatically generated from ImeNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ImeNumber extends ServiceEmissionExceedanceNumber {
	instantaneousData:InstantaneousData[];
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	imeData:ImeData[];
	imeNumber:string;
}