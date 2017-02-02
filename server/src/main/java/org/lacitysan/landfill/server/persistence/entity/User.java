package org.lacitysan.landfill.server.persistence.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name=ApplicationProperty.DATABASE_NAME + ".dbo.Users")
public class User {
	
	@Id
	@Column(name="UserPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;

	@JsonIgnoreProperties({"users"})
	@ManyToMany
	@JoinTable(name="test.dbo.UsersXRefUserGroups", joinColumns=@JoinColumn(name="UserFK"), inverseJoinColumns=@JoinColumn(name="UserGroupFK"))
	private Set<UserGroup> userGroups;
	
	@NotNull
	private Boolean enabled;
	
	@JsonIgnoreProperties({"user"})
	@OneToOne(mappedBy="user")
	private UserProfile userProfile;

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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
}
