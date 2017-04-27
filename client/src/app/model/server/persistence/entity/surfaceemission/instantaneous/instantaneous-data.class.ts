import { SurfaceEmissionData } from '../surface-emission-data.class';
import { ImeNumber } from './ime-number.class';
import { WarmspotData } from './warmspot-data.class';

/**
 * This class was automatically generated from InstantaneousData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class InstantaneousData extends SurfaceEmissionData {
	imeNumbers:ImeNumber[];
	warmspotData:WarmspotData;
}