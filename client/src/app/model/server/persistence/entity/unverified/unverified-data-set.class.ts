import { UnverifiedIntegratedData } from './unverified-integrated-data.class';
import { User } from '../user/user.class';
import { UnverifiedInstantaneousData } from './unverified-instantaneous-data.class';
import { UnverifiedProbeData } from './unverified-probe-data.class';
import { Site } from '../../enums/location/site.enum';

/**
 * This class was automatically generated from UnverifiedDataSet.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedDataSet {
	id:number;
	filename:string;
	inspector:User;
	site:Site;
	uploadedBy:User;
	uploadedDate:number;
	modifiedBy:User;
	modifiedDate:number;
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	unverifiedIntegratedData:UnverifiedIntegratedData[];
	unverifiedProbeData:UnverifiedProbeData[];
	errors:any;
}