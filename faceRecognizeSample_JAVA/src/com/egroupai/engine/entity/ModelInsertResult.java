package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;

/** 
* @author 作者 eGroupAI Team
* @date 2019年4月25日 上午7:54:57 
* @version 
* @description:
*/

public class ModelInsertResult {
	List<ModelInsertInfo> modelInsertInfoList = new ArrayList<>();

	public List<ModelInsertInfo> getModelInsertInfoList() {
		return modelInsertInfoList;
	}

	public void setModelInsertInfoList(List<ModelInsertInfo> modelInsertInfoList) {
		this.modelInsertInfoList = modelInsertInfoList;
	}
}
