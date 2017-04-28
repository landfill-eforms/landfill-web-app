package org.lacitysan.landfill.server.persistence.entity.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.UserActivities")
@JsonInclude(Include.NON_NULL)
public class UserActivity implements Comparable<UserActivity> {

	@Id
	@Column(name="UserActivityPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnoreProperties(value={"userGroups", "enabled"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name="UserFK")
	private User user;
	
	@NotNull
	private Timestamp date;
	
	// TODO Implement more detailed activity info, links, etc.
	@NotNull
	private String activity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Override
	public int compareTo(UserActivity o) {
		// Sort by reverse date.
		return o.getDate().compareTo(date);
	}
	
}
