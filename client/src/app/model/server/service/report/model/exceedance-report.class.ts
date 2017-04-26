import { ProbeExceedanceReportData } from './data/probe-exceedance-report-data.class';
import { ServiceEmissionExceedanceReportData } from './data/service-emission-exceedance-report-data.class';
import { Report } from './report.class';

/**
 * This class was automatically generated from ExceedanceReport.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ExceedanceReport extends Report {
	imeReportData:ServiceEmissionExceedanceReportData[];
	iseReportData:ServiceEmissionExceedanceReportData[];
	probeExceedanceReportData:ProbeExceedanceReportData[];
}