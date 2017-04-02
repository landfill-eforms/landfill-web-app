import { User } from '../user/user.class';
import { ImeData } from './ime-data.class';

/**
 * This class was automatically generated from ImeRepairData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ImeRepairData {
	id:number;
	user:User;
	dateTime:number;
	water:boolean;
	soil:boolean;
	description:string;
	crew:string;
	imeData:ImeData;
}