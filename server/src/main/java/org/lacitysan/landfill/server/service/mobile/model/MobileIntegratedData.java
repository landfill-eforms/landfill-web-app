package org.lacitysan.landfill.server.service.mobile.model;

import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;

/**
 * @author Alvin Quach
 */
public class MobileIntegratedData {

	private Integer mBagNumber;
	private Double mBarometricPressure;
	private String mEndDate;
	private String mGridId;
	private String mId;
	private String mInspectorName;
	private String mInspectorUserName;
	private Instrument mInstrument;
	private String mLocation;
	private Double mMethaneReading;
	private String mSampleId;
	private String mStartDate;
	private Integer mVolumeReading;

	public Integer getmBagNumber() {
		return mBagNumber;
	}

	public void setmBagNumber(Integer mBagNumber) {
		this.mBagNumber = mBagNumber;
	}

	public Double getmBarometricPressure() {
		return mBarometricPressure;
	}

	public void setmBarometricPressure(Double mBarometricPressure) {
		this.mBarometricPressure = mBarometricPressure;
	}

	public String getmEndDate() {
		return mEndDate;
	}

	public void setmEndDate(String mEndDate) {
		this.mEndDate = mEndDate;
	}

	public String getmGridId() {
		return mGridId;
	}

	public void setmGridId(String mGridId) {
		this.mGridId = mGridId;
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

	public Instrument getmInstrument() {
		return mInstrument;
	}

	public void setmInstrument(Instrument mInstrument) {
		this.mInstrument = mInstrument;
	}

	public String getmLocation() {
		return mLocation;
	}

	public void setmLocation(String mLocation) {
		this.mLocation = mLocation;
	}

	public Double getmMethaneReading() {
		return mMethaneReading;
	}

	public void setmMethaneReading(Double mMethaneReading) {
		this.mMethaneReading = mMethaneReading;
	}

	public String getmSampleId() {
		return mSampleId;
	}

	public void setmSampleId(String mSampleId) {
		this.mSampleId = mSampleId;
	}

	public String getmStartDate() {
		return mStartDate;
	}

	public void setmStartDate(String mStartDate) {
		this.mStartDate = mStartDate;
	}

	public Integer getmVolumeReading() {
		return mVolumeReading;
	}

	public void setmVolumeReading(Integer mVolumeReading) {
		this.mVolumeReading = mVolumeReading;
	}

}
