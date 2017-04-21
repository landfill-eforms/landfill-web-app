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
@Table(name="dbo.ScheduledReports")
@PrimaryKeyJoinColumn(name="ScheduledEmailFK")
@JsonInclude(Include.NON_NULL)
public class ScheduledReport extends ScheduledEmail {
	
	@NotNull
	private Integer joo;

	public Integer getJoo() {
		return joo;
	}

	public void setJoo(Integer joo) {
		this.joo = joo;
	}

}
