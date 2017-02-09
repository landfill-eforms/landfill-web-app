package org.lacitysan.landfill.server.rest.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.model.MonitoringPoint;
import org.lacitysan.landfill.server.model.MonitoringPointType;
import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.persistence.dao.test.SleepTestDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMEData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMENumber;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * For testing purposes.
 * @author Alvin Quach
 */
@RequestMapping(ApplicationProperty.RESOURCE_PATH + "test")
@RestController
public class TestController {

	@Autowired
	SleepTestDao sleepTestDao;
	
	@Autowired
	MonitoringPointService monitoringPointService;
	
	@RequestMapping(value="/sleep/{id}", method=RequestMethod.GET)
	public Object get(@PathVariable Integer id) {
		return sleepTestDao.getSleepById(id);
	}
	
	@RequestMapping(value="/authentication", method=RequestMethod.GET)
	public Object get() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getClass().getName());
		return auth;
	}
	
/*	@RequestMapping(value="/test", method=RequestMethod.GET)
	public void createDummyData()
	{
		
		List<InstantaneousData> dummy = new ArrayList<>();
		List<IMENumber> numbers = new ArrayList<>();
		List<IMEData> imeData = new ArrayList<>();
		List<Integer> methlvl = new ArrayList<>();
		
		double pressure = 0;
		
		//monitoringPointService.
		int count = 0;
		for(int i = 0; i < 999; i++)
		{
			InstantaneousData fillers = new InstantaneousData();
			
			Integer id = 0;
			String name = "";
			MonitoringPoint grid = monitoringPointService.getRandom(Arrays.asList(Site.values()), Arrays.asList(new MonitoringPointType[] {MonitoringPointType.GRID}));
			
			Instrument instrument = new Instrument();
			instrument.setId(1);
			
			User user = new User();
			user.setId(1);
			
			pressure = 29.50 + Math.random();
			Short barometricPressure = (short)(pressure*100);
			
			Integer methaneLevel = (int) Math.random() * 600;
			
			
			Timestamp startTime = new Timestamp(System.currentTimeMillis());
			Timestamp endTime = new Timestamp(System.currentTimeMillis());
			
			IMENumber imeNumber = new IMENumber();
			imeNumber.setId(1);
			if(methaneLevel > 499) 
			{ 
				count++;
				name = grid.getSite().getShortName() + "-1702-" + count;
				imeNumber.setImeNumber(name);
				imeNumber.getMonitoringPoints().add(grid);
				numbers.add(imeNumber);
				methlvl.add(methaneLevel);
			}
			else 
			{
				imeNumber.setImeNumber("");
			}
			fillers.setId(id);
			fillers.setBarometricPressure(barometricPressure);
			fillers.setEndTime(endTime);
			fillers.setImeNumber(imeNumber);
			fillers.setInstrument(instrument);
			fillers.setMethaneLevel(methaneLevel);
			fillers.setStartTime(startTime);
			fillers.setUser(user);
			
			dummy.add(fillers);
		}
		
		for(int i = 0; i<count; i++)
		{
			IMEData filler2 = new IMEData();
			filler2.setImeNumber(numbers.get(i));
			filler2.setMethaneLevel(methlvl.get(i));
			User user = new User();
			user.setId(1);
			
			Timestamp dateTime = new Timestamp(System.currentTimeMillis());
			filler2.setDateTime(dateTime);
			
			filler2.setDescription("description for ime");
			
			imeData.add(filler2);
		}
		
	}*/
	
}
