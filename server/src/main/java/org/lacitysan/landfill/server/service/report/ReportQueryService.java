package org.lacitysan.landfill.server.service.report;

import java.sql.Date;
import java.util.Calendar;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.persistence.enums.report.ReportPeriod;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for <code>ReportQuery</code> entities.
 * @author Alvin Quach
 */
@Service
public class ReportQueryService {
	
	/**
	 * Calculates the start and end date boundaries for a given report query
	 * and updates the corresponding fields in the {@code ReportQuery} object.
	 * @param reportQuery The {@code ReportQuery} object to calculate the date bounds for.
	 * @return The same {@code ReportQuery} object with its date bound fields updated.
	 */
	public ReportQuery updateReportQueryDateRange(ReportQuery reportQuery) {
		
		if (reportQuery.getReportPeriod() == ReportPeriod.SINGLE) {
			return reportQuery;
		}
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		int offset = reportQuery.getPeriodOffset() == null ? 0 : reportQuery.getPeriodOffset();
		
		
		if (reportQuery.getReportPeriod() == ReportPeriod.DAILY) {
			
			// Calculate start time.
			start.add(Calendar.DATE, offset);
			reportQuery.setStartDate(new Date(start.getTimeInMillis()));
			
			// Calculate end time.
			end.add(Calendar.DATE, offset);
			reportQuery.setEndDate(new Date(end.getTimeInMillis()));
			
		}
		
		else if (reportQuery.getReportPeriod() == ReportPeriod.WEEK) {
			
			int dayOfWeek = start.get(Calendar.DAY_OF_WEEK);
			
			// Calculate start time.
			start.add(Calendar.DATE, 1 - dayOfWeek + 7 * offset);
			reportQuery.setStartDate(new Date(start.getTimeInMillis()));
			
			// Calculate end time.
			end.add(Calendar.DATE, 7 - dayOfWeek + 7 * offset);
			reportQuery.setEndDate(new Date(end.getTimeInMillis()));
			
		}
		
		else if (reportQuery.getReportPeriod() == ReportPeriod.MONTH) {
			
			// Calculate start time.
			start.add(Calendar.MONTH, offset);
			start.set(Calendar.DATE, 1);
			reportQuery.setStartDate(new Date(start.getTimeInMillis()));
			
			// Calculate end time.
			end.add(Calendar.MONTH, offset + 1);
			end.set(Calendar.DATE, 0);
			reportQuery.setEndDate(new Date(end.getTimeInMillis()));
			
		}
		
		else if (reportQuery.getReportPeriod() == ReportPeriod.QUARTER) {
			
			int quarter = start.get(Calendar.MONTH) / 3 + offset;
			
			// Calculate start time.
			start.set(Calendar.MONTH, quarter * 3);
			start.set(Calendar.DATE, 1);
			reportQuery.setStartDate(new Date(start.getTimeInMillis()));
			
			// Calculate end time.
			end.set(Calendar.MONTH, (quarter + 1) * 3);
			end.set(Calendar.DATE, 0);
			reportQuery.setEndDate(new Date(end.getTimeInMillis()));
			
		}
		
		else if (reportQuery.getReportPeriod() == ReportPeriod.YEAR) {
			
			// Calculate start time.
			start.add(Calendar.YEAR, offset);
			start.set(Calendar.MONTH, 0);
			start.set(Calendar.DATE, 1);
			reportQuery.setStartDate(new Date(start.getTimeInMillis()));
			
			// Calculate end time.
			end.add(Calendar.YEAR, offset + 1);
			end.set(Calendar.MONTH, 0);
			end.set(Calendar.DATE, 0);
			reportQuery.setEndDate(new Date(end.getTimeInMillis()));
			
		}
		
		System.out.println(reportQuery.getStartDate() + "\n" + reportQuery.getEndDate());
		return reportQuery;
		
	}
	
	
	
}
