package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.egroup.util.AttributeCheck;

/** 
* @author 作者 eGroupAI Team
* @date 2019年2月11日 上午11:12:18 
* @version 
* @description:
*/
/** 
* @author eGroupAI Team
* @date 2019年2月11日 上午11:12:18 
* @version 
* @description:
*/
public class TrainResult {
	private List<String> passFacePathList;
	private List<String> failFacePathList;
	private Integer fileSize;
	private Integer faceSize;
	private String processingTime;	
	private String avgPprocessingSpped;	
	private List<TrainInfo> trainInfoList;
	// programe control
	private boolean trainResultFileExist = true;
	private boolean trainCmdSuccess = true;
	private boolean trainStatus = false;
	private Integer trainSize;
	// init func
	private AttributeCheck attributeCheck;
			
	public boolean isTrainResultFileExist() {
		return trainResultFileExist;
	}
	public void setTrainResultFileExist(boolean trainResultFileExist) {
		this.trainResultFileExist = trainResultFileExist;
	}
	public List<String> getPassFacePathList() {
		if(attributeCheck==null){
			attributeCheck = new AttributeCheck();
		}
		if(!attributeCheck.listNotEmpty(passFacePathList)){
			passFacePathList = new ArrayList<>();
		}
		return passFacePathList;
	}
	public void setPassFacePathList(List<String> passFacePathList) {
		this.passFacePathList = passFacePathList;
	}
	public List<String> getFailFacePathList() {
		if(attributeCheck==null){
			attributeCheck = new AttributeCheck();
		}
		if(!attributeCheck.listNotEmpty(failFacePathList)){
			failFacePathList = new ArrayList<>();
		}
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
	public String getAvgPprocessingSpped() {
		return avgPprocessingSpped;
	}
	public void setAvgPprocessingSpped(String avgPprocessingSpped) {
		this.avgPprocessingSpped = avgPprocessingSpped;
	}
	public List<TrainInfo> getTrainInfoList() {		
		if(attributeCheck==null){
			attributeCheck = new AttributeCheck();
		}
		if(!attributeCheck.listNotEmpty(trainInfoList)){
			trainInfoList = new ArrayList<>();
		}
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
