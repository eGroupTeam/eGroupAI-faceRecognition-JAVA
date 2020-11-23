package com.egroupai.engine.entity;

/** 
* @author 作者 eGroupAI Team
* @date 2019年4月22日 下午4:00:15 
* @version 
* @description:
*/

public class ModelAppendInfo {
	private String time;
	private String workingFolderCheckStatus;
	private String workingFolderStatus;
	private Double workingFolderSize;
	private String outputFaceDBCheckStatus;
	private String outputFaceDBStatus;
	private Double outputFaceDBSize;
	private String DBSizeCheckStatus;
	private Integer DBSize;
	private String DBFaceDBPath;
	private boolean isSucess;
	private String errorMessage;
	
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
	public boolean isSucess() {
		return isSucess;
	}
	public void setSucess(boolean isSucess) {
		this.isSucess = isSucess;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getOutputFaceDBCheckStatus() {
		return outputFaceDBCheckStatus;
	}
	public void setOutputFaceDBCheckStatus(String outputFaceDBCheckStatus) {
		this.outputFaceDBCheckStatus = outputFaceDBCheckStatus;
	}
	public String getOutputFaceDBStatus() {
		return outputFaceDBStatus;
	}
	public void setOutputFaceDBStatus(String outputFaceDBStatus) {
		this.outputFaceDBStatus = outputFaceDBStatus;
	}
	public Double getOutputFaceDBSize() {
		return outputFaceDBSize;
	}
	public void setOutputFaceDBSize(Double outputFaceDBSize) {
		this.outputFaceDBSize = outputFaceDBSize;
	}
	public String getDBFaceDBPath() {
		return DBFaceDBPath;
	}
	public void setDBFaceDBPath(String dBFaceDBPath) {
		DBFaceDBPath = dBFaceDBPath;
	}
}
