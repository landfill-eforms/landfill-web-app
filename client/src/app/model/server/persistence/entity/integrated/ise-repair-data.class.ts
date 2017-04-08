import { IseData } from './ise-data.class';
import { User } from '../user/user.class';

/**
 * This class was automatically generated from IseRepairData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IseRepairData {
	id:number;
	user:User;
	dateTime:number;
	water:boolean;
	soil:boolean;
	description:string;
	crew:string;
	iseData:IseData;
}