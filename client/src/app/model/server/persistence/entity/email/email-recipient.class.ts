import { EmailRecipientType } from '../../enums/email-recipient-type.enum';
import { ScheduledEmail } from '../scheduled/scheduled-email.class';

/**
 * This class was automatically generated from EmailRecipient.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class EmailRecipient {
	id:number;
	recipientType:EmailRecipientType;
	emailAddress:string;
	name:string;
	scheduledEmail:ScheduledEmail;
}