package com.egroupai.engine.entity;

/** 
* @author 作者 eGroupAI Team
* @date 2018年8月12日 下午10:47:59 
* @version 
* @description:
*/

public class ModelSwitch{	
	private String newModelPath;
	private String switchFilePath;
	private String modelSwitchStatusPath;
	private String enginePath;
	
	public String getNewModelPath() {
		return newModelPath;
	}
	public void setNewModelPath(String newModelPath) {
		this.newModelPath = newModelPath;
	}
	public String getSwitchFilePath() {
		return switchFilePath;
	}
	public void setSwitchFilePath(String switchFilePath) {
		this.switchFilePath = switchFilePath;
	}	
	public String getModelSwitchStatusPath() {
		return modelSwitchStatusPath;
	}
	public void setModelSwitchStatusPath(String modelSwitchStatusPath) {
		this.modelSwitchStatusPath = modelSwitchStatusPath;
	}
	public String getEnginePath() {
		return enginePath;
	}
	public void setEnginePath(String enginePath) {
		this.enginePath = enginePath;
	}
}
