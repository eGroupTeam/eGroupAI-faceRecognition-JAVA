package com.egroupai.engine.entity; 

/** 
* @author 作者 eGroupAI Team
* @date 2019年2月11日 下午4:54:35 
* @version 
* @description:
*/
/** 
* @author eGroupAI Team
* @date 2019年2月11日 下午4:54:35 
* @version 
* @description:
*/
public class ModelSwitchResult {
	private boolean isSuccess;
	private String faceReload;
	private String reloadTime;
	private String faceDB;
		
	public String getFaceReload() {
		return faceReload;
	}
	public void setFaceReload(String faceReload) {
		this.faceReload = faceReload;
	}
	public String getReloadTime() {
		return reloadTime;
	}
	public void setReloadTime(String reloadTime) {
		this.reloadTime = reloadTime;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getFaceDB() {
		return faceDB;
	}
	public void setFaceDB(String faceDB) {
		this.faceDB = faceDB;
	}
	
}
