package org.lacitysan.landfill.server.service.mobile;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.WarmspotDataDao;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetDao;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedIntegratedData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedProbeData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedWarmspotData;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.enums.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.MonitoringPoint;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.lacitysan.landfill.server.service.instantaneous.ImeService;
import org.lacitysan.landfill.server.service.integrated.IseService;
import org.lacitysan.landfill.server.service.mobile.model.MobileDataContainer;
import org.lacitysan.landfill.server.service.mobile.model.MobileImeData;
import org.lacitysan.landfill.server.service.mobile.model.MobileInstantaneousData;
import org.lacitysan.landfill.server.service.mobile.model.MobileIntegratedData;
import org.lacitysan.landfill.server.service.mobile.model.MobileIseData;
import org.lacitysan.landfill.server.service.mobile.model.MobileProbeData;
import org.lacitysan.landfill.server.service.mobile.model.MobileWarmspotData;
import org.lacitysan.landfill.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Deserializer service for converting data models from the mobile app to the web server.
 * @author Alvin Quach
 */
@Service
public class MobileDataDeserializer {
	
	@Autowired
	ImeNumberDao imeNumberDao;
	
	@Autowired
	WarmspotDataDao warmspotDataDao;
	
	@Autowired
	UnverifiedDataSetDao unverifiedDataSetDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ImeService imeService;
	
	@Autowired
	IseService iseService;
	
	@Autowired
	MonitoringPointService monitoringPointService;
	
	public Set<UnverifiedDataSet> deserializeData(MobileDataContainer mobileDataContainer) {
		
		// Store a map of users by their usernames.
		Map<String, User> userMap = new HashMap<>();
		
		// WTF???????????????
		Map<User, Map<Site, UnverifiedDataSet>> resultMap = new HashMap<>();

		// Store a map of imported IME numbers. The value stored for each IME number is its original imported IME number string, if exists.
		Map<ImeNumber, String> imeNumberMap = new HashMap<>();
		
		// Store a map of imported IME numbers. The value stored for each IME number is its original imported IME number string, if exists.
		Set<IseNumber> iseNumberSet = new HashSet<>();
		
		// Fucking want to chop my dick off.
		for (MobileImeData mobileImeData : mobileDataContainer.getmImeDatas()) {
			
			String imeNumberString = mobileImeData.getmImeNumber();

			// If the imported IME data doesn't contain an IME number, then get it from the site and date.
			if (imeNumberString == null || imeNumberString.trim().isEmpty()) {
				
				// Get site.
				Site site = monitoringPointService.getSiteByName(mobileImeData.getmLocation());
				if (site == null) {
					continue;
				}
				
				// Get date and format it into the date code.
				Timestamp date = parseDate(mobileImeData.getmDate());
				if (date == null) {
					continue;
				}
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				int dateCode = (calendar.get(Calendar.YEAR) % 2000) * 100 + calendar.get(Calendar.MONTH) + 1;
				
				// Create new IME number string based on site, date code, and next sequence number.
				imeNumberString = site.getShortName() + "-" + dateCode + "-00";
			}
			
			// Create new IME number based on the IME number string.
			ImeNumber imeNumber = imeService.getImeNumberFromString(imeNumberString);
			if (imeNumber == null) {
				continue;
			}
			
			// Set the status of the IME number as unverified.
			imeNumber.setStatus(ExceedanceStatus.UNVERIFIED);
			
			// Create initial IME data entry based on imported info.
			ImeData imeData = new ImeData();
			//imeData.setId(0);
			imeData.setDateTime(parseDate(mobileImeData.getmDate()));
			imeData.setDescription(mobileImeData.getmDescription());
			imeData.setMethaneLevel((int)(mobileImeData.getmMethaneReading() * 100));
			imeData.setInspector(getUser(userMap, mobileImeData.getmInspectorUserName()));
			imeData.setImeNumber(imeNumber);
			imeNumber.getImeData().add(imeData);
			
			// Add the IME number to the set of IME numbers.
			imeNumberMap.put(imeNumber, imeNumberString);
		}
		
		// Process the instantaneous data entries.
		for (MobileInstantaneousData mobileInstantaneousData : mobileDataContainer.getmInstantaneousDatas()) {

			UnverifiedInstantaneousData unverifiedInstantaneousData = new UnverifiedInstantaneousData();
			
			// TODO Implement this inside the enum.
			if (mobileInstantaneousData.getGridId() != null && !mobileInstantaneousData.getGridId().isEmpty() && mobileInstantaneousData.getmLocation() != null && !mobileInstantaneousData.getmLocation().isEmpty()) {
				MonitoringPoint grid = monitoringPointService.getGridBySiteNameAndId(monitoringPointService.getSiteByName(mobileInstantaneousData.getmLocation()), mobileInstantaneousData.getGridId());
				if (grid == null) {
					if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tError Unmapping Instantaneous Data: Grid " + mobileInstantaneousData.getGridId() + " in " + mobileInstantaneousData.getmLocation() + " not found.");
					return null;
				}
				unverifiedInstantaneousData.setMonitoringPoint(grid);
			}
			if (mobileInstantaneousData.getImeNumber() != null && !mobileInstantaneousData.getImeNumber().isEmpty()) {
				ImeNumber imeNumber = null;
				for (ImeNumber existingImeNumber : imeNumberMap.keySet()) {
					if (imeNumberMap.get(existingImeNumber).equals(mobileInstantaneousData.getImeNumber())) {
						imeNumber = existingImeNumber;
						break;
					}
				}
				if (imeNumber == null) {
					ImeNumber newImeNumber = imeService.getImeNumberFromString(mobileInstantaneousData.getImeNumber());
					if (newImeNumber != null) {
						short sequence = imeService.getNextSequence(newImeNumber.getSite(), newImeNumber.getDateCode(), false);
						newImeNumber.setSequence(sequence);
						newImeNumber.setStatus(ExceedanceStatus.UNVERIFIED);
						imeNumberMap.put(newImeNumber, mobileInstantaneousData.getImeNumber());
						imeNumber = newImeNumber;
					}
				}
				if (imeNumber != null) {
					imeNumber.getUnverifiedInstantaneousData().add(unverifiedInstantaneousData);
					unverifiedInstantaneousData.getImeNumbers().add(imeNumber);
				}
			}
			unverifiedInstantaneousData.setMethaneLevel((int)(mobileInstantaneousData.getMethaneReading() * 100));
			unverifiedInstantaneousData.setStartTime(parseDate(mobileInstantaneousData.getmStartDate()));
			unverifiedInstantaneousData.setEndTime(parseDate(mobileInstantaneousData.getmEndDate()));
			
			// Set barometric pressure
			if (mobileInstantaneousData.getmBarometricPressure() != null) {
				unverifiedInstantaneousData.setBarometricPressure((short)(mobileInstantaneousData.getmBarometricPressure() * 100));
			}
			
			// Get user information.
			User user = getUser(userMap, mobileInstantaneousData.getmInspectorUserName());
			if (user == null) {
				continue;
			}
			if (!resultMap.containsKey(user)) {
				Map<Site, UnverifiedDataSet> wtf = new HashMap<>();
				resultMap.put(user, wtf);
			}
			UnverifiedDataSet unverifiedDataSet = getDataSet(resultMap.get(user), monitoringPointService.getSiteByName(mobileInstantaneousData.getmLocation()));
			if (unverifiedDataSet.getInspector() == null) {
				unverifiedDataSet.setInspector(user);
			}

			unverifiedInstantaneousData.setUnverifiedDataSet(unverifiedDataSet);
			unverifiedDataSet.getUnverifiedInstantaneousData().add(unverifiedInstantaneousData);
		}
		
		// Process the instantaneous warmspot data entries.
		for (MobileWarmspotData mobileWarmspotData : mobileDataContainer.getmWarmSpotDatas()) {
			UnverifiedWarmspotData warmspotData = new UnverifiedWarmspotData();
			warmspotData.setDescription(mobileWarmspotData.getmDescription());
			warmspotData.setSize(String.valueOf(mobileWarmspotData.getmEstimatedSize()));
			User user = getUser(userMap, mobileWarmspotData.getmInspectorUserName());
			if (user == null || !resultMap.containsKey(user)) {
				continue;
			}
			Site site = monitoringPointService.getSiteByName(mobileWarmspotData.getmLocation());
			MonitoringPoint grid = monitoringPointService.getGridBySiteNameAndId(site, mobileWarmspotData.getmGridId());
			if (grid == null) {
				if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tError Unmapping Instantaneous Data: Grid " + mobileWarmspotData.getmGridId() + " in " + mobileWarmspotData.getmLocation() + " not found.");
				return null;
			}
			UnverifiedDataSet unverifiedDataSet = getDataSet(resultMap.get(user), site);
			
			// TODO Consider reversing the loops.
			for (UnverifiedInstantaneousData unverifiedInstantaneousData : unverifiedDataSet.getUnverifiedInstantaneousData()) {
				if (unverifiedInstantaneousData.getMethaneLevel() == (int)(mobileWarmspotData.getmMaxMethaneReading() * 100)) {
					unverifiedInstantaneousData.setUnverifiedWarmspotData(warmspotData);
					break;
				}
			}
		}
		
		// Process the ISE data entries.
		for (MobileIseData mobileIseData : mobileDataContainer.getmIseDatas()) {
			String iseNumberString = mobileIseData.getmIseNumber();

			// If the imported IME data doesn't contain an IME number, then get it from the site and date.
			if (iseNumberString == null || iseNumberString.trim().isEmpty()) {
				
				// Get site.
				Site site = monitoringPointService.getSiteByName(mobileIseData.getmLocation());
				if (site == null) {
					continue;
				}
				
				// Get date and format it into the date code.
				Timestamp date = parseDate(mobileIseData.getmDate());
				if (date == null) {
					continue;
				}
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				int dateCode = (calendar.get(Calendar.YEAR) % 2000) * 100 + calendar.get(Calendar.MONTH) + 1;
				
				// Create new IME number string based on site, date code, and next sequence number.
				iseNumberString = site.getShortName() + "-" + dateCode + "-00";
			}
			
			// Create new IME number based on the IME number string.
			IseNumber iseNumber = iseService.getIseNumberFromString(iseNumberString);
			if (iseNumber == null) {
				continue;
			}
			
			// Set the status of the IME number as unverified.
			iseNumber.setStatus(ExceedanceStatus.UNVERIFIED);
			
			// Create initial IME data entry based on imported info.
			IseData imeData = new IseData();
			//imeData.setId(0);
			imeData.setDateTime(parseDate(mobileIseData.getmDate()));
			imeData.setDescription(mobileIseData.getmDescription());
			imeData.setMethaneLevel((int)(mobileIseData.getmMethaneReading() * 100));
			imeData.setInspector(getUser(userMap, mobileIseData.getmInspectorUserName()));
			imeData.setIseNumber(iseNumber);
			iseNumber.getIseData().add(imeData);
			
			// Add the IME number to the set of IME numbers.
			iseNumberSet.add(iseNumber);
		}
		
		// Process the integrated data entries.
		for (MobileIntegratedData mobileIntegratedData : mobileDataContainer.getmIntegratedDatas()) {

			UnverifiedIntegratedData unverifiedIntegratedData = new UnverifiedIntegratedData();
			
			// TODO Implement this inside the enum.
			if (mobileIntegratedData.getmGridId() != null && !mobileIntegratedData.getmGridId().isEmpty() && mobileIntegratedData.getmLocation() != null && !mobileIntegratedData.getmLocation().isEmpty()) {
				MonitoringPoint grid = monitoringPointService.getGridBySiteNameAndId(monitoringPointService.getSiteByName(mobileIntegratedData.getmLocation()), mobileIntegratedData.getmGridId());
				if (grid == null) {
					if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tError Unmapping Integrated Data: Grid " + mobileIntegratedData.getmGridId() + " in " + mobileIntegratedData.getmLocation() + " not found.");
					return null;
				}
				unverifiedIntegratedData.setMonitoringPoint(grid);
			}
			
			unverifiedIntegratedData.setBagNumber(mobileIntegratedData.getmBagNumber().shortValue());
			unverifiedIntegratedData.setVolume(mobileIntegratedData.getmVolumeReading().shortValue());
			unverifiedIntegratedData.setMethaneLevel((int)(mobileIntegratedData.getmMethaneReading() * 100));
			unverifiedIntegratedData.setStartTime(parseDate(mobileIntegratedData.getmStartDate()));
			unverifiedIntegratedData.setEndTime(parseDate(mobileIntegratedData.getmEndDate()));
			
			// Set barometric pressure
			if (mobileIntegratedData.getmBarometricPressure() != null) {
				unverifiedIntegratedData.setBarometricPressure((short)(mobileIntegratedData.getmBarometricPressure() * 100));
			}
			
			// Get user information.
			User user = getUser(userMap, mobileIntegratedData.getmInspectorUserName());
			if (user == null) {
				continue;
			}
			if (!resultMap.containsKey(user)) {
				Map<Site, UnverifiedDataSet> wtf = new HashMap<>();
				resultMap.put(user, wtf);
			}
			UnverifiedDataSet unverifiedDataSet = getDataSet(resultMap.get(user), monitoringPointService.getSiteByName(mobileIntegratedData.getmLocation()));
			if (unverifiedDataSet.getInspector() == null) {
				unverifiedDataSet.setInspector(user);
			}

			unverifiedIntegratedData.setUnverifiedDataSet(unverifiedDataSet);
			unverifiedDataSet.getUnverifiedIntegratedData().add(unverifiedIntegratedData);
		}
		
		// Process probe data.
		for (MobileProbeData mobileProbeData : mobileDataContainer.getmProbeDatas()) {
			
			UnverifiedProbeData unverifiedProbeData = new UnverifiedProbeData();
			
			// TODO Implement this inside the enum.
			if (mobileProbeData.getmProbeNumber() != null && !mobileProbeData.getmProbeNumber().isEmpty() && mobileProbeData.getmLocation() != null && !mobileProbeData.getmLocation().isEmpty()) {
				MonitoringPoint probe = monitoringPointService.getProbeBySiteNameAndId(monitoringPointService.getSiteByName(mobileProbeData.getmLocation()), mobileProbeData.getmProbeNumber());
				if (probe == null) {
					if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tError Unmapping Probe Data: Probe " + mobileProbeData.getmProbeNumber() + " in " + mobileProbeData.getmLocation() + " not found.");
					return null;
				}
				unverifiedProbeData.setMonitoringPoint(probe);
			}
			
			unverifiedProbeData.setDate(new Date(parseDate(mobileProbeData.getmDate()).getTime()));
			unverifiedProbeData.setMethaneLevel((int)(mobileProbeData.getmMethanePercentage() * 100));
			unverifiedProbeData.setPressureLevel((int)(mobileProbeData.getmWaterPressure() * 100));
			unverifiedProbeData.setDescription(mobileProbeData.getmRemarks());
			unverifiedProbeData.setAccessible(false);
			
			// Set barometric pressure
			if (mobileProbeData.getmBarometricPressure() != null) {
				unverifiedProbeData.setBarometricPressure((short)(mobileProbeData.getmBarometricPressure() * 100));
			}
			
			// Get user information.
			User user = getUser(userMap, mobileProbeData.getmInspectorUserName());
			if (user == null) {
				continue;
			}
			unverifiedProbeData.getInspectors().add(user);

			UnverifiedDataSet unverifiedDataSet = getDataSet(resultMap.get(user), monitoringPointService.getSiteByName(mobileProbeData.getmLocation()));
			if (unverifiedDataSet.getInspector() == null) {
				unverifiedDataSet.setInspector(user);
			}

			unverifiedProbeData.setUnverifiedDataSet(unverifiedDataSet);
			unverifiedDataSet.getUnverifiedProbeData().add(unverifiedProbeData);
		}
		
		// *** If it got to this point in the code, then that means there was no 'errors' with the mobile data.
				
		Set<UnverifiedDataSet> result = resultMap.values().stream().map(map -> map.values()).flatMap(values -> values.stream()).collect(Collectors.toSet());
		
		// Insert IME numbers into database.
		for (ImeNumber imeNumber : imeNumberMap.keySet()) {
			// TODO Update 'modified by' field.
			imeService.createUnverified(imeNumber);

		}
		
		// Insert unverified data sets into the database.
		for (UnverifiedDataSet unverifiedDataSet : result) {
			unverifiedDataSet.setUploadedBy(userService.getCurrentUser());
			unverifiedDataSet.setUploadedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			unverifiedDataSet.setFilename(mobileDataContainer.getFilename());
			unverifiedDataSetDao.create(unverifiedDataSet);
		}
		
		// Return the set of results.
		return result;
	}

	private UnverifiedDataSet getDataSet(Map<Site, UnverifiedDataSet> map, Site site) {
		
		// If a data set doesn't exist yet for the site, then create a new data set and set its id to 0.
		if (!map.containsKey(site)) {
			UnverifiedDataSet dataSet = new UnverifiedDataSet();
			//dataSet.setId(0);
			dataSet.setSite(site);
			map.put(site, dataSet);
		}
		
		return map.get(site); // Return
	}
	
	private User getUser(Map<String, User> userMap, String username) {
		for (User user : userMap.values()) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		User user = userDao.getUserByUsername(username);
		if (user == null) {
			return null;
		}
		userMap.put(username, user);
		return user;
	}
	
	private Timestamp parseDate(String mobileDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		try {
			return new Timestamp(dateFormat.parse(mobileDate).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

}
