package com.egroupai.engine.entity;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;


/** 
* @author 作者 Daniel
* @date 2018年8月16日 上午8:40:44 
* @version 
* @description:
*/
public class Face {	
	private String hasFound;
	private String personId;
	private List<SimilarFace> similarFaceList = new ArrayList<SimilarFace>();
	private FrameFace frameFace;
	private String framePath;
	private String systemTime;
	private String videoTime;
	private String videoFrameNo;
	private String imageSourcePath;

	private Integer startIndex;
	private Integer endIndex;
	public String getHasFound() {
		return hasFound;
	}
	public void setHasFound(String hasFound) {
		this.hasFound = hasFound;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public List<SimilarFace> getSimilarFaceList() {
		return similarFaceList;
	}
	public void setSimilarFaceList(List<SimilarFace> similarFaceList) {
		this.similarFaceList = similarFaceList;
	}
	public FrameFace getFrameFace() {
		return frameFace;
	}
	public void setFrameFace(FrameFace frameFace) {
		this.frameFace = frameFace;
	}
	public String getFramePath() {
		return framePath;
	}
	public void setFramePath(String framePath) {
		this.framePath = framePath;
	}
	public String getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
	public String getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}
	public String getVideoFrameNo() {
		return videoFrameNo;
	}
	public void setVideoFrameNo(String videoFrameNo) {
		this.videoFrameNo = videoFrameNo;
	}
	public String getImageSourcePath() {
		return imageSourcePath;
	}
	public void setImageSourcePath(String imageSourcePath) {
		this.imageSourcePath = imageSourcePath;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}	
}
