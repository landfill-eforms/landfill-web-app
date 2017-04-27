import { ImeData } from './ime-data.class';
import { InstantaneousData } from './instantaneous-data.class';
import { SurfaceEmissionExceedanceNumber } from '../surface-emission-exceedance-number.class';
import { UnverifiedInstantaneousData } from '../../unverified/unverified-instantaneous-data.class';

/**
 * This class was automatically generated from ImeNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ImeNumber extends SurfaceEmissionExceedanceNumber {
	instantaneousData:InstantaneousData[];
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	imeData:ImeData[];
	imeNumber:string;
}