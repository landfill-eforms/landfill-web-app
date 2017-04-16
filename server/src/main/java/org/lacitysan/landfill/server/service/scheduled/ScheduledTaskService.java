package org.lacitysan.landfill.server.service.scheduled;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.scheduled.ScheduledEmailDao;
import org.lacitysan.landfill.server.persistence.entity.scheduled.Schedule;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.lacitysan.landfill.server.persistence.enums.scheduled.SchedulePeriodBoundary;
import org.lacitysan.landfill.server.persistence.enums.scheduled.ScheduleRecurrence;
import org.lacitysan.landfill.server.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {

	@Autowired
	ScheduledEmailDao scheduledEmailDao;

	@Autowired
	EmailService emailService;

	public void runScheduledTasks() {
		LocalDateTime now = LocalDateTime.now();
		if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tChecking for scheduled tasks at " + now);
		List<ScheduledEmail> scheduledEmails = scheduledEmailDao.getAll();
		if (scheduledEmails.isEmpty()) {
			if (ApplicationConstant.DEBUG) System.out.println("\tNo scheduled tasks.");
			return;
		}
		if (ApplicationConstant.DEBUG) System.out.println("\tLoaded " + scheduledEmails.size() + " scheduled tasks from database.");
		scheduledEmails.stream()
		.filter(scheduledEmail -> {
			Schedule schedule = scheduledEmail.getSchedule();
			boolean taskDue = isTaskDue(schedule, now);
			if (ApplicationConstant.DEBUG) printTaskInfo(schedule, taskDue); 
			return taskDue;
		})
		.parallel()
		.forEach(scheduledEmail -> {
			Schedule schedule = scheduledEmail.getSchedule();
			Calendar offset = new GregorianCalendar();
			offset.setTime(schedule.getOffset());
			emailService.sendEmail(scheduledEmail);
			if (schedule.getRecurrence() == ScheduleRecurrence.SINGLE) {
				schedule.setActive(false);
			}
			schedule.setLastOccurrence(Timestamp.from(now.atZone(ZoneId.systemDefault()).toInstant()));
			scheduledEmailDao.update(scheduledEmail);
		});
	}

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
