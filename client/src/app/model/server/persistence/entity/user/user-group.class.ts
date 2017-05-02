import { UserPermission } from '../../enums/user/user-permission.enum';
import { AbstractEntity } from '../abstract-entity.class';
import { User } from './user.class';

/**
 * This class was automatically generated from UserGroup.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UserGroup extends AbstractEntity {
	name:string;
	description:string;
	users:User[];
	userPermissions:UserPermission[];
	createdBy:User;
	createdDate:number;
	modifiedBy:User;
	modifiedDate:number;
}