import { AbstractEntity } from '../abstract-entity.class';
import { UnverifiedDataSet } from '../unverified/unverified-data-set.class';
import { ExceedanceStatus } from '../../enums/exceedance/exceedance-status.enum';
import { Site } from '../../enums/location/site.enum';

/**
 * This class was automatically generated from SurfaceEmissionExceedanceNumber.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export abstract class SurfaceEmissionExceedanceNumber extends AbstractEntity {
	site: Site;
	dateCode: number;
	sequence: number;
	status: ExceedanceStatus;
	unverifiedDataSet: UnverifiedDataSet;
}