package org.lacitysan.landfill.server.persistence.entity.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.Users")
@JsonInclude(Include.NON_NULL)
public class User {

	@Id
	@Column(name="UserPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String username;

	@NotNull
	@JsonProperty(access=Access.WRITE_ONLY)
	private String password;

	@JsonIgnoreProperties(value={"users", "createdBy", "modifiedBy", "scheduledReports"}, allowSetters=true)
	@ManyToMany
	@JoinTable(name="dbo.UsersXRefUserGroups", joinColumns=@JoinColumn(name="UserFK"), inverseJoinColumns=@JoinColumn(name="UserGroupFK"))
	private Set<UserGroup> userGroups = new HashSet<>();

	@NotNull
	private String firstname;

	@NotNull
	private String middlename;

	@NotNull
	private String lastname;

	@NotNull
	private String emailAddress;

	@NotNull
	private String employeeId;

	@NotNull
	private Boolean enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String printName() {
		return firstname
				+ (middlename == null || middlename.isEmpty() ? " " : " " + middlename.charAt(0) + ". ")
				+ lastname; 
	}
	
	@Override
	public String toString() {
		return username;
	}

}
