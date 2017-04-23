package org.lacitysan.landfill.server.service.report;

import java.util.List;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.persistence.enums.report.ReportType;
import org.lacitysan.landfill.server.service.report.model.InstantaneousReport;
import org.lacitysan.landfill.server.service.report.model.Report;
import org.lacitysan.landfill.server.service.report.model.data.InstantaneousReportData;
import org.lacitysan.landfill.server.service.serviceemission.instantaneous.ImeNumberService;
import org.lacitysan.landfill.server.util.DateTimeUtils;
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
	ImeNumberService imeNumberService;

	public Report generateReport(ReportQuery reportQuery) {

		if (reportQuery.getReportType() == ReportType.INSTANTANEOUS) {
			return generateInstantaneousReport(reportQuery);
		}

		return null;
	}

	private InstantaneousReport generateInstantaneousReport(ReportQuery reportQuery) {

		// Check if there is a site specified in the report query.
		Site site = reportQuery.getSites().stream().findFirst().orElse(null);
		if (site == null) {
			return null;
		}
		
		// Check if there are start and end dates specified in the report query.
		Long startDate = reportQuery.getStartDate() == null ? null : reportQuery.getStartDate().getTime();
		Long endDate = reportQuery.getEndDate() == null ? null : reportQuery.getEndDate().getTime();

		// Query the database.
		List<InstantaneousData> instantaneousData = instantaneousDataDao.getBySiteAndDate(site, startDate, endDate);

		// Convert the queried data into report data.
		List<InstantaneousReportData> instantaneousReportData = instantaneousData.parallelStream()
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
	

}
