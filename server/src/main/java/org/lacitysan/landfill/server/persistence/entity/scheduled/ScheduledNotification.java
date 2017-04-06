package org.lacitysan.landfill.server.persistence.entity.scheduled;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ScheduledNotifications")
@JsonInclude(Include.NON_NULL)
public class ScheduledNotification {

}