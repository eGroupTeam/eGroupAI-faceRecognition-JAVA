package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;

/** 
* @author 作者 eGroupAI Team
* @date 2018年8月16日 上午8:40:44 
* @version 
* @description:
*/
public class Face {	
//	@Schema(description = "有無找到人物")
	private String hasFound;
//	@Schema(description = "人物識別碼")
	private String personId;
//	@Schema(description = "相似人臉列表")
	private List<SimilarFace> similarFaceList;
//	@Schema(description = "影格人臉資訊，內容包含")
	private FrameFace frameFace;
//	@Schema(description = "影格路徑")
	private String framePath;
//	@Schema(description = "系統時間，單位至毫秒")
	private String systemTime;
//	@Schema(description = "影片時間，單位至秒")
	private String videoTime;
//	@Schema(description = "影片影格編號")
	private String videoFrameNo;
//	@Schema(description = "照片來源路徑")
	private String imageSourcePath;

	// Programe control
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
		if(similarFaceList==null){
			similarFaceList = new ArrayList<>();
		}
		return similarFaceList;
	}
	public void setSimilarFaceList(List<SimilarFace> similarFaceList) {
		this.similarFaceList = similarFaceList;
	}
	public FrameFace getFrameFace() {
		this.frameFace = new FrameFace();
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
