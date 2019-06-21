package com.egroupai.engine.entity;

import com.egroupai.engine.control.EngineFunc;
import com.egroupai.engine.util.AttributeCheck;

/** 
* @author 作者 eGroupAI
* @date 2018年8月12日 下午10:47:59 
* @version 
* @description:
*/

public class ModelSwitch extends EngineFunc{
	// init func
	private AttributeCheck attributeCheck = new AttributeCheck();
	
	private String newModelPath;
	private String nowModelPath;
	private String switchFilePath;
	private String modelSwitchLogPath;
	private String enginePath;
	
	public String getNewModelPath() {
		return newModelPath;
	}
	public void setNewModelPath(String newModelPath) {
		this.newModelPath = newModelPath;
	}	
	public String getNowModelPath() {
		return nowModelPath;
	}
	public void setNowModelPath(String nowModelPath) {
		this.nowModelPath = nowModelPath;
	}
	public String getSwitchFilePath() {
		return switchFilePath;
	}
	public void setSwitchFilePath(String switchFilePath) {
		this.switchFilePath = switchFilePath;
	}	
	public String getModelSwitchLogPath() {
		return modelSwitchLogPath;
	}
	public void setModelSwitchLogPath(String modelSwitchLogPath) {
		this.modelSwitchLogPath = modelSwitchLogPath;
	}
	public String getEnginePath() {
		return enginePath;
	}
	public void setEnginePath(String enginePath) {
		this.enginePath = enginePath;
	}
	
	
}
