package org.lacitysan.landfill.server.service.report;

import java.sql.Date;
import java.util.Calendar;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.persistence.enums.report.ReportPeriod;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for <code>ReportQuery</code> objects.
 * @author Alvin Quach
 */
@Service
public class ReportQueryService {
	
	public ReportQuery updateReportQueryDateRange(ReportQuery reportQuery) {
		
		if (reportQuery.getReportPeriod() == ReportPeriod.SINGLE) {
			return reportQuery;
		}
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		if (reportQuery.getReportPeriod() == ReportPeriod.DAILY) {
			
			// Calculate start time.
			start.add(Calendar.DATE, -1);
			reportQuery.setStartDate(new Date(start.getTimeInMillis()));
			
			// Calculate end time.
			end.add(Calendar.DATE, -1);
			reportQuery.setEndDate(new Date(end.getTimeInMillis()));
			
		}
		
		else if (reportQuery.getReportPeriod() == ReportPeriod.WEEKLY) {
			
			int dayOfWeek = start.get(Calendar.DAY_OF_WEEK);
			
			// Calculate start time.
			start.add(Calendar.DATE, -(dayOfWeek + 6));
			reportQuery.setStartDate(new Date(start.getTimeInMillis()));
			
			// Calculate end time.
			end.add(Calendar.DATE, 6 -(dayOfWeek + 6));
			reportQuery.setEndDate(new Date(end.getTimeInMillis()));
			
		}
		
		else if (reportQuery.getReportPeriod() == ReportPeriod.MONTHLY) {
			
			// Calculate start time.
			start.add(Calendar.MONTH, -2);
			start.set(Calendar.DATE, 1);
			reportQuery.setStartDate(new Date(start.getTimeInMillis()));
			
			// Calculate end time.
			end.add(Calendar.MONTH, -1);
			end.set(Calendar.DATE, 0);
			reportQuery.setEndDate(new Date(end.getTimeInMillis()));
			
		}
		
		else if (reportQuery.getReportPeriod() == ReportPeriod.QUARTERLY) {
			
			int quarter = start.get(Calendar.MONTH) / 3;
			
			// Calculate start time.
			if (quarter == 0) {
				start.add(Calendar.YEAR, -1);
				start.set(Calendar.MONTH, 9);
			}
			else {
				start.set(Calendar.MONTH, (quarter - 1) * 3);
			}
			start.set(Calendar.DATE, 1);
			reportQuery.setStartDate(new Date(start.getTimeInMillis()));
			
			// Calculate end time.
			end.set(Calendar.MONTH, quarter * 3 - 1);
			end.set(Calendar.DATE, 0);
			reportQuery.setEndDate(new Date(end.getTimeInMillis()));
			
		}
		
		else if (reportQuery.getReportPeriod() == ReportPeriod.YEARLY) {
			
			// Calculate start time.
			start.add(Calendar.YEAR, -2);
			start.set(Calendar.MONTH, 0);
			start.set(Calendar.DATE, 1);
			reportQuery.setStartDate(new Date(start.getTimeInMillis()));
			
			// Calculate end time.
			end.add(Calendar.YEAR, -1);
			end.set(Calendar.MONTH, 0);
			end.set(Calendar.DATE, 0);
			reportQuery.setEndDate(new Date(end.getTimeInMillis()));
			
		}
		
		System.out.println(reportQuery.getStartDate() + "\n" + reportQuery.getEndDate());
		return reportQuery;
		
	}
	
	
	
}
