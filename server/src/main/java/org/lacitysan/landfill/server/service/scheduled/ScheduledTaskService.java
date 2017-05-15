package org.lacitysan.landfill.server.service.scheduled;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.scheduled.ScheduledEmailDao;
import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.persistence.entity.scheduled.Schedule;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledReport;
import org.lacitysan.landfill.server.persistence.enums.scheduled.SchedulePeriodBoundary;
import org.lacitysan.landfill.server.persistence.enums.scheduled.ScheduleRecurrence;
import org.lacitysan.landfill.server.service.email.EmailService;
import org.lacitysan.landfill.server.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {

	@Autowired
	ScheduledEmailDao scheduledEmailDao;

	@Autowired
	EmailService emailService;
	
	@Autowired
	ReportService reportService;

	/** Runs all active scheduled tasks that are saved on the database. */
	public void runScheduledTasks() {
		
		// Get the instantaneous time.
		LocalDateTime now = LocalDateTime.now();
		if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tChecking for scheduled tasks at " + now);
		
		// Get the list of scheduled emails from the database. 
		// We can add other types of scheduled tasks, such as notifications, later on.
		List<ScheduledEmail> scheduledEmails = scheduledEmailDao.getAll();
		
		// Check if there are any scheduled emails.
		if (scheduledEmails.isEmpty()) {
			if (ApplicationConstant.DEBUG) System.out.println("\tNo scheduled tasks.");
			return;
		}
		if (ApplicationConstant.DEBUG) System.out.println("\tLoaded " + scheduledEmails.size() + " scheduled tasks from database.");
		
		// Filter out any emails that are not due at this time.
		scheduledEmails.stream()
		.filter(scheduledEmail -> {
			Schedule schedule = scheduledEmail.getSchedule();
			boolean taskDue = isTaskDue(schedule, now);
			if (ApplicationConstant.DEBUG) printTaskInfo(schedule, taskDue); 
			return taskDue;
		})
		
		// Run all the scheduled emails in parallel. 
		.parallel()
		.forEach(scheduledEmail -> {
			Schedule schedule = scheduledEmail.getSchedule();
			List<byte[]> attachments = new ArrayList<>();
			
			// Check if the email type is a report, and run the report query(s).
			if (scheduledEmail instanceof ScheduledReport) {
				for (ReportQuery reportQuery : ((ScheduledReport)scheduledEmail).getReportQueries()) {
					try {
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						reportService.generateReportPdf(baos, reportQuery);
						attachments.add(baos.toByteArray());
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Send the email.
			emailService.sendScheduledEmail(scheduledEmail, attachments);
			
			// Update the scheduled email.
			if (schedule.getRecurrence() == ScheduleRecurrence.SINGLE) {
				schedule.setActive(false);
			}
			schedule.setLastOccurrence(Timestamp.from(now.atZone(ZoneId.systemDefault()).toInstant()));
			scheduledEmailDao.update(scheduledEmail);
		});
		
	}

	/**
	 * Checks determines whether a scheduled task is due at the specified reference time.
	 * @param schedule The schedule of the scheduled task.
	 * @param now The reference time.
	 * @return True if the task is due, false otherwise.
	 */
	public boolean isTaskDue(Schedule schedule, LocalDateTime now) {
		if (!schedule.getActive()) {
			return false;
		}
		LocalDateTime offset = LocalDateTime.ofInstant(schedule.getOffset().toInstant(), ZoneId.systemDefault());
		if (schedule.getRecurrence() == ScheduleRecurrence.SINGLE) {
			if (now.isAfter(offset)) {
				return true;
			}
			return false;
		}
		if (schedule.getRecurrence() == ScheduleRecurrence.MINUTELY) {
			long difference = ChronoUnit.MINUTES.between(offset, now);
			if (difference >= 0 && difference % schedule.getFrequency() == 0) {
				return true;
			}
			return false;
		}
		if (schedule.getRecurrence() == ScheduleRecurrence.HOURLY) {
			long difference = ChronoUnit.HOURS.between(offset, now);
			if (difference >= 0 && difference % schedule.getFrequency() == 0) {
				if (schedule.getPeriodBoundary() == SchedulePeriodBoundary.START) {
					return now.getMinute() == 0;
				}
				if (schedule.getPeriodBoundary() == SchedulePeriodBoundary.END) {
					return now.getMinute() == 59;
				}
				return now.getMinute() == offset.getMinute();
			}
			return false;
		}
		if (schedule.getRecurrence() == ScheduleRecurrence.DAILY) {
			long difference = ChronoUnit.DAYS.between(offset, now);
			if (difference >= 0 && difference % schedule.getFrequency() == 0) {
				return now.getHour() == offset.getHour() && now.getMinute() == offset.getMinute();
			}
			return false;
		}
		if (schedule.getRecurrence() == ScheduleRecurrence.WEEKLY) {
			long difference = ChronoUnit.WEEKS.between(offset, now);
			if (difference >= 0 && difference % schedule.getFrequency() == 0) {
				return now.getDayOfWeek() == offset.getDayOfWeek() && now.getHour() == offset.getHour() && now.getMinute() == offset.getMinute();
			}
			return false;
		}
		if (schedule.getRecurrence() == ScheduleRecurrence.MONTHLY) {
			if (schedule.getPeriodBoundary() == SchedulePeriodBoundary.START) {
				return now.getDayOfMonth() == 1 && now.getHour() == offset.getHour() && now.getMinute() == offset.getMinute();
			}
			if (schedule.getPeriodBoundary() == SchedulePeriodBoundary.END) {
				int lastDayOfMonth = new GregorianCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
				return now.getDayOfMonth() == lastDayOfMonth && now.getHour() == offset.getHour() && now.getMinute() == offset.getMinute();
			}
			return false; // Monthly recurrence requires a period boundary to be defined.
		}
		if (schedule.getRecurrence() == ScheduleRecurrence.QUARTERLY) {
			if (schedule.getPeriodBoundary() == SchedulePeriodBoundary.START) {
				return (now.getMonthValue() - 1) % 3 == 0 && now.getDayOfMonth() == 1 && now.getHour() == offset.getHour() && now.getMinute() == offset.getMinute();
			}
			if (schedule.getPeriodBoundary() == SchedulePeriodBoundary.END) {
				int lastDayOfMonth = new GregorianCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
				return now.getMonthValue() % 3 == 0 && now.getDayOfMonth() == lastDayOfMonth && now.getHour() == offset.getHour() && now.getMinute() == offset.getMinute();
			}
			return false; // Quarterly recurrence requires a period boundary to be defined.
		}
		if (schedule.getRecurrence() == ScheduleRecurrence.YEARLY) {
			if (schedule.getPeriodBoundary() == SchedulePeriodBoundary.START) {
				return now.getDayOfYear() == 1 && now.getHour() == 0 && now.getMinute() == 0;
			}
			if (schedule.getPeriodBoundary() == SchedulePeriodBoundary.END) {
				int lastDayOfYear = new GregorianCalendar().getActualMaximum(Calendar.DAY_OF_YEAR);
				return now.getDayOfYear() == lastDayOfYear && now.getHour() == 23 && now.getMinute() == 59;
			}
			return now.getMonthValue() == offset.getMonthValue() && now.getDayOfMonth() == offset.getDayOfMonth() && now.getHour() == offset.getHour() && now.getMinute() == offset.getMinute();
		}
		return false;
	}

	public void printTaskInfo(Schedule schedule, boolean run) {
		System.out.println("DEBUG:\tScheduled task:");
		System.out.println("\tOffset = " + schedule.getOffset());
		System.out.println("\tFrequency = " + schedule.getFrequency());
		System.out.println("\tRecurrence = " + schedule.getRecurrence());
		System.out.println("\tPeriodBoundary = " + schedule.getPeriodBoundary());
		if (run) {
			System.out.println("\tThis task will run right now.");
		}
		else {
			System.out.println("\tThis task will not run right now.");
		}
	}

}
