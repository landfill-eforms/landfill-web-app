package org.lacitysan.landfill.server.service.datamap.model.mobile;

import java.sql.Time;

/**
 * @author Alvin Quach
 */
public class InstantaneousDataMobile {
	
	private String mId;
	private String gridId;
	private Double methaneReading;
	private Time mStartDate;
	private Time mEndDate;

	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getGridId() {
		return gridId;
	}
	public void setGridId(String gridId) {
		this.gridId = gridId;
	}
	public Double getMethaneReading() {
		return methaneReading;
	}
	public void setMethaneReading(Double methaneReading) {
		this.methaneReading = methaneReading;
	}
	public Time getmStartDate() {
		return mStartDate;
	}
	public void setmStartDate(Time mStartDate) {
		this.mStartDate = mStartDate;
	}
	public Time getmEndDate() {
		return mEndDate;
	}
	public void setmEndDate(Time mEndDate) {
		this.mEndDate = mEndDate;
	}
	
}
