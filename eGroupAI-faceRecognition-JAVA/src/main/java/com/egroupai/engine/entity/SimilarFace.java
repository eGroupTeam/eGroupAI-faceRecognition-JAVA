package com.egroupai.engine.entity;

import io.swagger.v3.oas.annotations.media.Schema;

/** 
* @author 作者 eGroupAI Team
* @date 2018年8月16日 上午8:40:51 
* @version 
* @description:
*/
public class SimilarFace {
	@Schema(description = "人臉識別碼")
	private String personFaceId;
	@Schema(description = "人臉路徑")
	private String personFacePath;
	@Schema(description = "相似度")
	private String similarity;
	
	public String getPersonFaceId() {
		return personFaceId;
	}
	public void setPersonFaceId(String personFaceId) {
		this.personFaceId = personFaceId;
	}
	public String getPersonFacePath() {
		return personFacePath;
	}
	public void setPersonFacePath(String personFacePath) {
		this.personFacePath = personFacePath;
	}
	public String getSimilarity() {
		return similarity;
	}
	public void setSimilarity(String similarity) {
		this.similarity = similarity;
	}
}
