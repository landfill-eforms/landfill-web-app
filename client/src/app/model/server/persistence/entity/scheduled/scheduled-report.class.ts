import { ScheduledReportQuery } from '../report/scheduled-report-query.class';
import { ScheduledEmail } from './scheduled-email.class';

/**
 * This class was automatically generated from ScheduledReport.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ScheduledReport extends ScheduledEmail {
	reportQueries:ScheduledReportQuery[];
}