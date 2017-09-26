package org.lacitysan.landfill.server.rest.mobile;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.exception.FileProcessingException;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.mobile.MobileDataDeserializer;
import org.lacitysan.landfill.server.service.mobile.MobileDataSerializer;
import org.lacitysan.landfill.server.service.mobile.model.MobileDataContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/mobile")
@RestController
public class MobileDataController {

	@Autowired
	MobileDataSerializer mobileDataSerializer;
	
	@Autowired
	MobileDataDeserializer mobileDataDeserializer;

	@RestSecurity(UserPermission.UPLOAD_MOBILE_DATA)
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public Object uploadMobileData(@RequestBody MultipartFile file) {
		try {
			if (ApplicationConstant.DEBUG) {
				System.out.println("File Uploaded. Size: " + file.getSize() + " bytes");
			}
			ObjectMapper mapper = new ObjectMapper();
			MobileDataContainer rawData = mapper.readValue(file.getBytes(), new TypeReference<MobileDataContainer>(){});
			rawData.setFilename(file.getOriginalFilename());
			return mobileDataDeserializer.deserializeData(rawData);
		} 
		catch (JsonParseException e) { 
			throw new FileProcessingException("File is not a valid JSON file.");
		}
		catch (IOException e) {
			// TODO Find out under which conditions this exception is thrown.
			throw new FileProcessingException("File cannot be processed.");
		}
	}
	
	@RestSecurity(UserPermission.DOWNLOAD_MOBILE_DATA)
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void downloadMobileData(HttpServletResponse response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String source = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mobileDataSerializer.generateDataDump());
			InputStream in = IOUtils.toInputStream(source, "UTF-8");
			
			// Set response headers. This needs to be done before writing to the response's output stream.
			response.setContentType("application/json");
			response.addHeader("Content-Disposition", "attachment; filename=\"LandFillDataImport.json\"");
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
