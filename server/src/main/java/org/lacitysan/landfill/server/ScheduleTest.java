package org.lacitysan.landfill.server;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.scheduled.Schedule;
import org.lacitysan.landfill.server.persistence.enums.SchedulePeriodBoundary;
import org.lacitysan.landfill.server.persistence.enums.ScheduleRecurrence;
import org.lacitysan.landfill.server.service.scheduled.ScheduledTaskService;

public class ScheduleTest {
	
	private static ScheduledTaskService scheduledTaskService = new ScheduledTaskService();
	
	public static void main(String[] args) {
		
		LocalDateTime now = LocalDateTime.of(2017, 6, 30, 9, 0);
		
		List<Schedule> schedules = new LinkedList<>();
		
		Schedule schedule;
		
		schedule = new Schedule();
		schedule.setActive(true);
		schedule.setFrequency(3);
		schedule.setOffset(new Timestamp(Date.from(LocalDateTime.of(2017, 2, 2, 9, 0).atZone(ZoneId.systemDefault()).toInstant()).getTime()));
		schedule.setPeriodBoundary(SchedulePeriodBoundary.START);
		schedule.setRecurrence(ScheduleRecurrence.MONTHLY);
		schedules.add(schedule);
		
		schedule = new Schedule();
		schedule.setActive(true);
		schedule.setFrequency(1);
		schedule.setOffset(new Timestamp(Date.from(LocalDateTime.of(2017, 2, 2, 9, 0).atZone(ZoneId.systemDefault()).toInstant()).getTime()));
		schedule.setPeriodBoundary(SchedulePeriodBoundary.END);
		schedule.setRecurrence(ScheduleRecurrence.MONTHLY);
		schedules.add(schedule);
		
		schedule = new Schedule();
		schedule.setActive(true);
		schedule.setFrequency(1);
		schedule.setOffset(new Timestamp(Date.from(LocalDateTime.of(2017, 2, 2, 9, 0).atZone(ZoneId.systemDefault()).toInstant()).getTime()));
		schedule.setPeriodBoundary(SchedulePeriodBoundary.UNDEFINED);
		schedule.setRecurrence(ScheduleRecurrence.MONTHLY);
		schedules.add(schedule);
		
		System.out.println("It is now " + now + ".\n");
		for (Schedule s : schedules) {
			shit(s, now);
		}
		
	}
	
	private static void shit(Schedule schedule, LocalDateTime now) {
		System.out.println("Scheduled task:");
		System.out.println("\tOffset = " + schedule.getOffset());
		System.out.println("\tFrequency = " + schedule.getFrequency());
		System.out.println("\tRecurrence = " + schedule.getRecurrence());
		System.out.println("\tPeriodBoundary = " + schedule.getPeriodBoundary());
		if (scheduledTaskService.isTaskDue(schedule, now)) {
			System.out.println("\tThis task will run right now.");
		}
		else {
			System.out.println("\tThis task will not run right now.");
		}
		System.out.println();
	}

}
