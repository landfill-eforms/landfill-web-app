package org.lacitysan.landfill.server.rest;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.report.IndividualReportQuery;
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
	public Report reportTest() throws ParseException {
		ReportQuery reportQuery = new IndividualReportQuery();
		reportQuery.setReportType(ReportType.INSTANTANEOUS);
//		reportQuery.setSite(Site.LOPEZ);
		reportQuery.setSite(Site.BISHOPS);
		reportQuery.setStartDate(new Date(new SimpleDateFormat("M/d/yyyy").parse("4/15/2017").getTime()));
		reportQuery.setEndDate(new Date(new SimpleDateFormat("M/d/yyyy").parse("4/16/2017").getTime()));
		return reportService.generateReport(reportQuery);
	}

}
