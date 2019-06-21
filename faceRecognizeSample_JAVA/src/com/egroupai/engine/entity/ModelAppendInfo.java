package com.egroupai.engine.entity;

/** 
* @author 作者 eGroupAI
* @date 2019年4月22日 下午4:00:15 
* @version 
* @description:
*/

public class ModelAppendInfo {
	private String time;
	private String workingFolderCheckStatus;
	private String workingFolderStatus;
	private Double workingFolderSize;
	private String outputBinaryCheckStatus;
	private String outputBinaryStatus;
	private Double outputBinarySize;
	private String outputFaceInfoCheckStatus;
	private String outputFaceInfoStatus;
	private Double outputFaceInfoSize;
	private String DBSizeCheckStatus;
	private Integer DBSize;
	private String DBBinaryPath;
	private String DBFaceInfoPath;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWorkingFolderCheckStatus() {
		return workingFolderCheckStatus;
	}
	public void setWorkingFolderCheckStatus(String workingFolderCheckStatus) {
		this.workingFolderCheckStatus = workingFolderCheckStatus;
	}
	public String getWorkingFolderStatus() {
		return workingFolderStatus;
	}
	public void setWorkingFolderStatus(String workingFolderStatus) {
		this.workingFolderStatus = workingFolderStatus;
	}
	public Double getWorkingFolderSize() {
		return workingFolderSize;
	}
	public void setWorkingFolderSize(Double workingFolderSize) {
		this.workingFolderSize = workingFolderSize;
	}
	public String getOutputBinaryCheckStatus() {
		return outputBinaryCheckStatus;
	}
	public void setOutputBinaryCheckStatus(String outputBinaryCheckStatus) {
		this.outputBinaryCheckStatus = outputBinaryCheckStatus;
	}
	public String getOutputBinaryStatus() {
		return outputBinaryStatus;
	}
	public void setOutputBinaryStatus(String outputBinaryStatus) {
		this.outputBinaryStatus = outputBinaryStatus;
	}
	public Double getOutputBinarySize() {
		return outputBinarySize;
	}
	public void setOutputBinarySize(Double outputBinarySize) {
		this.outputBinarySize = outputBinarySize;
	}
	public String getOutputFaceInfoCheckStatus() {
		return outputFaceInfoCheckStatus;
	}
	public void setOutputFaceInfoCheckStatus(String outputFaceInfoCheckStatus) {
		this.outputFaceInfoCheckStatus = outputFaceInfoCheckStatus;
	}
	public String getOutputFaceInfoStatus() {
		return outputFaceInfoStatus;
	}
	public void setOutputFaceInfoStatus(String outputFaceInfoStatus) {
		this.outputFaceInfoStatus = outputFaceInfoStatus;
	}
	public Double getOutputFaceInfoSize() {
		return outputFaceInfoSize;
	}
	public void setOutputFaceInfoSize(Double outputFaceInfoSize) {
		this.outputFaceInfoSize = outputFaceInfoSize;
	}
	public String getDBSizeCheckStatus() {
		return DBSizeCheckStatus;
	}
	public void setDBSizeCheckStatus(String dBSizeCheckStatus) {
		DBSizeCheckStatus = dBSizeCheckStatus;
	}	
	public Integer getDBSize() {
		return DBSize;
	}
	public void setDBSize(Integer dBSize) {
		DBSize = dBSize;
	}
	public String getDBBinaryPath() {
		return DBBinaryPath;
	}
	public void setDBBinaryPath(String dBBinaryPath) {
		DBBinaryPath = dBBinaryPath;
	}
	public String getDBFaceInfoPath() {
		return DBFaceInfoPath;
	}
	public void setDBFaceInfoPath(String dBFaceInfoPath) {
		DBFaceInfoPath = dBFaceInfoPath;
	}
}
