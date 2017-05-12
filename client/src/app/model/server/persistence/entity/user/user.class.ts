import { AbstractEntity } from '../abstract-entity.class';
import { UserGroup } from './user-group.class';

/**
 * This class was automatically generated from User.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class User extends AbstractEntity {
	username:string;
	password:string;
	userGroups:UserGroup[];
	firstname:string;
	middlename:string;
	lastname:string;
	emailAddress:string;
	employeeId:string;
	enabled:boolean;
	lastLogin:number;
	createdBy:User;
	createdDate:number;
	modifiedBy:User;
	modifiedDate:number;
}