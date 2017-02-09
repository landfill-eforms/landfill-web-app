package org.lacitysan.landfill.server.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.springframework.stereotype.Service;

@Service
public class UnverifiedDataService {
	
	public UnverifiedDataSet createDummyData() {
		
		Site[] sites = Site.values();
		Site site = sites[new Random().nextInt(sites.length)];
		Long date = Calendar.getInstance().getTime().getTime();
		User inspector = new User();
		inspector.setId(1);
		
		UnverifiedDataSet data = new UnverifiedDataSet();
		data.setId(0);
		data.setFilename("dummy-" + date + ".exe");
		data.setSite(site);
		data.setInspector(inspector);
		data.setUploadedBy(inspector);
		data.setUploadedDate(new Timestamp(date));
		
		return data;
		
	}

}
