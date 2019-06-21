package com.egroupai.engine.entity; 

/** 
* @author 作者 eGroupAI
* @date 2019年2月11日 上午11:33:16 
* @version 
* @description:
*/
public class TrainInfo {
	private String time;
	private String status;
	private String facePath;
	private String personId;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFacePath() {
		return facePath;
	}
	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
}
