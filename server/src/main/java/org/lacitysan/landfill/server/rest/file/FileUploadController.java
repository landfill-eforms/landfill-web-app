package org.lacitysan.landfill.server.rest.file;

import java.io.IOException;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.exception.FileProcessingException;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.mobile.MobileDataDeserializer;
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

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/upload")
@RestController
public class FileUploadController {

	@Autowired
	MobileDataDeserializer mobileDataDeserializer;

	@RestSecurity(UserPermission.UPLOAD_MOBILE_DATA)
	@RequestMapping(value="/mobile", method=RequestMethod.POST)
	public Object uploadMobileData(@RequestBody MultipartFile file) {
		try {
			if (ApplicationConstant.DEBUG) {
				System.out.println("FILE SIZE: " + file.getSize());
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
			e.printStackTrace();
		}
		return null;
	}

}
