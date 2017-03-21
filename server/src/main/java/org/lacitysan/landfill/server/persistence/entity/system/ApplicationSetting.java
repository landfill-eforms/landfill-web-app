package org.lacitysan.landfill.server.persistence.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.config.appconsts.ApplicationConstant;

/**
 * Key value pair for application settings.
 * @author Alvin Quach
 */
@Entity
@Table(name=ApplicationConstant.DATABASE_NAME + ".dbo.ApplicationSettings")
public class ApplicationSetting {
	
	@Id
	@Column(name="Name")
	private String key;
	
	@NotNull
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
