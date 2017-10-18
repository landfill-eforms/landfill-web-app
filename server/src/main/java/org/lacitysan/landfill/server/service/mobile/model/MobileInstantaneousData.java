package org.lacitysan.landfill.server.service.mobile.model;

/**
 * @author Alvin Quach
 */
public class MobileInstantaneousData {
	
	private String mId;
	private String mLocation;
	private Double mBarometricPressure;
	private String gridId;
	private String mInspectorName;
	private String mInspectorUserName;
	private String mStartDate;
	private String mEndDate;
	private Integer mInstrument;
	private Double methaneReading;
	private String imeNumber; // This will be an array or collection in the future.	
	
	
	public Integer getmInstrument() {
		return mInstrument;
	}

	public void setmInstrument(Integer mInstrument) {
		this.mInstrument = mInstrument;
	}

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

	public Double getmBarometricPressure() {
		return mBarometricPressure;
	}

	public void setmBarometricPressure(Double mBarometricPressure) {
		this.mBarometricPressure = mBarometricPressure;
	}

	public String getGridId() {
		return gridId;
	}

	public void setGridId(String gridId) {
		this.gridId = gridId;
	}

	public String getmInspectorName() {
		return mInspectorName;
	}

	public void setmInspectorName(String mInspectorName) {
		this.mInspectorName = mInspectorName;
	}

	public String getmInspectorUserName() {
		return mInspectorUserName;
	}

	public void setmInspectorUserName(String mInspectorUserName) {
		this.mInspectorUserName = mInspectorUserName;
	}

	public String getmStartDate() {
		return mStartDate;
	}

	public void setmStartDate(String mStartDate) {
		this.mStartDate = mStartDate;
	}

	public String getmEndDate() {
		return mEndDate;
	}

	public void setmEndDate(String mEndDate) {
		this.mEndDate = mEndDate;
	}

	public Double getMethaneReading() {
		return methaneReading;
	}

	public void setMethaneReading(Double methaneReading) {
		this.methaneReading = methaneReading;
	}

	public String getImeNumber() {
		return imeNumber;
	}

	public void setImeNumber(String imeNumber) {
		this.imeNumber = imeNumber;
	}
	
}
