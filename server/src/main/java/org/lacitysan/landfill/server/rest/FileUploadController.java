package org.lacitysan.landfill.server.rest;

import java.util.ArrayList;
import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.entity.InstantaneousData;
import org.lacitysan.landfill.server.service.datamap.mapper.MobileDataMapper;
import org.lacitysan.landfill.server.service.datamap.model.mobile.InstantaneousDataMobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/file")
@RestController
public class FileUploadController {
	
	@Autowired
	MobileDataMapper mobileDataMapper;
	
	@Autowired
	InstantaneousDataDao instantaneousDataDao;

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public Object testUpload(@RequestBody MultipartFile file) {
		System.out.println("FILE SIZE: " + file.getSize());
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<InstantaneousDataMobile> rawList = mapper.readValue(file.getBytes(), new TypeReference<List<InstantaneousDataMobile>>(){});
			List<InstantaneousData> result = new ArrayList<>();
			for (InstantaneousDataMobile raw : rawList) {
				InstantaneousData data = mobileDataMapper.unmapInstantaneousData(raw);
				instantaneousDataDao.save(data);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
