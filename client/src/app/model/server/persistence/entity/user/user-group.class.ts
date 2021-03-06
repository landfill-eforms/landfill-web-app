import { AbstractEntity } from '../abstract-entity.class';
import { User } from './user.class';
import { UserPermission } from '../../enums/user/user-permission.enum';

/**
 * This class was automatically generated from UserGroup.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UserGroup extends AbstractEntity {
	name: string;
	description: string;
	inspectorGroupFlag: boolean;
	users: User[];
	userPermissions: UserPermission[];
	createdBy: User;
	createdDate: number;
	modifiedBy: User;
	modifiedDate: number;
}