package com.egroupai.engine.status.entity; 

/** 
* @author 作者 daniel
* @date 2019年11月4日 下午3:04:36 
* @version 
* @description:
*/

public class StartupStatus {
	private Integer checkId;
	private String checkName;
	private boolean checkFlag;
	
	public Integer getCheckId() {
		return checkId;
	}
	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public boolean isCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}
}
