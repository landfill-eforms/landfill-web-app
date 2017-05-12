package org.lacitysan.landfill.server.report;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.report.IndividualReportQuery;
import org.lacitysan.landfill.server.service.report.ReportService;
import org.lacitysan.landfill.server.service.report.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/report")
@RestController
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	@RequestMapping(value="/preview", method=RequestMethod.POST)
	public Report previewReport(@RequestBody IndividualReportQuery reportQuery) {
		return reportService.generateReport(reportQuery);
	}

}
