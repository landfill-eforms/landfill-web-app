import { AbstractEntity } from '../abstract-entity.class';
import { ScheduledEmail } from '../scheduled/scheduled-email.class';
import { EmailRecipientType } from '../../enums/email/email-recipient-type.enum';

/**
 * This class was automatically generated from EmailRecipient.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class EmailRecipient extends AbstractEntity {
	recipientType: EmailRecipientType;
	emailAddress: string;
	name: string;
	scheduledEmail: ScheduledEmail;
}