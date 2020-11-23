package com.egroupai.engine.scenario.entity;

import com.egroupai.engine.entity.FrameFace;

/** 
* @author 作者 Daniel
* @date 2018年8月27日 下午3:08:11 
* @version 
* @description:
*/
public class TimeRecord {
	private String systemTime;
	private FrameFace frameFace = new FrameFace();
	private String framePath;
	private String similarity;
	private String personId;
	private String personName;
	private Object object;
	
	public String getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
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
	public String getSimilarity() {
		return similarity;
	}
	public void setSimilarity(String similarity) {
		this.similarity = similarity;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}	
}
