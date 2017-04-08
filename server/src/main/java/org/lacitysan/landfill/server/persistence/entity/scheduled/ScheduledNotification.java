package org.lacitysan.landfill.server.persistence.entity.scheduled;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ScheduledNotifications")
@PrimaryKeyJoinColumn(name="ScheduledEmailFK")
@JsonInclude(Include.NON_NULL)
public class ScheduledNotification extends ScheduledEmail {
	
	@NotNull
	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

}
