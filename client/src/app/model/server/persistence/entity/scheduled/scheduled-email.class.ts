import { Schedule } from './schedule.class';
import { UserGroup } from '../user/user-group.class';
import { EmailRecipient } from '../email/email-recipient.class';

/**
 * This class was automatically generated from ScheduledEmail.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export abstract class ScheduledEmail {
	id:number;
	schedule:Schedule;
	subject:string;
	body:string;
	userGroups:UserGroup[];
	recipients:EmailRecipient[];
}