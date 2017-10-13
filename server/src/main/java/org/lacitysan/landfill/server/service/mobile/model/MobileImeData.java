package org.lacitysan.landfill.server.service.mobile.model;

/**
 * @author Alvin Quach
 */
public class MobileImeData {

	private String mId;
	private String mImeNumber;
	private String mLocation;
	private String mGridId;
	private String mDate;
	private String mDescription;
	private String mInspectorUserName;
	private Double mMethaneReading;
	private String mInstrument; // TODO Change this to integer on Andriod side.

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmImeNumber() {
		return mImeNumber;
	}

	public void setmImeNumber(String mImeNumber) {
		this.mImeNumber = mImeNumber;
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

	public String getmInstrument() {
		return mInstrument;
	}

	public void setmInstrument(String mInstrument) {
		this.mInstrument = mInstrument;
	}

}
