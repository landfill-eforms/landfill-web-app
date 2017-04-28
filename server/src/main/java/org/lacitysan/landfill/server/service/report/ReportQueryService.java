package org.lacitysan.landfill.server.service.report;

import java.sql.Date;
import java.util.Calendar;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for <code>ReportQuery</code> objects.
 * @author Alvin Quach
 */
@Service
public class ReportQueryService {
	
	public ReportQuery truncateDateRange(ReportQuery reportQuery) {
		reportQuery.setStartDate(new Date(DateTimeUtils.truncate(reportQuery.getStartDate().getTime(), Calendar.DATE)));
		reportQuery.setEndDate(new Date(DateTimeUtils.truncate(reportQuery.getEndDate().getTime(), Calendar.DATE)));
		System.out.println("Start: " + reportQuery.getStartDate());
		System.out.println("End: " + reportQuery.getEndDate());
		return reportQuery;
	}
	
}
