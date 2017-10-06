import { AbstractEntity } from '../abstract-entity.class';
import { ExceedanceType } from '../../enums/exceedance/exceedance-type.enum';
import { Site } from '../../enums/location/site.enum';
import { ReportPeriod } from '../../enums/report/report-period.enum';
import { ReportType } from '../../enums/report/report-type.enum';

/**
 * This class was automatically generated from ReportQuery.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export abstract class ReportQuery extends AbstractEntity {
	reportType: ReportType;
	site: Site;
	exceedanceTypes: ExceedanceType[];
	reportPeriod: ReportPeriod;
	periodOffset: number;
	startDate: number;
	endDate: number;
	dateCreated: number;
}