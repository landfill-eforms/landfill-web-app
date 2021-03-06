package org.lacitysan.landfill.server.service.mobile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.WarmspotDataDao;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedIntegratedData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedProbeData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedWarmspotData;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.lacitysan.landfill.server.service.mobile.model.MobileDataContainer;
import org.lacitysan.landfill.server.service.mobile.model.MobileImeData;
import org.lacitysan.landfill.server.service.mobile.model.MobileInstantaneousData;
import org.lacitysan.landfill.server.service.mobile.model.MobileIntegratedData;
import org.lacitysan.landfill.server.service.mobile.model.MobileIseData;
import org.lacitysan.landfill.server.service.mobile.model.MobileProbeData;
import org.lacitysan.landfill.server.service.mobile.model.MobileWarmspotData;
import org.lacitysan.landfill.server.service.surfaceemission.instantaneous.ImeNumberService;
import org.lacitysan.landfill.server.service.surfaceemission.integrated.IseNumberService;
import org.lacitysan.landfill.server.service.system.TrackingService;
import org.lacitysan.landfill.server.service.unverified.UnverifiedDataSetService;
import org.lacitysan.landfill.server.service.user.UserService;
import org.lacitysan.landfill.server.util.DateTimeUtils;
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
	UserDao userDao;
	
	@Autowired
	InstrumentDao instrumentDao;

	@Autowired
	UnverifiedDataSetService unverifiedDataSetService;

	@Autowired
	UserService userService;

	@Autowired
	ImeNumberService imeNumberService;

	@Autowired
	IseNumberService iseNumberService;

	@Autowired
	MonitoringPointService monitoringPointService;
	
	@Autowired
	TrackingService trackingService;

	public Set<UnverifiedDataSet> deserializeData(MobileDataContainer mobileDataContainer) {

		// Store a map of users by their usernames.
		Map<String, User> userMap = new HashMap<>();

		// WTF???????????????
		Map<User, Map<Site, UnverifiedDataSet>> resultMap = new HashMap<>();

		// Process the IME data entries.
		for (MobileImeData mobileImeData : mobileDataContainer.getmImeDatas()) {

			// Check if the methane level is within valid range.
			// If it is not within valid range, then it is probably bad data
			// exported by the Andriod app, and should just be skipped.
			if (mobileImeData.getmMethaneReading() == null || mobileImeData.getmMethaneReading() < ImeData.MININIMUM_READING / 100.0) {
				continue;
			}

			// Get user information.
			User user = getUser(userMap, mobileImeData.getmInspectorUserName());

			// Get site.
			Site site = monitoringPointService.getSiteByEnumName(mobileImeData.getmLocation());

			// Get corresponding unverified data set.
			UnverifiedDataSet unverifiedDataSet = getUnverifiedDataSet(resultMap, user, site);
			if (unverifiedDataSet == null) {
				continue;
			}

			// Get IME number string from mobile data.
			String imeNumberString = mobileImeData.getmImeNumber();

			// If the imported IME data doesn't contain an IME number, then get it from the site and date.
			if (imeNumberString == null || imeNumberString.trim().isEmpty()) {

				// Get date and format it into the date code.
				Timestamp date = DateTimeUtils.mobileDateToTimestamp(mobileImeData.getmDate());
				int dateCode = imeNumberService.generateDateCodeFromLong(date.getTime());

				// Create new IME number string based on site, date code, and next sequence number.
				imeNumberString = site.getShortName() + "-" + dateCode + "-00";
			}

			// Create new IME number based on the IME number string.
			ImeNumber imeNumber = imeNumberService.generateImeNumberFromString(imeNumberString);
			if (imeNumber == null) {
				continue;
			}
	
			// Parse set of grids from the space delimited string.
			if (mobileImeData.getmGrids() != null) {
				Set<MonitoringPoint> grids = Arrays.stream(mobileImeData.getmGrids().split(", "))
						.map(g -> monitoringPointService.getGridBySiteNameAndId(site, g))
						.filter(g -> g != null)
						.collect(Collectors.toSet()); 

				// TODO Handle case when grids is empty.

				// Add grids to the IME.
				imeNumber.getMonitoringPoints().addAll(grids);
			}

			// Set the status of the IME number as unverified.
			imeNumber.setStatus(ExceedanceStatus.UNVERIFIED);
			
			// Find instrument based provided ID, if any.
			Instrument instrument = null;
			if (mobileImeData.getmInstrument() != null) {
				// TODO Change instrument to integer on Andriod side, so that we don't need to convert string to integer.
				try {
					instrument = instrumentDao.getById(mobileImeData.getmInstrument());
				}
				catch (NumberFormatException e) {
					if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tInstrument ID '" + mobileImeData.getmInstrument() + "' in IME data is not a number. Instrument will be left as null.");
				}
			}

			// Create initial IME data entry based on imported info.
			ImeData imeData = new ImeData();
			imeData.setDateTime(DateTimeUtils.mobileDateToTimestamp(mobileImeData.getmDate()));
			imeData.setDescription(mobileImeData.getmDescription() == null ? "" : mobileImeData.getmDescription());
			imeData.setMethaneLevel((int)(mobileImeData.getmMethaneReading() * 100));
			imeData.setInspector(getUser(userMap, mobileImeData.getmInspectorUserName()));
			imeData.setInstrument(instrument);

			// Add the IME number to the IME data entry and vice versa.
			imeData.setImeNumber(imeNumber);
			imeNumber.getImeData().add(imeData);

			/* 
			 * Add the IME number to the unverified data set and vice versa.
			 * 
			 * NOTE: Do not add unverified data set to IME number here.
			 * Let the checkRelations() method in the UnverifiedDataSetService handle this.
			 */
			unverifiedDataSet.getImeNumbers().add(imeNumber);

		}

		// Process the instantaneous data entries.
		for (MobileInstantaneousData mobileInstantaneousData : mobileDataContainer.getmInstantaneousDatas()) {
			
			// Check if the methane level is null.
			// If it is null, then it is probably bad data exported
			// by the Andriod app, and should just be skipped.
			if (mobileInstantaneousData.getMethaneReading() == null) {
				continue;
			}

			// Get user information.
			User user = getUser(userMap, mobileInstantaneousData.getmInspectorUserName());

			// Get site.
			Site site = monitoringPointService.getSiteByEnumName(mobileInstantaneousData.getmLocation());

			// Get corresponding unverified data set.
			UnverifiedDataSet unverifiedDataSet = getUnverifiedDataSet(resultMap, user, site);
			if (unverifiedDataSet == null) {
				continue;
			}

			// Declare new unverified instantaneous data.
			UnverifiedInstantaneousData unverifiedInstantaneousData = new UnverifiedInstantaneousData();

			MonitoringPoint grid = monitoringPointService.getGridBySiteNameAndId(site, mobileInstantaneousData.getGridId());
			if (grid == null) {
				if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tError Unmapping Instantaneous Data: Grid " + mobileInstantaneousData.getGridId() + " in " + mobileInstantaneousData.getmLocation() + " not found.");

				// If grid is null, the just skip over the reading.
				// It is not possible to leave the grid null in the Andriod app,
				// but there is a rare bug where a non-existent(?) reading is exported with a null grid.
				continue;
			}
			unverifiedInstantaneousData.setMonitoringPoint(grid);

			// If the instantaneous reading is a hotspot...
			if (mobileInstantaneousData.getMethaneReading() >= ImeData.MININIMUM_READING / 100.0) {
				
				// Declare a variable for an IME number and initialize to null.
				ImeNumber imeNumber = null;
				
				// If there is already a IME number specified for the reading...
				if (mobileInstantaneousData.getImeNumber() != null && !mobileInstantaneousData.getImeNumber().isEmpty()) {

					// Find an ing IME number (in this data set, not from the database) that matches the current one.
					for (ImeNumber existingImeNumber : unverifiedDataSet.getImeNumbers()) {
						if (existingImeNumber.getImeNumber().equals(mobileInstantaneousData.getImeNumber())) {
							imeNumber = existingImeNumber;
							break;
						}
					}

					// If no suitable IME number was found, then create a new one.
					// Not sure if this is a good idea... comment out this if statement block to disable.
					if (imeNumber == null) {
						ImeNumber newImeNumber = imeNumberService.generateImeNumberFromString(mobileInstantaneousData.getImeNumber());
						if (newImeNumber != null) {

							// Make sure the IME's date code matches with the discovery date.
							Timestamp date = DateTimeUtils.mobileDateToTimestamp(mobileInstantaneousData.getmStartDate());
							newImeNumber.setDateCode(imeNumberService.generateDateCodeFromLong(date.getTime()));
							
							newImeNumber.setStatus(ExceedanceStatus.UNVERIFIED);
							unverifiedDataSet.getImeNumbers().add(newImeNumber);
							imeNumber = newImeNumber;
						}
					}

				}
				
				// If an IME number was not specified for the hotspot, then try to link to an existing one.
				// Not sure if this is a good idea either... comment out this else statement block to disable.
				else  {
					
					// Look for an existing IME (in this dataset, not from the data base) that matches
					// the methane level and grid of the instantaneous reading.
					for (ImeNumber existingImeNumber : unverifiedDataSet.getImeNumbers()) {
						ImeData existingImeData = imeNumberService.findInitialDataEntry(existingImeNumber);
						if (existingImeData == null) {
							continue;
						}
						boolean gridMatch = existingImeNumber.getMonitoringPoints().stream()
								.filter(m -> m.getName().equals(mobileInstantaneousData.getGridId()))
								.count() > 0;
						if (gridMatch && (int)(mobileInstantaneousData.getMethaneReading() * 100) == existingImeData.getMethaneLevel()) {
							imeNumber = existingImeNumber;
							break;
						}
					}
				}
				
				// Add the instantaneous data to the IME number and vice versa. Also add grid to the IME number.
				if (imeNumber != null) {
					imeNumber.getMonitoringPoints().add(monitoringPointService.getGridBySiteNameAndId(site, mobileInstantaneousData.getGridId()));
					imeNumber.getUnverifiedInstantaneousData().add(unverifiedInstantaneousData);
					unverifiedInstantaneousData.getImeNumbers().add(imeNumber);
				}
				
			}
			
			Instrument instrument = null;
			if (mobileInstantaneousData.getmInstrument() != null) {
				try {
					// Find instrument based provided ID, if any.
					instrument = instrumentDao.getById(mobileInstantaneousData.getmInstrument());
				}
				catch (NumberFormatException e) {
					if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tInstrument ID '" + mobileInstantaneousData.getmInstrument() + "' in instantaneous data is not a number. Instrument will be left as null.");
				}
			}
			
			unverifiedInstantaneousData.setInstrument(instrument);
			unverifiedInstantaneousData.setMethaneLevel((int)(mobileInstantaneousData.getMethaneReading() * 100));
			unverifiedInstantaneousData.setStartTime(DateTimeUtils.mobileDateToTimestamp(mobileInstantaneousData.getmStartDate()));
			unverifiedInstantaneousData.setEndTime(DateTimeUtils.mobileDateToTimestamp(mobileInstantaneousData.getmEndDate()));
			
			// Set barometric pressure
			if (mobileInstantaneousData.getmBarometricPressure() != null) {
				unverifiedInstantaneousData.setBarometricPressure((short)(mobileInstantaneousData.getmBarometricPressure() * 100));
			}

			// Add the unverified data set to the unverified instantaneous data and vice versa.
			unverifiedInstantaneousData.setUnverifiedDataSet(unverifiedDataSet);
			unverifiedDataSet.getUnverifiedInstantaneousData().add(unverifiedInstantaneousData);

		}

		// Process the instantaneous warmspot data entries.
		for (MobileWarmspotData mobileWarmspotData : mobileDataContainer.getmWarmSpotDatas()) {

			// Check if the methane level is within valid range.
			// If it is not within valid range, then it is probably bad data
			// exported by the Andriod app, and should just be skipped.
			if (mobileWarmspotData.getmMaxMethaneReading() == null ||
				mobileWarmspotData.getmMaxMethaneReading() < WarmspotData.MININIMUM_READING / 100.0 ||
				mobileWarmspotData.getmMaxMethaneReading() >= WarmspotData.MAXNIMUM_READING / 100.0) {
				continue;
			}

			// Get user information.
			User user = getUser(userMap, mobileWarmspotData.getmInspectorUserName());

			// Get site.
			Site site = monitoringPointService.getSiteByEnumName(mobileWarmspotData.getmLocation());

			// Get corresponding unverified data set.
			UnverifiedDataSet unverifiedDataSet = getUnverifiedDataSet(resultMap, user, site);
			if (unverifiedDataSet == null) {
				continue;
			}
			
			UnverifiedWarmspotData unverifiedWarmspotData = new UnverifiedWarmspotData();
			unverifiedWarmspotData.setMethaneLevel((int)(mobileWarmspotData.getmMaxMethaneReading() * 100));
			unverifiedWarmspotData.setDate(new Date(DateTimeUtils.mobileDateToTimestamp(mobileWarmspotData.getmDate()).getTime()));
			unverifiedWarmspotData.setDescription(mobileWarmspotData.getmDescription() == null ? "" : mobileWarmspotData.getmDescription());
			unverifiedWarmspotData.setSize(String.valueOf(mobileWarmspotData.getmEstimatedSize()));
			
			Instrument instrument = null;
			if (mobileWarmspotData.getmInstrument() != null) {
				try {
					// Find instrument based provided ID, if any.
					instrument = instrumentDao.getById(mobileWarmspotData.getmInstrument());
				}
				catch (NumberFormatException e) {
					if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tInstrument ID '" + mobileWarmspotData.getmInstrument() + "' in warmspot data is not a number. Instrument will be left as null.");
				}
			}
			unverifiedWarmspotData.setInstrument(instrument);
			
			// Parse set of grids from the space delimited string.
			if (mobileWarmspotData.getmGrids() != null) {
				Set<MonitoringPoint> grids = Arrays.stream(mobileWarmspotData.getmGrids().split(", "))
						.map(g -> monitoringPointService.getGridBySiteNameAndId(site, g))
						.filter(g -> g != null)
						.collect(Collectors.toSet()); 
				
				// TODO Handle case when grids is empty.
				
				// Add grids to warmspot data.
				unverifiedWarmspotData.getMonitoringPoints().addAll(grids);
			}

			// Add the unverified data set to the unverified warmspot and vice versa.
			unverifiedWarmspotData.setUnverifiedDataSet(unverifiedDataSet);
			unverifiedDataSet.getUnverifiedWarmspotData().add(unverifiedWarmspotData);

		}

		// Process the ISE data entries.
		for (MobileIseData mobileIseData : mobileDataContainer.getmIseDatas()) {

			// Check if the methane level is within valid range.
			// If it is not within valid range, then it is probably bad data
			// exported by the Andriod app, and should just be skipped.
			if (mobileIseData.getmMethaneReading() == null || mobileIseData.getmMethaneReading() < IseData.MININIMUM_READING / 100.0) {
				continue;
			}

			// Get user information.
			User user = getUser(userMap, mobileIseData.getmInspectorUserName());

			// Get site.
			Site site = monitoringPointService.getSiteByEnumName(mobileIseData.getmLocation());

			// Get corresponding unverified data set.
			UnverifiedDataSet unverifiedDataSet = getUnverifiedDataSet(resultMap, user, site);
			if (unverifiedDataSet == null) {
				continue;
			}

			// Get ISE number string from mobile data.
			String iseNumberString = mobileIseData.getmIseNumber();

			// If the imported ISE data doesn't contain an IME number, then get it from the site and date.
			if (iseNumberString == null || iseNumberString.trim().isEmpty()) {

				// Get date and format it into the date code.
				Timestamp date = DateTimeUtils.mobileDateToTimestamp(mobileIseData.getmDate());
				if (date == null) {
					continue;
				}
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				int dateCode = (calendar.get(Calendar.YEAR) % 2000) * 100 + calendar.get(Calendar.MONTH) + 1;

				// Create new ISE number string based on site, date code, and next sequence number.
				iseNumberString = site.getShortName() + "-" + dateCode + "-00";

			}

			// Create new ISE number based on the ISE number string.
			IseNumber iseNumber = iseNumberService.generateIseNumberFromString(iseNumberString);
			if (iseNumber == null) {
				continue;
			}

			// Set grid for the ISE number.
			iseNumber.setMonitoringPoint(monitoringPointService.getGridBySiteNameAndId(site, mobileIseData.getmGridId()));

			// Set the status of the ISE number as unverified.
			iseNumber.setStatus(ExceedanceStatus.UNVERIFIED);
			
			// Create initial ISE data entry based on imported info.
			IseData iseData = new IseData();
			
			iseData.setDateTime(DateTimeUtils.mobileDateToTimestamp(mobileIseData.getmDate()));
			iseData.setDescription(mobileIseData.getmDescription() == null ? "" : mobileIseData.getmDescription());
			iseData.setMethaneLevel((int)(mobileIseData.getmMethaneReading() * 100));
			iseData.setInspector(getUser(userMap, mobileIseData.getmInspectorUserName()));
			
			Instrument instrument = null;
			if (mobileIseData.getmInstrument() != null) {
				try {
					instrument = instrumentDao.getById(mobileIseData.getmInstrument());
				}
				catch (NumberFormatException e) {
					if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tInstrument ID '" + mobileIseData.getmInstrument() + "' in ISE data is not a number. Instrument will be left as null.");
				}
			}
			iseData.setInstrument(instrument);
			
			// Add the ISE number to the ISE data entry and vice versa.
			iseData.setIseNumber(iseNumber);
			iseNumber.getIseData().add(iseData);

			/* 
			 * Add the ISE number to the unverified data set and vice versa.
			 * 
			 * NOTE: Do not add unverified data set to ISE number here.
			 * Let the checkRelations() method in the UnverifiedDataSetService handle this.
			 */
			unverifiedDataSet.getIseNumbers().add(iseNumber);

		}

		// Process the integrated data entries.
		for (MobileIntegratedData mobileIntegratedData : mobileDataContainer.getmIntegratedDatas()) {

			// Get user information.
			User user = getUser(userMap, mobileIntegratedData.getmInspectorUserName());

			// Get corresponding unverified data set.
			UnverifiedDataSet unverifiedDataSet = getUnverifiedDataSet(resultMap, user, monitoringPointService.getSiteByEnumName(mobileIntegratedData.getmLocation()));
			if (unverifiedDataSet == null) {
				continue;
			}

			// Declare new unverfied integrated data. 
			UnverifiedIntegratedData unverifiedIntegratedData = new UnverifiedIntegratedData();

			MonitoringPoint grid = monitoringPointService.getGridBySiteNameAndId(monitoringPointService.getSiteByEnumName(mobileIntegratedData.getmLocation()), mobileIntegratedData.getmGridId());
			if (grid == null) {
				if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tError Unmapping Integrated Data: Grid " + mobileIntegratedData.getmGridId() + " in " + mobileIntegratedData.getmLocation() + " not found.");
				
				// If grid is null, the just skip over the reading.
				// It is not possible to leave the grid null in the Andriod app,
				// but there is a rare bug where a non-existent(?) reading is exported with a null grid.
				continue;
			}
			unverifiedIntegratedData.setMonitoringPoint(grid);

			Instrument instrument = null;
			if (mobileIntegratedData.getmInstrument() != null) {
				try {
					instrument = instrumentDao.getById(mobileIntegratedData.getmInstrument());
				}
				catch (NumberFormatException e) {
					if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tInstrument ID '" + mobileIntegratedData.getmInstrument() + "' in integrated data is not a number. Instrument will be left as null.");
				}
			}
			unverifiedIntegratedData.setInstrument(instrument);
			
			// TODO Automate sample id.
			unverifiedIntegratedData.setSampleId(mobileIntegratedData.getmSampleId() == null ? "" : mobileIntegratedData.getmSampleId());
			unverifiedIntegratedData.setBagNumber(mobileIntegratedData.getmBagNumber().shortValue());
			unverifiedIntegratedData.setVolume(mobileIntegratedData.getmVolumeReading().shortValue());
			unverifiedIntegratedData.setMethaneLevel((int)(mobileIntegratedData.getmMethaneReading() * 100));
			unverifiedIntegratedData.setStartTime(DateTimeUtils.mobileDateToTimestamp(mobileIntegratedData.getmStartDate()));
			unverifiedIntegratedData.setEndTime(DateTimeUtils.mobileDateToTimestamp(mobileIntegratedData.getmEndDate()));

			// Set barometric pressure
			if (mobileIntegratedData.getmBarometricPressure() != null) {
				unverifiedIntegratedData.setBarometricPressure((short)(mobileIntegratedData.getmBarometricPressure() * 100));
			}

			// Add the unverified data set to the unverified integrated data and vice versa.
			unverifiedIntegratedData.setUnverifiedDataSet(unverifiedDataSet);
			unverifiedDataSet.getUnverifiedIntegratedData().add(unverifiedIntegratedData);

		}

		// Process probe data.
		for (MobileProbeData mobileProbeData : mobileDataContainer.getmProbeDatas()) {

			UnverifiedProbeData unverifiedProbeData = new UnverifiedProbeData();

			MonitoringPoint probe = monitoringPointService.getProbeBySiteNameAndId(monitoringPointService.getSiteByEnumName(mobileProbeData.getmLocation()), mobileProbeData.getmProbeNumber());
			if (probe == null) {
				if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tError Unmapping Probe Data: Probe " + mobileProbeData.getmProbeNumber() + " in " + mobileProbeData.getmLocation() + " not found.");
				continue;
			}
			unverifiedProbeData.setMonitoringPoint(probe);

			unverifiedProbeData.setDate(new Date(DateTimeUtils.mobileDateToTimestamp(mobileProbeData.getmDate()).getTime()));
			unverifiedProbeData.setMethaneLevel((int)(mobileProbeData.getmMethanePercentage() * 100));
			unverifiedProbeData.setPressureLevel((int)(mobileProbeData.getmWaterPressure() * 100));
			unverifiedProbeData.setDescription(mobileProbeData.getmRemarks() == null ? "" : mobileProbeData.getmRemarks());
			unverifiedProbeData.setAccessible(false);
			
			Instrument instrument = null;
			if (mobileProbeData.getmInstrument() != null) {
				// TODO Change instrument to integer on Andriod side, so that we don't need to convert string to integer.
				try {
					instrument = instrumentDao.getById(mobileProbeData.getmInstrument());
				}
				catch (NumberFormatException e) {
					if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tInstrument ID '" + mobileProbeData.getmInstrument() + "' in probe data is not a number. Instrument will be left as null.");
				}
			}
			unverifiedProbeData.setInstrument(instrument);


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

			// At this point, this cannot be null.
			UnverifiedDataSet unverifiedDataSet = getUnverifiedDataSet(resultMap, user, monitoringPointService.getSiteByEnumName(mobileProbeData.getmLocation()));

			// Add the unverified data set to the unverified probe data and vice versa.
			unverifiedProbeData.setUnverifiedDataSet(unverifiedDataSet);
			unverifiedDataSet.getUnverifiedProbeData().add(unverifiedProbeData);

		}

		// *** If it got to this point in the code, then that means there was no 'errors' with the mobile data.

		Set<UnverifiedDataSet> result = resultMap.values().stream().map(map -> map.values()).flatMap(values -> values.stream()).collect(Collectors.toSet());

		// Insert unverified data sets into the database.
		for (UnverifiedDataSet unverifiedDataSet : result) {

			// Insert IME numbers into database.
			for (ImeNumber imeNumber : unverifiedDataSet.getImeNumbers()) {
				imeNumberService.createUnverified(imeNumber);
			}

			// Insert ISE numbers into database.
			for (IseNumber iseNumber : unverifiedDataSet.getIseNumbers()) {
				iseNumberService.createUnverified(iseNumber);
			}

			// Insert rest of data set to database.
			unverifiedDataSet.setFilename(mobileDataContainer.getFilename());
			unverifiedDataSetService.create(unverifiedDataSet);
		}

		// Return the set of results.
		return result;
	}

	private UnverifiedDataSet getUnverifiedDataSet(Map<User, Map<Site, UnverifiedDataSet>> resultMap, User user, Site site) {
		if (user == null || site == null) {
			return null;
		}
		if (!resultMap.containsKey(user)) {
			Map<Site, UnverifiedDataSet> map = new HashMap<>();
			resultMap.put(user, map);
		}
		UnverifiedDataSet unverifiedDataSet = getUnverifiedDataSet(resultMap.get(user), site);
		if (unverifiedDataSet.getInspector() == null) {
			unverifiedDataSet.setInspector(user);
		}
		return unverifiedDataSet;
	}

	private UnverifiedDataSet getUnverifiedDataSet(Map<Site, UnverifiedDataSet> map, Site site) {

		// If a data set doesn't exist yet for the site, then create a new one.
		if (!map.containsKey(site)) {
			UnverifiedDataSet dataSet = new UnverifiedDataSet();
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

}
