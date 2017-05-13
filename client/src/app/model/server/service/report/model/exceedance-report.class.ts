import { Report } from './report.class';
import { ProbeExceedanceReportData } from './data/probe-exceedance-report-data.class';
import { SurfaceEmissionExceedanceReportData } from './data/surface-emission-exceedance-report-data.class';

/**
 * This class was automatically generated from ExceedanceReport.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ExceedanceReport extends Report {
	imeReportData:SurfaceEmissionExceedanceReportData[];
	iseReportData:SurfaceEmissionExceedanceReportData[];
	probeExceedanceReportData:ProbeExceedanceReportData[];
}