package org.lacitysan.landfill.server.service.mobile.model;

/**
 * @author Alvin Quach
 */
public class MobileIseData {

	private String mId;
	private String mIseNumber;
	private String mLocation;
	private String mGridId;
	private String mDate;
	private String mDescription;
	private String mInspectorUserName;
	private Double mMethaneReading;
	private Integer mInstrument;

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

	public String getmIseNumber() {
		return mIseNumber;
	}

	public void setmIseNumber(String mIseNumber) {
		this.mIseNumber = mIseNumber;
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

	public String getmInspectorUserName() {
		return mInspectorUserName;
	}

	public void setmInspectorUserName(String mInspectorUserName) {
		this.mInspectorUserName = mInspectorUserName;
	}

	public Double getmMethaneReading() {
		return mMethaneReading;
	}

	public void setmMethaneReading(Double mMethaneReading) {
		this.mMethaneReading = mMethaneReading;
	}

}
