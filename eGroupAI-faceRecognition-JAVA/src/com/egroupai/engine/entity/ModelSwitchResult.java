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
	private boolean isCheckBinaryPass;
	private boolean isCheckFaceInfoPass;
	private boolean isSuccess;
	private String faceReload;
	private String reloadTime;
	
	public boolean isCheckBinaryPass() {
		return isCheckBinaryPass;
	}
	public void setCheckBinaryPass(boolean isCheckBinaryPass) {
		this.isCheckBinaryPass = isCheckBinaryPass;
	}
	public boolean isCheckFaceInfoPass() {
		return isCheckFaceInfoPass;
	}
	public void setCheckFaceInfoPass(boolean isCheckFaceInfoPass) {
		this.isCheckFaceInfoPass = isCheckFaceInfoPass;
	}
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
}
