package org.lacitysan.landfill.server.service.mobile.model;

import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;

public class MobileWarmspotData {
	
	private String mId;
    private String mLocation;
    private String mGridId;
    private String mDate;
    private String mDescription;
    private String mEstimatedSize;
    private String mInspectorFullName;
    private String mInspectorUserName;
    private Double mMaxMethaneReading;
	private Instrument mInstrument;
    
	public String getmId() {
		return mId;
	}
	
	public void setmId(String mId) {
		this.mId = mId;
	}
	
	public String getmLocation() {
		return mLocation;
	}
	
	public void setmLocation(String mLocation) {
		this.mLocation = mLocation;
	}
	
	public String getmGridId() {
		return mGridId;
	}
	
	public void setmGridId(String mGridId) {
		this.mGridId = mGridId;
	}
	
	public String getmDate() {
		return mDate;
	}
	
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	
	public String getmDescription() {
		return mDescription;
	}
	
	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}
	
	public String getmEstimatedSize() {
		return mEstimatedSize;
	}

	public void setmEstimatedSize(String mEstimatedSize) {
		this.mEstimatedSize = mEstimatedSize;
	}

	public String getmInspectorFullName() {
		return mInspectorFullName;
	}
	
	public void setmInspectorFullName(String mInspectorFullName) {
		this.mInspectorFullName = mInspectorFullName;
	}
	
	public String getmInspectorUserName() {
		return mInspectorUserName;
	}
	
	public void setmInspectorUserName(String mInspectorUserName) {
		this.mInspectorUserName = mInspectorUserName;
	}
	
	public Double getmMaxMethaneReading() {
		return mMaxMethaneReading;
	}
	
	public void setmMaxMethaneReading(Double mMaxMethaneReading) {
		this.mMaxMethaneReading = mMaxMethaneReading;
	}

	public Instrument getmInstrument() {
		return mInstrument;
	}

	public void setmInstrument(Instrument mInstrument) {
		this.mInstrument = mInstrument;
	}

}
