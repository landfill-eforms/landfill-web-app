import { UnverifiedProbeData } from './unverified-probe-data.class';
import { ImeNumber } from '../surfaceemission/instantaneous/ime-number.class';
import { ProbeExceedance } from '../probe/probe-exceedance.class';
import { IseNumber } from '../surfaceemission/integrated/ise-number.class';
import { AbstractEntity } from '../abstract-entity.class';
import { Site } from '../../enums/location/site.enum';
import { User } from '../user/user.class';
import { UnverifiedWarmspotData } from './unverified-warmspot-data.class';
import { UnverifiedInstantaneousData } from './unverified-instantaneous-data.class';
import { UnverifiedIntegratedData } from './unverified-integrated-data.class';

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
	probeExceedances:ProbeExceedance[];
	errors:any;
	createdBy:User;
	createdDate:number;
	modifiedBy:User;
	modifiedDate:number;
}