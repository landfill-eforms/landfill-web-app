import { User } from '../user/user.class';

/**
 * This class was automatically generated from ServiceEmissionExceedanceData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export abstract class ServiceEmissionExceedanceData {
	id:number;
	inspector:User;
	methaneLevel:number;
	dateTime:number;
	description:string;
}