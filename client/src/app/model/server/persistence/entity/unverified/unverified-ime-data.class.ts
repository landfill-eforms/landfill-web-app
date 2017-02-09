import { IMENumber } from '../instantaneous/ime-number.class';
import { User } from '../user/user.class';

/**
 * This class was automatically generated from UnverifiedIMEData.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UnverifiedIMEData {
	id:number;
	imeNumber:IMENumber;
	inspector:User;
	methaneLevel:number;
	dateTime:number;
	description:string;
}