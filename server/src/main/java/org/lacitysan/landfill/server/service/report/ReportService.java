package org.lacitysan.landfill.server.service.report;

import java.util.List;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated.IntegratedDataDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated.IseNumberDao;
import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeRepairData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseRepairData;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceType;
import org.lacitysan.landfill.server.persistence.enums.report.ReportType;
import org.lacitysan.landfill.server.service.report.model.ExceedanceReport;
import org.lacitysan.landfill.server.service.report.model.InstantaneousReport;
import org.lacitysan.landfill.server.service.report.model.IntegratedReport;
import org.lacitysan.landfill.server.service.report.model.Report;
import org.lacitysan.landfill.server.service.report.model.data.InstantaneousReportData;
import org.lacitysan.landfill.server.service.report.model.data.IntegratedReportData;
import org.lacitysan.landfill.server.service.report.model.data.ProbeExceedanceReportData;
import org.lacitysan.landfill.server.service.report.model.data.ServiceEmissionExceedanceReportData;
import org.lacitysan.landfill.server.service.serviceemission.instantaneous.ImeNumberService;
import org.lacitysan.landfill.server.service.serviceemission.integrated.IseNumberService;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.lacitysan.landfill.server.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class ReportService {

	@Autowired
	InstantaneousDataDao instantaneousDataDao;

	@Autowired
	IntegratedDataDao integratedDataDao;

	@Autowired
	ImeNumberDao imeNumberDao;
	
	@Autowired
	IseNumberDao iseNumberDao;

	@Autowired
	ImeNumberService imeNumberService;
	
	@Autowired
	IseNumberService iseNumberService;

	public Report generateReport(ReportQuery reportQuery) {
		if (reportQuery.getReportType() == ReportType.EXCEEDANCE) {
			return generateIntegratedReport(reportQuery);
		}
		if (reportQuery.getReportType() == ReportType.INSTANTANEOUS) {
			return generateInstantaneousReport(reportQuery);
		}
		if (reportQuery.getReportType() == ReportType.INTEGRATED) {
			return generateIntegratedReport(reportQuery);
		}
		if (reportQuery.getReportType() == ReportType.PROBE) {
			// WTF???
		}
		return null;
	}

	private ExceedanceReport generateExceedanceReport(ReportQuery reportQuery) {

		// Check if there are start and end dates specified in the report query.
		Long startDate = reportQuery.getStartDate() == null ? null : reportQuery.getStartDate().getTime();
		Long endDate = reportQuery.getEndDate() == null ? null : reportQuery.getEndDate().getTime();

		ExceedanceReport exceedanceReport = new ExceedanceReport(reportQuery);

		// Query the database for IMEs and add them to the report data.
		if (reportQuery.getExceedanceTypes().contains(ExceedanceType.INSTANTANEOUS)) {
			
			// TODO Create method to query by date range instead of date code.
			exceedanceReport.getImeReportData().addAll(imeNumberDao.getVerifiedBySiteAndDateCode(reportQuery.getSite(), null)
					.parallelStream()
					.sorted((a, b) -> a.compareTo(b))
					.map(c -> {
						
						// Check for missing data.
						if (c.getImeData() == null || c.getImeData().isEmpty()) {
							return null;
						}
						
						// Get initial IME entry and final repair entry, if exists.
						List<ImeData> imeDataList = c.getImeData().stream().collect(Collectors.toList());
						ImeData initial = imeDataList.get(0);
						ImeRepairData finalRepair = imeNumberService.getLastRepair(c);
						
						ServiceEmissionExceedanceReportData d = new ServiceEmissionExceedanceReportData();
						d.setDiscoveredDate(DateTimeUtils.formatSimpleDate(initial.getDateTime().getTime()));
						d.setExceedanceNumber(c.getImeNumber());
						d.setMonitoringPoints(StringUtils.collectionToCommaDelimited(c.getMonitoringPoints(), true));
						d.setRepairDescription(finalRepair.getDescription());
						d.setInitial(String.format("%.2f", initial.getMethaneLevel() / 100.0));
						d.setRecheck(imeDataList.size() == 1 ? "" : String.format("%.2f", imeDataList.get(imeDataList.size() - 1).getMethaneLevel()));
						d.setClearedDate(c.getStatus() == ExceedanceStatus.CLEARED ? DateTimeUtils.formatSimpleDate(finalRepair.getDateTime().getTime()) : "");
						return d;
					})
					.filter(e -> e != null)
					.collect(Collectors.toList()));
		}
		
		// Query the database for ISEs and add them to the report data.
		if (reportQuery.getExceedanceTypes().contains(ExceedanceType.INTEGRATED)) {
			exceedanceReport.getIseReportData().addAll(iseNumberDao.getVerifiedBySiteAndDateCode(reportQuery.getSite(), null)
					.parallelStream()
					.sorted((a, b) -> a.compareTo(b))
					.map(c -> {
						
						// Check for missing data.
						if (c.getIseData() == null || c.getIseData().isEmpty()) {
							return null;
						}
						
						// Get initial IME entry and final repair entry, if exists.
						List<IseData> iseDataList = c.getIseData().stream().collect(Collectors.toList());
						IseData initial = iseDataList.get(0);
						IseRepairData finalRepair = iseNumberService.getLastRepair(c);
						
						ServiceEmissionExceedanceReportData d = new ServiceEmissionExceedanceReportData();
						d.setDiscoveredDate(DateTimeUtils.formatSimpleDate(initial.getDateTime().getTime()));
						d.setExceedanceNumber(c.getIseNumber());
						d.setMonitoringPoints(StringUtils.collectionToCommaDelimited(c.getMonitoringPoints(), true));
						d.setRepairDescription(finalRepair.getDescription());
						d.setInitial(String.format("%.2f", initial.getMethaneLevel() / 100.0));
						d.setRecheck(iseDataList.size() == 1 ? "" : String.format("%.2f", iseDataList.get(iseDataList.size() - 1).getMethaneLevel()));
						d.setClearedDate(c.getStatus() == ExceedanceStatus.CLEARED ? DateTimeUtils.formatSimpleDate(finalRepair.getDateTime().getTime()) : "");
						return d;
					})
					.filter(e -> e != null)
					.collect(Collectors.toList()));
		}
		
		// Query the database for Probe exceedances and add them to the report data.
		if (reportQuery.getExceedanceTypes().contains(ExceedanceType.PROBE)) {
			exceedanceReport.getProbeExceedanceReportData().addAll(iseNumberDao.getVerifiedBySiteAndDateCode(reportQuery.getSite(), null)
					.parallelStream()
					.sorted((a, b) -> a.compareTo(b))
					.map(c -> {
						return new ProbeExceedanceReportData();
					})
					.filter(e -> e != null)
					.collect(Collectors.toList()));
		}
		
		return exceedanceReport;

	}

	private InstantaneousReport generateInstantaneousReport(ReportQuery reportQuery) {

		// Check if there are start and end dates specified in the report query.
		Long startDate = reportQuery.getStartDate() == null ? null : reportQuery.getStartDate().getTime();
		Long endDate = reportQuery.getEndDate() == null ? null : reportQuery.getEndDate().getTime();

		// Query the database and convert the queried data into report data.
		List<InstantaneousReportData> instantaneousReportData = instantaneousDataDao.getBySiteAndDate(reportQuery.getSite(), startDate, endDate)
				.parallelStream()
				.sorted((a, b) -> {
					// Sort data here instead of having InstantaneousData implement Comparable, so that we can add sort options in the future.
					int sort = a.getStartTime().compareTo(b.getStartTime());
					if (sort != 0) {
						return sort;
					}
					return a.getMonitoringPoint().ordinal() - b.getMonitoringPoint().ordinal();
				})
				.map(c -> {
					InstantaneousReportData d = new InstantaneousReportData();
					d.setDate(DateTimeUtils.formatSimpleDate(c.getStartTime().getTime()));
					d.setBarometricPressure(String.format("%.2f", c.getBarometricPressure() / 100.0));
					d.setInspector(c.getInspector().printName());
					d.setMonitoringPoint(c.getMonitoringPoint().getName());
					d.setStartTime(DateTimeUtils.formatSimpleTime(c.getStartTime().getTime()));
					d.setEndTime(DateTimeUtils.formatSimpleTime(c.getEndTime().getTime()));
					d.setInstrument(c.getInstrument().getSerialNumber());
					d.setMethaneLevel(String.format("%.2f", c.getMethaneLevel() / 100.0));
					d.setImeNumber(imeNumberService.stringifyCommonCollection(c.getImeNumbers()));
					return d;
				})
				.collect(Collectors.toList());

		// Return the generated report data.
		return new InstantaneousReport(reportQuery, instantaneousReportData);

	}

	private IntegratedReport generateIntegratedReport(ReportQuery reportQuery) {

		// Check if there are start and end dates specified in the report query.
		Long startDate = reportQuery.getStartDate() == null ? null : reportQuery.getStartDate().getTime();
		Long endDate = reportQuery.getEndDate() == null ? null : reportQuery.getEndDate().getTime();

		// Query the database and convert the queried data into report data.
		List<IntegratedReportData> integratedReportData = integratedDataDao.getBySiteAndDate(reportQuery.getSite(), startDate, endDate)
				.parallelStream()
				.sorted((a, b) -> {
					// Sort data here instead of having IntegratedData implement Comparable, so that we can add sort options in the future.
					int sort = a.getStartTime().compareTo(b.getStartTime());
					if (sort != 0) {
						return sort;
					}
					return a.getMonitoringPoint().ordinal() - b.getMonitoringPoint().ordinal();
				})
				.map(c -> {
					IntegratedReportData d = new IntegratedReportData();
					d.setDate(DateTimeUtils.formatSimpleDate(c.getStartTime().getTime()));
					d.setBarometricPressure(String.format("%.2f", c.getBarometricPressure() / 100.0));
					d.setInspector(c.getInspector().printName());
					d.setMonitoringPoint(c.getMonitoringPoint().getName());
					d.setStartTime(DateTimeUtils.formatSimpleTime(c.getStartTime().getTime()));
					d.setEndTime(DateTimeUtils.formatSimpleTime(c.getEndTime().getTime()));
					d.setInstrument(c.getInstrument().getSerialNumber());
					d.setMethaneLevel(String.format("%.2f", c.getMethaneLevel() / 100.0));
					d.setBagNumber(String.valueOf(c.getBagNumber()));
					d.setVolume(String.valueOf(c.getVolume()));
					d.setSampleId(c.getSampleId());
					return d;
				})
				.collect(Collectors.toList());

		// Return the generated report data.
		return new IntegratedReport(reportQuery, integratedReportData);

	}



}
