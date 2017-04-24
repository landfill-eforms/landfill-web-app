package org.lacitysan.landfill.server.rest.file;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.mobile.MobileDataSerializer;
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

	@RestSecurity(UserPermission.DOWNLOAD_MOBILE_DATA)
	@RequestMapping(value="/mobile/dump", method=RequestMethod.GET)
	public void getMobileDataDump(HttpServletResponse response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String source = mapper.writeValueAsString(mobileDataSerializer.generateDataDump());
			InputStream in = IOUtils.toInputStream(source, "UTF-8");
			IOUtils.copy(in, response.getOutputStream());
			response.setContentType("application/json");
			response.flushBuffer();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
