package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;

/** 
* @author 作者 eGroupAI
* @date 2019年2月11日 上午11:12:18 
* @version 
* @description:
*/
/** 
* @author Daniel
* @date 2019年2月11日 上午11:12:18 
* @version 
* @description:
*/
public class TrainResult {
	private List<String> passFacePathList = new ArrayList<>();
	private List<String> failFacePathList = new ArrayList<>();
	private Integer fileSize;
	private Integer faceSize;
	private String processingTime;	
	private String avgPprocessingTime;	
	private List<TrainInfo> trainInfoList = new ArrayList<>();
	// programe control
	private boolean trainResultFileExist = true;
	private boolean trainCmdSuccess = true;
	private boolean trainStatus = false;
	private Integer trainSize;
			
	public boolean isTrainResultFileExist() {
		return trainResultFileExist;
	}
	public void setTrainResultFileExist(boolean trainResultFileExist) {
		this.trainResultFileExist = trainResultFileExist;
	}
	public List<String> getPassFacePathList() {
		return passFacePathList;
	}
	public void setPassFacePathList(List<String> passFacePathList) {
		this.passFacePathList = passFacePathList;
	}
	public List<String> getFailFacePathList() {
		return failFacePathList;
	}
	public void setFailFacePathList(List<String> failFacePathList) {
		this.failFacePathList = failFacePathList;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	public Integer getFaceSize() {
		return faceSize;
	}
	public void setFaceSize(Integer faceSize) {
		this.faceSize = faceSize;
	}
	
	public String getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(String processingTime) {
		this.processingTime = processingTime;
	}	
	public String getAvgPprocessingTime() {
		return avgPprocessingTime;
	}
	public void setAvgPprocessingTime(String avgPprocessingTime) {
		this.avgPprocessingTime = avgPprocessingTime;
	}
	public List<TrainInfo> getTrainInfoList() {
		return trainInfoList;
	}
	public void setTrainInfoList(List<TrainInfo> trainInfoList) {
		this.trainInfoList = trainInfoList;
	}
	public boolean isTrainCmdSuccess() {
		return trainCmdSuccess;
	}
	public void setTrainCmdSuccess(boolean trainCmdSuccess) {
		this.trainCmdSuccess = trainCmdSuccess;
	}
	public boolean isTrainStatus() {
		return trainStatus;
	}
	public void setTrainStatus(boolean trainStatus) {
		this.trainStatus = trainStatus;
	}
	public Integer getTrainSize() {
		return trainSize;
	}
	public void setTrainSize(Integer trainSize) {
		this.trainSize = trainSize;
	}	
	
}
