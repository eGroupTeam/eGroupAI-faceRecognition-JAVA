package com.egroupai.engine.entity; 

/** 
* @author 作者 daniel
* @date 2019年12月24日 下午2:44:37 
* @version 
* @description:
*/

public class ModelCompareResultData {
	private String totalTime;
	private String startTime;
	private String endTime;
	private String imagePerSec;
	private Integer samPersonCount;
	private Integer notSamPersonCount;
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}	
	public String getImagePerSec() {
		return imagePerSec;
	}
	public void setImagePerSec(String imagePerSec) {
		this.imagePerSec = imagePerSec;
	}
	public Integer getSamPersonCount() {
		return samPersonCount;
	}
	public void setSamPersonCount(Integer samPersonCount) {
		this.samPersonCount = samPersonCount;
	}
	public Integer getNotSamPersonCount() {
		return notSamPersonCount;
	}
	public void setNotSamPersonCount(Integer notSamPersonCount) {
		this.notSamPersonCount = notSamPersonCount;
	}
}
