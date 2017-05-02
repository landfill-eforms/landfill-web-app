import { AbstractEntity } from '../abstract-entity.class';
import { User } from './user.class';

/**
 * This class was automatically generated from UserActivity.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UserActivity extends AbstractEntity {
	user:User;
	date:number;
	activity:string;
}