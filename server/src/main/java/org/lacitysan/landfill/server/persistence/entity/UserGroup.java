package org.lacitysan.landfill.server.persistence.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.lib.enumeration.UserRole;
import org.lacitysan.landfill.server.security.annotation.RestControllerSecurity;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="test.dbo.UserGroups")
public class UserGroup {
	
	@Id
	@Column(name="UserGroupPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String name;
	
	private String description;
	
	@JsonIgnoreProperties({"userGroups"})
	@ManyToMany(mappedBy="userGroups")
	private Set<User> users;
	
	//@JsonIgnoreProperties({"userGroups"})
	@ManyToMany
	@JoinTable(name="test.dbo.UserGroupsXRefUserRoles", joinColumns=@JoinColumn(name="UserRoleFK"), inverseJoinColumns=@JoinColumn(name="UserGroupFK"))
	@Enumerated(EnumType.ORDINAL)
	private Set<UserRole> userRoles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
