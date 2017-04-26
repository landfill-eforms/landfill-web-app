import { ServiceEmissionExceedanceData } from '../service-emission-exceedance-data.class';
import { ImeNumber } from './ime-number.class';
import { ImeRepairData } from './ime-repair-data.class';

/**
 * This class was automatically generated from ImeData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ImeData extends ServiceEmissionExceedanceData {
	imeNumber:ImeNumber;
	imeRepairData:ImeRepairData[];
}