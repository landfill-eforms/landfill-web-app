import { AbstractEntity } from '../abstract-entity.class';

/**
 * This class was automatically generated from SurfaceEmissionExceedanceRepairData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export abstract class SurfaceEmissionExceedanceRepairData extends AbstractEntity {
	dateTime: number;
	water: boolean;
	soil: boolean;
	description: string;
	crew: string;
}