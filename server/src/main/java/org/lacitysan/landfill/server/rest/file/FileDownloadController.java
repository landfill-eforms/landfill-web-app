package org.lacitysan.landfill.server.rest.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.report.ReportExport;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.mobile.MobileDataSerializer;
import org.lacitysan.landfill.server.service.report.ReportService;
import org.lacitysan.landfill.server.service.report.model.ExceedanceReport;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/download")
@RestController
public class FileDownloadController {

	@Autowired
	MobileDataSerializer mobileDataSerializer;
	
	@Autowired
	ReportService reportService;

	@RestSecurity(UserPermission.DOWNLOAD_MOBILE_DATA)
	@RequestMapping(value="/mobile/dump", method=RequestMethod.GET)
	public void getMobileDataDump(HttpServletResponse response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String source = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mobileDataSerializer.generateDataDump());
			InputStream in = IOUtils.toInputStream(source, "UTF-8");
			
			// Set response headers. This needs to be done before writing to the response's output stream.
			response.setContentType("application/json");
			response.addHeader("Content-Disposition", "attachment; filename=\"mobile_sync_" + DateTimeUtils.formatCondensed(Calendar.getInstance().getTimeInMillis()) + ".json\"");
			response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");

			IOUtils.copy(in, response.getOutputStream());
			response.flushBuffer();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/pdf/test", method=RequestMethod.GET)
	public void getTestPdf(HttpServletResponse response) {
		try {
			InputStream in = new FileInputStream(new File("D:/Alvin/Documents/CS4440 HW6.pdf"));

			// Set response headers. This needs to be done before writing to the response's output stream.
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=\"test_" + DateTimeUtils.formatCondensed(Calendar.getInstance().getTimeInMillis()) + ".pdf\"");
			response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
			
			ReportExport.createExceedanceReport(response, null);
			response.flushBuffer();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// This is for the demo. Remove when no longer needed.
	@RestSecurity(UserPermission.DOWNLOAD_MOBILE_DATA)
	@RequestMapping(value="/pdf/fake", method=RequestMethod.GET)
	public void getFakePdf(HttpServletResponse response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String source = mapper.writerWithDefaultPrettyPrinter().writeValueAsString("HI ALLEN");
			InputStream in = IOUtils.toInputStream(source, "UTF-8");
			
			// Set response headers. This needs to be done before writing to the response's output stream.
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=\"report_" + DateTimeUtils.formatCondensed(Calendar.getInstance().getTimeInMillis()) + ".pdf\"");
			response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");

			IOUtils.copy(in, response.getOutputStream());
			response.flushBuffer();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
