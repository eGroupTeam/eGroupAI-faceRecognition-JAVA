package com.egroupai.engine.entity; 

/** 
* @author 作者 eGroupAI Team
* @date 2019年2月11日 上午11:33:16 
* @version 
* @description:
*/
public class TrainInfo {
	private String time;
	private String status;
	private String facePath;
	private String personId;
	private boolean isFaceQuality = false;
	private boolean isFaceQualityBlurness = false;
	private boolean isFaceQualityLowLuminance = false;
	private boolean isFaceQualityHighLuminance = false;
	private boolean isFaceQualityHeadpose = false;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFacePath() {
		return facePath;
	}
	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public boolean isFaceQuality() {
		return isFaceQuality;
	}
	public void setFaceQuality(boolean isFaceQuality) {
		this.isFaceQuality = isFaceQuality;
	}
	public boolean isFaceQualityBlurness() {
		return isFaceQualityBlurness;
	}
	public void setFaceQualityBlurness(boolean isFaceQualityBlurness) {
		this.isFaceQualityBlurness = isFaceQualityBlurness;
	}
	public boolean isFaceQualityLowLuminance() {
		return isFaceQualityLowLuminance;
	}
	public void setFaceQualityLowLuminance(boolean isFaceQualityLowLuminance) {
		this.isFaceQualityLowLuminance = isFaceQualityLowLuminance;
	}
	public boolean isFaceQualityHighLuminance() {
		return isFaceQualityHighLuminance;
	}
	public void setFaceQualityHighLuminance(boolean isFaceQualityHighLuminance) {
		this.isFaceQualityHighLuminance = isFaceQualityHighLuminance;
	}
	public boolean isFaceQualityHeadpose() {
		return isFaceQualityHeadpose;
	}
	public void setFaceQualityHeadpose(boolean isFaceQualityHeadpose) {
		this.isFaceQualityHeadpose = isFaceQualityHeadpose;
	}
}
