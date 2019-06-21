package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.egroupai.engine.util.AttributeCheck;

/** 
* @author 作者 eGroupAI
* @date 2019年4月25日 上午8:04:03 
* @version 
* @description:
*/
/** 
* @author daniel
* @date 2019年4月25日 上午8:04:03 
* @version 
* @description:
*/
public class ModelInsert {
	private String ListPath;
	private String enginePath;
	private List<String> modelBinaryList = new ArrayList<>(); 
	private List<String> modelFaceInfoList = new ArrayList<>();	
		
	public String getEnginePath() {
		return enginePath;
	}
	public void setEnginePath(String enginePath) {
		this.enginePath = enginePath;
	}
	public String getListPath() {
		return ListPath;
	}
	public void setListPath(String listPath) {
		ListPath = listPath;
	}
	public List<String> getModelBinaryList() {
		return modelBinaryList;
	}
	public void setModelBinaryList(List<String> modelBinaryList) {
		this.modelBinaryList = modelBinaryList;
	}
	public List<String> getModelFaceInfoList() {
		return modelFaceInfoList;
	}
	public void setModelFaceInfoList(List<String> modelFaceInfoList) {
		this.modelFaceInfoList = modelFaceInfoList;
	} 
}
