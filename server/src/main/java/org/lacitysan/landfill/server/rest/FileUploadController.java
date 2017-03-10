package org.lacitysan.landfill.server.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetDao;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.lacitysan.landfill.server.service.datamap.mapper.MobileDataDeserializer;
import org.lacitysan.landfill.server.service.datamap.model.mobile.MobileInstantaneousData;
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
	MobileDataDeserializer mobileDataDeserializer;
	
	@Autowired
	UnverifiedDataSetDao unverifiedDataSetDao;
	
	@Autowired
	ImeNumberDao imeNumberDao;
	
	@Autowired
	UserDao userDao;

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public Object testUpload(@RequestBody MultipartFile file) {
		System.out.println("FILE SIZE: " + file.getSize());
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<MobileInstantaneousData> rawList = mapper.readValue(file.getBytes(), new TypeReference<List<MobileInstantaneousData>>(){});
			Set<UnverifiedInstantaneousData> inst = new HashSet<>();
			List<ImeNumber> imeNumbers = new ArrayList<>();
			UnverifiedDataSet dataSet = new UnverifiedDataSet();
			Site site = null;
			
			for (MobileInstantaneousData raw : rawList) {
				UnverifiedInstantaneousData data = mobileDataDeserializer.deserializeInstantaneousData(raw, imeNumbers);
				data.setUnverifiedDataSet(dataSet);
				inst.add(data);
				if (site == null) {
					site = data.getMonitoringPoint().getSite();
				}
			}
			
			for (ImeNumber imeNumber : imeNumbers) {
				imeNumber.setId((Integer)imeNumberDao.create(imeNumber));
			}
			
			dataSet.setId(0);
			dataSet.setFilename(file.getOriginalFilename());
			dataSet.setSite(site);
			dataSet.setUnverifiedInstantaneousData(inst);
			User inspector = userDao.getUserByUsername(rawList.get(0).getInspectorUserName());
			if (inspector == null) {
				inspector = new User();
				inspector.setId(1);
			}
			dataSet.setInspector(inspector);
			dataSet.setInspector(inspector);
			dataSet.setUploadedBy(inspector);
			dataSet.setUploadedDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
			Object result = unverifiedDataSetDao.create(dataSet);
			if (result instanceof Integer) {
				dataSet.setId((Integer)result);
			}
			return dataSet;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
