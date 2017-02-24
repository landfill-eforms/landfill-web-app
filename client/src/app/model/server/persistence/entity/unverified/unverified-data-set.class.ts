import { User } from '../user/user.class';
import { UnverifiedInstantaneousData } from './unverified-instantaneous-data.class';
import { Site } from '../../../model/site.enum';

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
	barometricPressure:number;
	unverifiedInstantaneousData:UnverifiedInstantaneousData[];
	errors:any;
}