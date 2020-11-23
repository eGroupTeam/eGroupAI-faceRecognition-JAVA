package com.egroupai.engine.entity; 

/** 
* @author 作者 daniel
* @date 2019年12月18日 上午9:28:49 
* @version 
* @description:
*/

public class RecognizeResultData {
	private Integer imageCount;
	private String imagePerSec;
	private String totalTime;
	private String startTime;
	private String endTime;
	
	public Integer getImageCount() {
		return imageCount;
	}
	public void setImageCount(Integer imageCount) {
		this.imageCount = imageCount;
	}	
	public String getImagePerSec() {
		return imagePerSec;
	}
	public void setImagePerSec(String imagePerSec) {
		this.imagePerSec = imagePerSec;
	}
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
}
