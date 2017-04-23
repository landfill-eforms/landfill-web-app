package org.lacitysan.landfill.server.rest;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.persistence.enums.report.ReportType;
import org.lacitysan.landfill.server.service.report.ReportService;
import org.lacitysan.landfill.server.service.report.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/test")
@RestController
public class TestController {

	@Autowired
	ReportService reportService;
	
	@RequestMapping(value="/asdf", method=RequestMethod.GET)
	public Report reportTest() {
		ReportQuery reportQuery = new ReportQuery();
		reportQuery.setReportType(ReportType.INSTANTANEOUS);
		reportQuery.getSites().add(Site.LOPEZ);
		//reportQuery.getSites().add(Site.BISHOPS);
		//reportQuery.setStartDate(startDate);
		return reportService.generateReport(reportQuery);
	}

}
