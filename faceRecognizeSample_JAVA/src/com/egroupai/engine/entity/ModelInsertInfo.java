package com.egroupai.engine.entity; 

/** 
* @author 作者 eGroupAI
* @date 2019年4月25日 上午7:54:46 
* @version 
* @description:
*/

public class ModelInsertInfo {
	String datetimeString;
	String insertModelStatus;
	Integer insertFacesCount;
	Integer insertPeopleCount;
	Integer CurrDBFaceCout;
	Integer CurrDBPeopleCount;
	String insertProcessTime;
	public String getDatetimeString() {
		return datetimeString;
	}
	public void setDatetimeString(String datetimeString) {
		this.datetimeString = datetimeString;
	}
	public String getInsertModelStatus() {
		return insertModelStatus;
	}
	public void setInsertModelStatus(String insertModelStatus) {
		this.insertModelStatus = insertModelStatus;
	}	
	public Integer getInsertFacesCount() {
		return insertFacesCount;
	}
	public void setInsertFacesCount(Integer insertFacesCount) {
		this.insertFacesCount = insertFacesCount;
	}
	public Integer getInsertPeopleCount() {
		return insertPeopleCount;
	}
	public void setInsertPeopleCount(Integer insertPeopleCount) {
		this.insertPeopleCount = insertPeopleCount;
	}
	public Integer getCurrDBFaceCout() {
		return CurrDBFaceCout;
	}
	public void setCurrDBFaceCout(Integer currDBFaceCout) {
		CurrDBFaceCout = currDBFaceCout;
	}
	public Integer getCurrDBPeopleCount() {
		return CurrDBPeopleCount;
	}
	public void setCurrDBPeopleCount(Integer currDBPeopleCount) {
		CurrDBPeopleCount = currDBPeopleCount;
	}
	public String getInsertProcessTime() {
		return insertProcessTime;
	}
	public void setInsertProcessTime(String insertProcessTime) {
		this.insertProcessTime = insertProcessTime;
	}
	
}
