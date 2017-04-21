import { ServiceEmissionExceedanceData } from '../service-emission-exceedance-data.class';
import { IseNumber } from './ise-number.class';
import { IseRepairData } from './ise-repair-data.class';

/**
 * This class was automatically generated from IseData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IseData extends ServiceEmissionExceedanceData {
	iseNumber:IseNumber;
	iseRepairData:IseRepairData[];
}