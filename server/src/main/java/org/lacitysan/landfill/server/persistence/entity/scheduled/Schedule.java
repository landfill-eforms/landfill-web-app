package org.lacitysan.landfill.server.persistence.entity.scheduled;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
import org.lacitysan.landfill.server.persistence.enums.scheduled.SchedulePeriodBoundary;
import org.lacitysan.landfill.server.persistence.enums.scheduled.ScheduleRecurrence;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.Schedules")
@AttributeOverride(name="id", column=@Column(name="SchedulePK"))
@JsonInclude(Include.NON_NULL)
public class Schedule extends AbstractEntity {
	
	@NotNull
	@Column(name="RecurrenceString")
	@Enumerated(EnumType.STRING)
	private ScheduleRecurrence recurrence;
	
	@NotNull
	private Timestamp offset;
	
	@NotNull
	@Column(name="PeriodBoundaryString")
	@Enumerated(EnumType.STRING)
	private SchedulePeriodBoundary periodBoundary;
	
	@NotNull
	private Integer frequency;
	
	@NotNull
	private Boolean active;
	
	private Timestamp lastOccurrence;

	public ScheduleRecurrence getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(ScheduleRecurrence recurrence) {
		this.recurrence = recurrence;
	}

	public Timestamp getOffset() {
		return offset;
	}

	public void setOffset(Timestamp offset) {
		this.offset = offset;
	}

	public SchedulePeriodBoundary getPeriodBoundary() {
		return periodBoundary;
	}

	public void setPeriodBoundary(SchedulePeriodBoundary periodBoundary) {
		this.periodBoundary = periodBoundary;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getLastOccurrence() {
		return lastOccurrence;
	}

	public void setLastOccurrence(Timestamp lastOccurrence) {
		this.lastOccurrence = lastOccurrence;
	}
	
}
