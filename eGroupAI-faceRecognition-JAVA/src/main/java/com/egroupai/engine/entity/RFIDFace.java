package com.egroupai.engine.entity; 

/** 
* @author 作者 daniel
* @date 2019年5月10日 上午10:09:40 
* @version 
* @description:
*/

public class RFIDFace {
	private String hasFound;
	private Integer faceSize;
	private Integer faceConsecutiveFrame;
	private Integer faceDurationFrame;
	
	public String getHasFound() {
		return hasFound;
	}
	public void setHasFound(String hasFound) {
		this.hasFound = hasFound;
	}
	public Integer getFaceSize() {
		return faceSize;
	}
	public void setFaceSize(Integer faceSize) {
		this.faceSize = faceSize;
	}
	public Integer getFaceConsecutiveFrame() {
		return faceConsecutiveFrame;
	}
	public void setFaceConsecutiveFrame(Integer faceConsecutiveFrame) {
		this.faceConsecutiveFrame = faceConsecutiveFrame;
	}
	public Integer getFaceDurationFrame() {
		return faceDurationFrame;
	}
	public void setFaceDurationFrame(Integer faceDurationFrame) {
		this.faceDurationFrame = faceDurationFrame;
	}
}
