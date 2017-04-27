package org.lacitysan.landfill.server.persistence.entity.system;

import java.sql.Timestamp;
import java.util.Date;

import org.lacitysan.landfill.server.persistence.entity.user.User;

/**
 * Instances of classes that implement this interface can be tracked by their
 * creation date, creator, last modification date, and last modificaiton date.
 * @author Alvin Quach
 */
public interface Trackable {
	
	/** Gets the <code>User</code> that created this object. */
	public User getCreatedBy();

	/** Sets the <code>User</code> that created this object. */
	public void setCreatedBy(User createdBy);

	/** Gets the date and time that this object was created. */
	public Date getCreatedDate();

	/** Sets the date and time that this object was created. */
	public void setCreatedDate(Timestamp createdDate);
	
	/** Gets the last <code>User</code> that modified this object. */
	public User getModifiedBy();

	/** Sets the last <code>User</code> that modified this object. */
	public void setModifiedBy(User modifiedBy);

	/** Gets the date and time that this object was last modified. */
	public Timestamp getModifiedDate();

	/** Sets the date and time that this object was last modified. */
	public void setModifiedDate(Timestamp modifiedDate);

}
