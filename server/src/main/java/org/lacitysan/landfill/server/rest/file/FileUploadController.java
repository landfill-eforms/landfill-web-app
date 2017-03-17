package org.lacitysan.landfill.server.rest.file;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.service.mobile.MobileDataDeserializer;
import org.lacitysan.landfill.server.service.mobile.model.MobileDataContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/upload")
@RestController
public class FileUploadController {

	@Autowired
	MobileDataDeserializer mobileDataDeserializer;
	
	@RequestMapping(value="/mobile", method=RequestMethod.POST)
	public Object uploadMobileData(@RequestBody MultipartFile file) {
		try {
			System.out.println("FILE SIZE: " + file.getSize());
			ObjectMapper mapper = new ObjectMapper();
			MobileDataContainer rawData = mapper.readValue(file.getBytes(), new TypeReference<MobileDataContainer>(){});
			rawData.setFilename(file.getOriginalFilename());
			return mobileDataDeserializer.deserializeData(rawData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
