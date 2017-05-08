import { AbstractEntity } from '../abstract-entity.class';
import { ProbeExceedance } from '../probe/probe-exceedance.class';
import { ImeNumber } from '../surfaceemission/instantaneous/ime-number.class';
import { IseNumber } from '../surfaceemission/integrated/ise-number.class';
import { UnverifiedInstantaneousData } from './unverified-instantaneous-data.class';
import { UnverifiedIntegratedData } from './unverified-integrated-data.class';
import { UnverifiedProbeData } from './unverified-probe-data.class';
import { UnverifiedWarmspotData } from './unverified-warmspot-data.class';
import { User } from '../user/user.class';
import { Site } from '../../enums/location/site.enum';

/**
 * This class was automatically generated from UnverifiedDataSet.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedDataSet extends AbstractEntity {
	filename:string;
	inspector:User;
	site:Site;
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	unverifiedWarmspotData:UnverifiedWarmspotData[];
	imeNumbers:ImeNumber[];
	unverifiedIntegratedData:UnverifiedIntegratedData[];
	iseNumbers:IseNumber[];
	unverifiedProbeData:UnverifiedProbeData[];
	probeExceedances?:ProbeExceedance[];
	createdBy:User;
	createdDate:number;
	modifiedBy:User;
	modifiedDate:number;
}