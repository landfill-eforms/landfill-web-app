import { UnverifiedProbeData } from './unverified-probe-data.class';
import { User } from '../user/user.class';
import { UnverifiedIntegratedData } from './unverified-integrated-data.class';
import { Site } from '../../enums/location/site.enum';
import { UnverifiedInstantaneousData } from './unverified-instantaneous-data.class';

/**
 * This class was automatically generated from UnverifiedDataSet.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedDataSet {
	id:number;
	filename:string;
	inspector:User;
	site:Site;
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	unverifiedIntegratedData:UnverifiedIntegratedData[];
	unverifiedProbeData:UnverifiedProbeData[];
	errors:any;
	createdBy:User;
	modifiedBy:User;
	modifiedDate:number;
}