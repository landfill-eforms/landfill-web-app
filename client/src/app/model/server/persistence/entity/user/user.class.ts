import { UserGroup } from './user-group.class';
import { Person } from './person.class';

/**
 * This class was automatically generated from User.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class User {
	id:number;
	username:string;
	password:string;
	userGroups:UserGroup[];
	enabled:boolean;
	person:Person;
}