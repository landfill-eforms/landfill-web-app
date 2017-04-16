package org.lacitysan.landfill.server.persistence.entity.user;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.UserGroups")
@JsonInclude(Include.NON_NULL)
public class UserGroup {
	
	@Id
	@Column(name="UserGroupPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String name;
	
	private String description;
	
	// TODO Find a better way to implement/retrieve 'modified by' and 'created by' info.
	@JsonIgnoreProperties(value={"userGroups", "enabled"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name="CreatedByFK")
	private User createdBy;
	
	private Timestamp createdDate;
	
	@JsonIgnoreProperties(value={"userGroups", "enabled"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name="ModifiedByFK")
	private User modifiedBy;
	
	private Timestamp modifiedDate;
	
	@JsonIgnoreProperties(value={"userGroups", "enabled"}, allowSetters=true)
	@ManyToMany(mappedBy="userGroups")
	private Set<User> users = new HashSet<>();
	
	@ElementCollection(targetClass=UserPermission.class)
	@JoinTable(name="dbo.UserGroupsXRefUserPermissions", joinColumns=@JoinColumn(name="UserGroupFK"))
	@Column(name="UserPermissionString")
	@Enumerated(EnumType.STRING)
	private Set<UserPermission> userPermissions = new HashSet<>();

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

	public Set<UserPermission> getUserPermissions() {
		return userPermissions;
	}

	public void setUserPermissions(Set<UserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
