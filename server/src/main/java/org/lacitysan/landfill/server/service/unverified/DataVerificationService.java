package org.lacitysan.landfill.server.service.unverified;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.exception.DataVerificationException;
import org.lacitysan.landfill.server.persistence.dao.probe.ProbeDataDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.WarmspotDataDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.integrated.IntegratedDataDao;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetDao;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedInstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedWarmspotDataDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IntegratedData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedIntegratedData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedWarmspotData;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.service.surfaceemission.instantaneous.ImeNumberService;
import org.lacitysan.landfill.server.service.surfaceemission.integrated.IseNumberService;
import org.lacitysan.landfill.server.service.unverified.model.VerifiedDataSet;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class DataVerificationService {

	@Autowired
	UnverifiedDataSetDao unverifiedDataSetDao;

	@Autowired
	UnverifiedInstantaneousDataDao unverifiedInstantaneousDataDao;

	@Autowired
	UnverifiedWarmspotDataDao unverifiedWarmspotDataDao;

	@Autowired
	InstantaneousDataDao instantaneousDataDao;

	@Autowired
	IntegratedDataDao integrateDataDao;

	@Autowired
	ProbeDataDao probeDataDao;

	@Autowired
	WarmspotDataDao warmspotDataDao;

	@Autowired
	ImeNumberService imeNumberService;

	@Autowired
	IseNumberService iseNumberService;

	public Object verifyAndCommit(UnverifiedDataSet unverifiedDataSet) {

		// Create a list to store errors.
		List<String> errorLog = new LinkedList<>();

		// Create a new set to store the verified data.
		VerifiedDataSet result = new VerifiedDataSet();

		// This is the site that we are working with for this set of unverified data.
		Site site = unverifiedDataSet.getSite();

		// This is the inspector that we are working with for this set of unverified data.
		User inspector = unverifiedDataSet.getInspector();

		// Go through all the unverified instantaneous data entries.
		result.getInstantaneousData().addAll(unverifiedDataSet.getUnverifiedInstantaneousData().stream()
				.map(unverifiedInstantaneousData -> {

					// Check if the grid of each data point belongs to the correct site.
					if (unverifiedInstantaneousData.getMonitoringPoint().getSite() != site) {
						errorLog.add("Instantaneous reading of " + (unverifiedInstantaneousData.getMethaneLevel() / 100.0) + " ppm belongs to different site (" + unverifiedInstantaneousData.getMonitoringPoint().getSite() + ")");
					}

					// Check if there is an instrument defined and if it of the correct type.
					Instrument instrument = unverifiedInstantaneousData.getInstrument();
					if (instrument == null) {
						errorLog.add(getDescription(unverifiedInstantaneousData) + " has no instrument defined.");
					}
					else if (!instrument.getInstrumentType().getInstantaneous()) {
						errorLog.add(getDescription(unverifiedInstantaneousData) + " has an instrument that cannot be used for instantaneous readings.");
					}

					// Get the barometric pressure from the unverified data point, and check if its value is valid.
					Short barometricPressure = unverifiedInstantaneousData.getBarometricPressure();
					if (barometricPressure == null || barometricPressure < 2950 || barometricPressure > 3050) {
						errorLog.add(getDescription(unverifiedInstantaneousData) + " has an out-of-range barometric pressure.");
					}

					// Create new instantaneous data object and populate its fields with the data from the unverified data point.
					InstantaneousData instantaneousData = new InstantaneousData();

					// If the data point is supposed to be a hotspot...
					if (unverifiedInstantaneousData.getMethaneLevel() >= 50000) { 

						// If the data point is an IME, but doesn't contain any IME numbers...
						if (unverifiedInstantaneousData.getImeNumbers() == null || unverifiedInstantaneousData.getImeNumbers().isEmpty()) {
							errorLog.add(getDescription(unverifiedInstantaneousData) + " is a hotspot, but has no IME numbers associated with it.");
						}

						// Validate each of the IME Numbers that are associated with the data point.
						for (ImeNumber imeNumber : unverifiedInstantaneousData.getImeNumbers()) {
							if (imeNumber.getSite() != site) {
								errorLog.add(getDescription(unverifiedInstantaneousData) + " has an IME number (" + imeNumber + ") from a different site (" + imeNumber.getSite() + ")");
							}
							instantaneousData.getImeNumbers().add(imeNumber);
							result.getImeNumbers().add(imeNumber);
						}

					}

					// If the data is not a hotspot...
					else {

						// If the data point is supposed to be a warmspot...
						if (unverifiedInstantaneousData.getMethaneLevel() >= 20000) {

							// Check if there is a corresponding warmspot entry in the dataset.
							boolean warmspotGridFound = false;
							boolean warmspotReadingFound = false;
							for (UnverifiedWarmspotData unverifiedWarmspotData : unverifiedDataSet.getUnverifiedWarmspotData()) {
								if (unverifiedWarmspotData.getMonitoringPoint() == unverifiedInstantaneousData.getMonitoringPoint()) {
									warmspotGridFound = true;
								}
								else {
									continue;
								}
								if (unverifiedWarmspotData.getMethaneLevel().equals(unverifiedInstantaneousData.getMethaneLevel())) {
									warmspotReadingFound = true;
									break;
								}
							}

							// If the data point is a warmspot, but doesn't contain any warmspot data...
							if (!warmspotReadingFound) {
								if (warmspotGridFound) {
									errorLog.add(getDescription(unverifiedInstantaneousData) + " is a warmspot, and the data set contains a warmspot entry with the same grid, but it has a different a ppm.");
								}
								else {
									errorLog.add(getDescription(unverifiedInstantaneousData) + " is a warmspot, but the data set doesn't contain a corresponding warmspot entry with the same grid and ppm.");
								}
							}

						}

						// If the data point is not a hotspot, then they shouldn't have any IME Numbers.
						if (unverifiedInstantaneousData.getImeNumbers() != null && !unverifiedInstantaneousData.getImeNumbers().isEmpty()) {
							errorLog.add(getDescription(unverifiedInstantaneousData) + " is a not a hotspot, but has IME number(s) associated with it.");
						}

					}

					instantaneousData.setInspector(inspector);
					instantaneousData.setBarometricPressure(barometricPressure);
					instantaneousData.setStartTime(unverifiedInstantaneousData.getStartTime());
					instantaneousData.setEndTime(unverifiedInstantaneousData.getEndTime());
					instantaneousData.setInstrument(unverifiedInstantaneousData.getInstrument());
					instantaneousData.setMonitoringPoint(unverifiedInstantaneousData.getMonitoringPoint());
					instantaneousData.setMethaneLevel(unverifiedInstantaneousData.getMethaneLevel());

					return instantaneousData;

				})
				.collect(Collectors.toSet()));

		// Add the rest of the IME numbers from the data set.
		result.getImeNumbers().addAll(unverifiedDataSet.getImeNumbers());

		// Check all unverified IME numbers for errors.
		for (ImeNumber imeNumber : result.getImeNumbers()) {
			if (imeNumber.getStatus() != ExceedanceStatus.UNVERIFIED) {
				continue;
			}
			if (imeNumber.getSite() != site) {
				errorLog.add(getDescription(imeNumber) + " is for a different site (" + imeNumber.getSite().getName() + ").");
				continue;
			}
			for (MonitoringPoint monitoringPoint : imeNumber.getMonitoringPoints()) {
				if (monitoringPoint.getSite() != site) {
					errorLog.add(getDescription(imeNumber) + " contains a grid (" + monitoringPoint.name() + ") from a different site (" + monitoringPoint.getSite().getName() + ").");
				}
			}
			ImeData initial = imeNumber.getImeData().stream().findFirst().orElse(null);
			if (initial == null) {
				errorLog.add(getDescription(imeNumber) + " does not contain an initial reading.");
				continue;
			}
			if (initial.getMethaneLevel() < 50000) {
				errorLog.add(getDescription(imeNumber) + " has an initial reading below the threshold (" + (initial.getMethaneLevel() / 100.0) + " < 500.00).");
			}
			if (imeNumber.getDateCode() != imeNumberService.generateDateCodeFromLong(initial.getDateTime().getTime())) {
				errorLog.add(getDescription(imeNumber) + " has a discovery date (" + DateTimeUtils.formatSimpleDate(initial.getDateTime().getTime()) + ") that does not correspond the IME number.");
			}
		}

		// Go through all the unverified warmspot data entries.
		result.getWarmspotData().addAll(unverifiedDataSet.getUnverifiedWarmspotData().stream()
				.map(unverifiedWarmspotData -> {
					// TODO Verify warmspot data, such as instrument, inspector, grid, and date.
					WarmspotData warmspotData = new WarmspotData();
					warmspotData.setMonitoringPoint(unverifiedWarmspotData.getMonitoringPoint());
					warmspotData.setInstrument(unverifiedWarmspotData.getInstrument());
					warmspotData.setInspector(inspector);
					warmspotData.setMethaneLevel(unverifiedWarmspotData.getMethaneLevel());
					warmspotData.setDate(unverifiedWarmspotData.getDate());
					warmspotData.setDescription(unverifiedWarmspotData.getDescription());
					warmspotData.setSize(unverifiedWarmspotData.getSize());
					return warmspotData;
				})
				.collect(Collectors.toSet()));

		// Go through all the unverified integrated data entries.
		result.getIntegratedData().addAll(unverifiedDataSet.getUnverifiedIntegratedData().stream()
				.map(unverifiedIntegratedData -> {

					// Check if the grid of each data point belongs to the correct site.
					if (unverifiedIntegratedData.getMonitoringPoint().getSite() != site) {
						errorLog.add("Instantaneous reading of " + (unverifiedIntegratedData.getMethaneLevel() / 100.0) + " ppm belongs to different site (" + unverifiedIntegratedData.getMonitoringPoint().getSite() + ")");
					}

					// Check if there is an instrument defined and if it of the correct type.
					Instrument instrument = unverifiedIntegratedData.getInstrument();
					if (instrument == null) {
						errorLog.add(getDescription(unverifiedIntegratedData) + " has no instrument defined.");
					}
					else if (!instrument.getInstrumentType().getInstantaneous()) {
						errorLog.add(getDescription(unverifiedIntegratedData) + " has an instrument that cannot be used for integrated readings.");
					}

					// Get the barometric pressure from the unverified data point, and check if its value is valid.
					Short barometricPressure = unverifiedIntegratedData.getBarometricPressure();
					if (barometricPressure == null || barometricPressure < 2950 || barometricPressure > 3050) {
						errorLog.add(getDescription(unverifiedIntegratedData) + " has an out-of-range barometric pressure.");
					}

					// Create new integrated data object and populate its fields with the data from the unverified data point.
					IntegratedData integratedData = new IntegratedData();
					integratedData.setInspector(inspector);
					integratedData.setBarometricPressure(barometricPressure);
					integratedData.setStartTime(unverifiedIntegratedData.getStartTime());
					integratedData.setEndTime(unverifiedIntegratedData.getEndTime());
					integratedData.setInstrument(unverifiedIntegratedData.getInstrument());
					integratedData.setMonitoringPoint(unverifiedIntegratedData.getMonitoringPoint());
					integratedData.setMethaneLevel(unverifiedIntegratedData.getMethaneLevel());
					integratedData.setBagNumber(unverifiedIntegratedData.getBagNumber());
					integratedData.setVolume(unverifiedIntegratedData.getVolume());

					return integratedData;

				})
				.filter(integratedData -> integratedData != null)
				.collect(Collectors.toSet()));

		// Add the ISE numbers from the data set.
		result.getIseNumbers().addAll(unverifiedDataSet.getIseNumbers());

		// Check all unverified ISE numbers for errors.
		for (IseNumber iseNumber : result.getIseNumbers()) {
			if (iseNumber.getStatus() != ExceedanceStatus.UNVERIFIED) {
				continue;
			}
			if (iseNumber.getSite() != site) {
				errorLog.add(getDescription(iseNumber) + " is for a different site (" + iseNumber.getSite().getName() + ").");
				continue;
			}
			if (iseNumber.getMonitoringPoint().getSite() != site) {
				errorLog.add(getDescription(iseNumber) + " has a grid (" + iseNumber.getMonitoringPoint().name() + ") from a different site (" + iseNumber.getMonitoringPoint().getSite().getName() + ").");
			}
			IseData initial = iseNumber.getIseData().stream().findFirst().orElse(null);
			if (initial == null) {
				errorLog.add(getDescription(iseNumber) + " does not contain an initial reading.");
				continue;
			}
			if (initial.getMethaneLevel() < 2500) {
				errorLog.add(getDescription(iseNumber) + " has an initial reading below the threshold (" + (initial.getMethaneLevel() / 100.0) + " < 25.00).");
			}
			if (iseNumber.getDateCode() != iseNumberService.generateDateCodeFromLong(initial.getDateTime().getTime())) {
				errorLog.add(getDescription(iseNumber) + " has a discovery date (" + DateTimeUtils.formatSimpleDate(initial.getDateTime().getTime()) + ") that does not correspond the ISE number.");
			}
		}

		// Go through all the unverified probe data entries.
		result.getProbeData().addAll(unverifiedDataSet.getUnverifiedProbeData().stream()
				.map(unverifiedProbeData -> {
					Short barometricPressure = unverifiedProbeData.getBarometricPressure();
					if (barometricPressure == null || barometricPressure < 2950 || barometricPressure > 3050) {
						return null;
					}
					ProbeData probeData = new ProbeData();
					probeData.setAccessible(unverifiedProbeData.getAccessible());
					probeData.setBarometricPressure(unverifiedProbeData.getBarometricPressure());
					probeData.setDate(unverifiedProbeData.getDate());
					probeData.setDescription(unverifiedProbeData.getDescription());
					probeData.setInspectors(unverifiedProbeData.getInspectors());
					probeData.setMethaneLevel(unverifiedProbeData.getMethaneLevel());
					probeData.setMonitoringPoint(unverifiedProbeData.getMonitoringPoint());
					probeData.setPressureLevel(unverifiedProbeData.getPressureLevel());
					return probeData;
				})
				.filter(probeData -> probeData != null)
				.collect(Collectors.toSet()));

		// If there are errors, do not continue with the process. Instead, throw an exception.
		if (!errorLog.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (String error : errorLog) {
				sb.append(error).append("\n\n");
			}
			throw new DataVerificationException(sb.toString());
		}

		/*
		 * All data should be verified and good to go by this point.
		 * 
		 * We wanted to make sure that all the selected test types had no errors
		 * before committing the data.
		 */

		// Go through the set of instantaneous data.
		for (InstantaneousData instantaneousData : result.getInstantaneousData()) {

			/* 
			 * Update IME Number verified status.
			 * 
			 * This should also verify any unverified IME numbers from other unverified 
			 * data sets that are used by instantaneous data in this data set.
			 */
			for (ImeNumber imeNumber : instantaneousData.getImeNumbers()) {
				if (imeNumber.getStatus() == ExceedanceStatus.UNVERIFIED) {
					imeNumberService.verify(imeNumber);
				}
			}

			// Insert verified instantaneous data into database.
			instantaneousDataDao.create(instantaneousData);

		}

		// Update the rest of the IME numbers in the data set.
		for (ImeNumber imeNumber : unverifiedDataSet.getImeNumbers()) {
			if (imeNumber.getStatus() == ExceedanceStatus.UNVERIFIED) {
				imeNumberService.verify(imeNumber);
			}
		}

		// Commit warmspot data.
		for (WarmspotData warmspotData : result.getWarmspotData()) {
			warmspotDataDao.create(warmspotData);
		}

		// Clear instantaneous data types from unverified data set
		unverifiedDataSet.getUnverifiedInstantaneousData().clear();
		unverifiedDataSet.getUnverifiedWarmspotData().clear();
		unverifiedDataSet.getImeNumbers().clear();

		for (IntegratedData integratedData : result.getIntegratedData()) {
			integrateDataDao.create(integratedData);
		}
		for (IseNumber iseNumber : unverifiedDataSet.getIseNumbers()) {
			if (iseNumber.getStatus() == ExceedanceStatus.UNVERIFIED) {
				iseNumberService.verify(iseNumber);
			}
		}
		// TODO Delete unverified integrated data.
		unverifiedDataSet.getUnverifiedIntegratedData().clear();
		unverifiedDataSet.getIseNumbers().clear();

		for (ProbeData probeData : result.getProbeData()) {
			probeDataDao.create(probeData);
		}
		// TODO Add probe exceedances
		// TODO Delete unverified probe data.
		unverifiedDataSet.getUnverifiedProbeData().clear();
		unverifiedDataSet.getProbeExceedances().clear();

		if (unverifiedDataSet.isEmpty()) {
			return unverifiedDataSetDao.deleteSafe(unverifiedDataSet);
		}
		else {
			return unverifiedDataSetDao.update(unverifiedDataSet);
		}

	}

	private String getDescription(Object o) {
		if (o instanceof UnverifiedInstantaneousData) {
			UnverifiedInstantaneousData unverifiedInstantaneousData = (UnverifiedInstantaneousData)o;
			return "Instantaneous reading of " + (unverifiedInstantaneousData.getMethaneLevel() / 100.0) + " ppm on grid " + unverifiedInstantaneousData.getMonitoringPoint().getName();
		}
		if (o instanceof ImeNumber) {
			return "IME number " + o;
		}
		if (o instanceof UnverifiedIntegratedData) {
			UnverifiedIntegratedData unverifiedIntegratedData = (UnverifiedIntegratedData)o;
			return "Integrated reading of " + (unverifiedIntegratedData.getMethaneLevel() / 100.0) + " ppm on grid " + unverifiedIntegratedData.getMonitoringPoint().getName();
		}
		if (o instanceof IseNumber) {
			return "ISE number " + o;
		}
		return "";
	}

}
