import { IseRepairData } from './ise-repair-data.class';
import { IseNumber } from './ise-number.class';
import { SurfaceEmissionExceedanceData } from '../surface-emission-exceedance-data.class';

/**
 * This class was automatically generated from IseData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IseData extends SurfaceEmissionExceedanceData {
	iseNumber:IseNumber;
	iseRepairData:IseRepairData[];
}