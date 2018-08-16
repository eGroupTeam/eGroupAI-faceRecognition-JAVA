package com.egroupai.engine.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.egroupai.engine.control.EngineFunc;
import com.egroupai.engine.util.AttributeCheck;

/** 
* @author 作者 Daniel
* @date 2018年8月12日 下午10:47:59 
* @version 
* @description:
*/
/** 
* @author 作者 Daniel
* @date 2018年8月12日 下午10:47:59 
* @version 
* @description:
*/
public class ModelSwitch extends EngineFunc{
	private AttributeCheck attributeCheck = new AttributeCheck();
	private String newModelBinaryPath;
	private String newModelFaceInfoPath;
	private String modelBinaryPath;
	private String modelFaceInfoPath;
	private String switchFilePath;
	public String getNewModelBinaryPath() {
		return newModelBinaryPath;
	}
	public void setNewModelBinaryPath(String newModelBinaryPath) {
		this.newModelBinaryPath = newModelBinaryPath;
	}
	public String getNewModelFaceInfoPath() {
		return newModelFaceInfoPath;
	}
	public void setNewModelFaceInfoPath(String newModelFaceInfoPath) {
		this.newModelFaceInfoPath = newModelFaceInfoPath;
	}
	public String getModelBinaryPath() {
		return modelBinaryPath;
	}
	public void setModelBinaryPath(String modelBinaryPath) {
		this.modelBinaryPath = modelBinaryPath;
	}
	public String getModelFaceInfoPath() {
		return modelFaceInfoPath;
	}
	public void setModelFaceInfoPath(String modelFaceInfoPath) {
		this.modelFaceInfoPath = modelFaceInfoPath;
	}
	public String getSwitchFilePath() {
		return switchFilePath;
	}
	public void setSwitchFilePath(String switchFilePath) {
		this.switchFilePath = switchFilePath;
	}
}
