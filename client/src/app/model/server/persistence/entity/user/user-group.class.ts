import { User } from './user.class';
import { UserPermission } from '../../enums/user/user-permission.enum';

/**
 * This class was automatically generated from UserGroup.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UserGroup {
	id:number;
	name:string;
	description:string;
	users:User[];
	userPermissions:UserPermission[];
	createdBy:User;
	modifiedBy:User;
	modifiedDate:number;
}