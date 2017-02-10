package org.lacitysan.landfill.server.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.IMENumbersDao;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetsDao;
import org.lacitysan.landfill.server.persistence.dao.user.UsersDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMENumber;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.user.User;
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
	UnverifiedDataSetsDao unverifiedDataSetsDao;
	
	@Autowired
	IMENumbersDao imeNumbersDao;
	
	@Autowired
	UsersDao usersDao;

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public Object testUpload(@RequestBody MultipartFile file) {
		System.out.println("FILE SIZE: " + file.getSize());
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<InstantaneousDataMobile> rawList = mapper.readValue(file.getBytes(), new TypeReference<List<InstantaneousDataMobile>>(){});
			Set<UnverifiedInstantaneousData> inst = new HashSet<>();
			List<IMENumber> imeNumbers = new ArrayList<>();
			UnverifiedDataSet dataSet = new UnverifiedDataSet();
			Site site = null;
			
			for (InstantaneousDataMobile raw : rawList) {
				UnverifiedInstantaneousData data = mobileDataMapper.unmapInstantaneousData(raw, imeNumbers);
				data.setUnverifiedDataSet(dataSet);
				inst.add(data);
				if (site == null) {
					site = data.getMonitoringPoint().getSite();
				}
			}
			
			for (IMENumber imeNumber : imeNumbers) {
				imeNumber.setId((Integer)imeNumbersDao.create(imeNumber));
			}
			
			dataSet.setId(0);
			dataSet.setFilename(file.getOriginalFilename());
			dataSet.setSite(site);
			dataSet.setUnverifiedInstantaneousData(inst);
			User inspector = usersDao.getUserByUsername(rawList.get(0).getInspectorUserName());
			if (inspector == null) {
				inspector = new User();
				inspector.setId(1);
			}
			dataSet.setInspector(inspector);
			dataSet.setInspector(inspector);
			dataSet.setUploadedBy(inspector);
			dataSet.setUploadedDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
			Object result = unverifiedDataSetsDao.create(dataSet);
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
