package org.lacitysan.landfill.server.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.model.MonitoringPoint;
import org.lacitysan.landfill.server.model.MonitoringPointType;
import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.IMENumberDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMEData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMENumber;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMERepairData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 * @author Allen Huang
 */
@Service
public class UnverifiedDataService {
	
	@Autowired
	MonitoringPointService monitoringPointService;
	
	@Autowired
	IMENumberDao imeNumberDao;
	
	public Set<InstantaneousData> verifyInstantaneousData(UnverifiedDataSet dataSet) {
		Set<InstantaneousData> result = new HashSet<>();
		Short barometricPressure = dataSet.getBarometricPressure();
		if (barometricPressure == null) {
			// TODO Check range of barometric pressure.
			return null;
		}
		for (UnverifiedInstantaneousData data : dataSet.getUnverifiedInstantaneousData()) {
			InstantaneousData instantaneousData = new InstantaneousData();
			instantaneousData.setId(0);
			if (data.getMethaneLevel() >= 50000) { 
				if (data.getImeNumber() == null) {
					return null;
				}
				//instantaneousData.setImeNumber(data.getImeNumber());
			}
			else if (data.getMethaneLevel() < 50000 && data.getImeNumber() != null) {
				return null;
			}
			instantaneousData.setInspector(dataSet.getInspector());
			instantaneousData.setBarometricPressure(barometricPressure);
			instantaneousData.setStartTime(data.getStartTime());
			instantaneousData.setEndTime(data.getEndTime());
			instantaneousData.setInstrument(data.getInstrument());
			instantaneousData.setMonitoringPoint(data.getMonitoringPoint());
			instantaneousData.setMethaneLevel(data.getMethaneLevel());
			result.add(instantaneousData);
		}
		return result;
	}
	
	public UnverifiedDataSet createDummyData() {
		
		List<Site> sites = Arrays.asList(Site.values()).stream().filter(site -> site.isActive()).collect(Collectors.toList());
		Site site = sites.get(new Random().nextInt(sites.size()));
		Long date = Calendar.getInstance().getTime().getTime();
		User inspector = new User();
		inspector.setId(1);
		
		UnverifiedDataSet dataSet = new UnverifiedDataSet();
		dataSet.setId(0);
		dataSet.setFilename("dummy-" + date + ".exe");
		dataSet.setSite(site);
		dataSet.setInspector(inspector);
		dataSet.setUploadedBy(inspector);
		dataSet.setUploadedDate(new Timestamp(date));
		
		Instrument instrument = new Instrument();
		instrument.setId(1);
		Long baseStartTime = date - 1000 * 60 * 60 * 24 * new Random().nextInt(60);
		Set<MonitoringPoint> usedMonitoringPoints = new HashSet<>();
		for (int i = 0; i < new Random().nextInt(20); i++) {
			Long startTime = baseStartTime - 1000 * 60 * new Random().nextInt(180);
			UnverifiedInstantaneousData instantaneousData = new UnverifiedInstantaneousData();
			while (true) {
				MonitoringPoint grid = monitoringPointService.getRandom(MonitoringPointType.GRID, site);
				if (usedMonitoringPoints.add(grid)) {
					instantaneousData.setMonitoringPoint(grid);
					break;
				}
			}
			instantaneousData.setInstrument(instrument);
			instantaneousData.setMethaneLevel(new Random().nextInt(1337) * 100);
			instantaneousData.setStartTime(new Timestamp(startTime));
			instantaneousData.setEndTime(new Timestamp(startTime + 1000 * 60 * 30));		
			instantaneousData.setUnverifiedDataSet(dataSet);
			
			if(instantaneousData.getMethaneLevel()> 50000)
			{
				IMENumber num = new IMENumber();
				num.setId(0);
				num.setSite(site);
				
				// TODO Fix this.
				//num.setDiscoveryDate(instantaneousData.getStartTime());
				
				short series;
				Set<InstantaneousData> instant = new HashSet();
				
				InstantaneousData data = new InstantaneousData();
				data.setId(dataSet.getId());
				data.setBarometricPressure(dataSet.getBarometricPressure());
				data.setEndTime(instantaneousData.getEndTime());
				
				IMENumber ime = new IMENumber();
				//data.setImeNumber(ime);
				
				data.setInspector(dataSet.getInspector());
				data.setInstrument(instantaneousData.getInstrument());
				data.setMethaneLevel(instantaneousData.getMethaneLevel());
				data.setMonitoringPoint(instantaneousData.getMonitoringPoint());
				data.setStartTime(instantaneousData.getStartTime());
				
				instant.add(data);
				num.setInstantaneousData(instant);
				
				Set<IMEData> imeData = new HashSet();
				num.setImeData(imeData);
				
				Set<IMERepairData> imeRepairData = new HashSet();
				//num.setImeRepairData(imeRepairData);
				
				Set<MonitoringPoint> monitoringPoints = new HashSet<MonitoringPoint>();
				monitoringPoints.add(instantaneousData.getMonitoringPoint());
				num.setMonitoringPoints(monitoringPoints);
				instantaneousData.setImeNumber(num);
			}		
			
			dataSet.getUnverifiedInstantaneousData().add(instantaneousData);
		
		}
		return dataSet;
		
	}
	
	
	
//	public void verifyData(UnverifiedDataSet dataSet) {
//		
//		List<String> instantaneousErrors = new ArrayList<>();
//		
//		// Check if all the data's monitoring points belong to the correct site.
//		for (UnverifiedInstantaneousData instantaneousData : dataSet.getUnverifiedInstantaneousData()) {
//			if (instantaneousData.getMonitoringPoint().getSite() != dataSet.getSite()) {
//				instantaneousErrors.add(")
//			}
//		}
//		
//	}
//	
//	private String getInstantaneousDataDescriptor(UnverifiedInstantaneousData instantaneousData) {
//		return "";
//	}

}
