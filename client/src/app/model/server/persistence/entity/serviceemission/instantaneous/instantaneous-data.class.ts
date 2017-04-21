import { WarmspotData } from './warmspot-data.class';
import { ServiceEmissionData } from '../service-emission-data.class';
import { ImeNumber } from './ime-number.class';

/**
 * This class was automatically generated from InstantaneousData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class InstantaneousData extends ServiceEmissionData {
	imeNumbers:ImeNumber[];
	warmspotData:WarmspotData;
}