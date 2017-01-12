package org.lacitysan.landfill.server.rest;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/file")
@RestController
public class FileUploadController {

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public Object testUpload(@RequestParam MultipartFile file) {
		System.out.println("FILE SIZE: " + file.getSize());
		return null;
	}
	
}
