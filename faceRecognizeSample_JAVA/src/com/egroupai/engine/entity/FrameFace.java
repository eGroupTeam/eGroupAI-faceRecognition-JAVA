package com.egroupai.engine.entity;

/** 
* @author 作者 eGroupAI Team
* @date 2018年8月16日 上午8:47:46 
* @version 
* @description:
*/
public class FrameFace {
//	@Schema(description = "影格人臉編號")
	private String frameFaceNo;
//	@Schema(description = "影格人臉路徑")
	private String frameFacePath;
	
	public String getFrameFaceNo() {
		return frameFaceNo;
	}
	public void setFrameFaceNo(String frameFaceNo) {
		this.frameFaceNo = frameFaceNo;
	}
	public String getFrameFacePath() {
		return frameFacePath;
	}
	public void setFrameFacePath(String frameFacePath) {
		this.frameFacePath = frameFacePath;
	}
}
