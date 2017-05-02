import { ImeNumber } from './ime-number.class';
import { ImeRepairData } from './ime-repair-data.class';
import { SurfaceEmissionExceedanceData } from '../surface-emission-exceedance-data.class';

/**
 * This class was automatically generated from ImeData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ImeData extends SurfaceEmissionExceedanceData {
	imeNumber:ImeNumber;
	imeRepairData:ImeRepairData[];
}