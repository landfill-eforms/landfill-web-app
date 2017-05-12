package org.lacitysan.landfill.server.report;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.report.IndividualReportQuery;
import org.lacitysan.landfill.server.service.report.ReportService;
import org.lacitysan.landfill.server.service.report.model.Report;
import org.lacitysan.landfill.server.util.DateTimeUtils;
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
	
	@RequestMapping(value="/download", method=RequestMethod.POST)
	public void getTestPdf(HttpServletResponse response, @RequestBody IndividualReportQuery reportQuery) {
		try {

			// Set response headers. This needs to be done before writing to the response's output stream.
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=\"test_" + DateTimeUtils.formatCondensed(Calendar.getInstance().getTimeInMillis()) + ".pdf\"");
			response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
			
			reportService.generateReportPdf(response, reportQuery);
			response.flushBuffer();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
