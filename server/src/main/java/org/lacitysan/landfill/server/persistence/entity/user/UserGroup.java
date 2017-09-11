package org.lacitysan.landfill.server.persistence.entity.user;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
import org.lacitysan.landfill.server.persistence.entity.system.Trackable;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.UserGroups")
@AttributeOverride(name="id", column=@Column(name="UserGroupPK"))
@JsonInclude(Include.NON_NULL)
public class UserGroup extends AbstractEntity implements Trackable {
	
	@NotNull
	private String name;
	
	private String description;
	
	private Boolean containsInspectors;
	
	@JsonIgnoreProperties(value={"userGroups", "enabled", "lastLogin", "createdBy", "createdDate", "modifiedBy", "modifiedDate"}, allowSetters=true)
	@ManyToMany(mappedBy="userGroups")
	private Set<User> users = new HashSet<>();
	
	@ElementCollection(targetClass=UserPermission.class)
	@JoinTable(name="dbo.UserGroupsXRefUserPermissions", joinColumns=@JoinColumn(name="UserGroupFK"))
	@Column(name="UserPermissionString")
	@Enumerated(EnumType.STRING)
	private Set<UserPermission> userPermissions = new HashSet<>();
	
	@JsonIgnoreProperties(value={"userGroups", "enabled", "lastLogin", "createdBy", "createdDate", "modifiedBy", "modifiedDate"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name="CreatedByFK")
	private User createdBy;
	
	private Timestamp createdDate;
	
	@JsonIgnoreProperties(value={"userGroups", "enabled", "lastLogin", "createdBy", "createdDate", "modifiedBy", "modifiedDate"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name="ModifiedByFK")
	private User modifiedBy;
	
	private Timestamp modifiedDate;

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

	public Boolean getContainsInspectors() {
		return containsInspectors;
	}

	public void setContainsInspectors(Boolean containsInspectors) {
		this.containsInspectors = containsInspectors;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<UserPermission> getUserPermissions() {
		return userPermissions;
	}

	public void setUserPermissions(Set<UserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	}

	@Override
	public User getCreatedBy() {
		return createdBy;
	}

	@Override
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public User getModifiedBy() {
		return modifiedBy;
	}

	@Override
	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	@Override
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
