import { User } from './user.class';
import { UserRole } from '../../enums/user-role.enum';

/**
 * This class was automatically generated from UserGroup.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UserGroup {
	id:number;
	name:string;
	description:string;
	createdBy:User;
	modifiedBy:User;
	users:User[];
	userRoles:UserRole[];
}