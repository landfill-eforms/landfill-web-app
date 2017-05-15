package org.lacitysan.landfill.server.config;

import org.lacitysan.landfill.server.service.scheduled.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Spring configuration for scheduled tasks, such as scheduled emails.
 * @author Alvin Quach
 */
@Configuration
@EnableScheduling
public class SchedulerConfig {
	
	@Autowired
	ScheduledTaskService scheduledTaskService;
	
	/** Tasks are checked an run every 60 seconds. */
	@Scheduled(fixedRate=60000)
	public void runScheduledTasks() {
		scheduledTaskService.runScheduledTasks();
	}

	@Bean
	public TaskScheduler poolScheduler() {
		return new ThreadPoolTaskScheduler();
	}

}
