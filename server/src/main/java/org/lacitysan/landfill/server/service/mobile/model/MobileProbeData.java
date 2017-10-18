package org.lacitysan.landfill.server.service.mobile.model;

/**
 * @author Alvin Quach
 */
public class MobileProbeData {

	private Double mBarometricPressure;
	private String mDate;
	private String mId;
	private String mInspectorName;
	private String mInspectorUserName;
	private String mLocation;
	private Double mMethanePercentage;
	private String mProbeNumber;
	private String mRemarks;
	private Double mWaterPressure;
	private Integer mInstrument; // TODO Change this to integer on Andriod side.

	
	public Integer getmInstrument() {
		return mInstrument;
	}

	public void setmInstrument(Integer mInstrument) {
		this.mInstrument = mInstrument;
	}

	public Double getmBarometricPressure() {
		return mBarometricPressure;
	}

	public void setmBarometricPressure(Double mBarometricPressure) {
		this.mBarometricPressure = mBarometricPressure;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
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

	public String getmLocation() {
		return mLocation;
	}

	public void setmLocation(String mLocation) {
		this.mLocation = mLocation;
	}

	public Double getmMethanePercentage() {
		return mMethanePercentage;
	}

	public void setmMethanePercentage(Double mMethanePercentage) {
		this.mMethanePercentage = mMethanePercentage;
	}

	public String getmProbeNumber() {
		return mProbeNumber;
	}

	public void setmProbeNumber(String mProbeNumber) {
		this.mProbeNumber = mProbeNumber;
	}

	public String getmRemarks() {
		return mRemarks;
	}

	public void setmRemarks(String mRemarks) {
		this.mRemarks = mRemarks;
	}

	public Double getmWaterPressure() {
		return mWaterPressure;
	}

	public void setmWaterPressure(Double mWaterPressure) {
		this.mWaterPressure = mWaterPressure;
	}
}
