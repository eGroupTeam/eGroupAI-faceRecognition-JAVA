package com.egroupai.engine.entity; 

/** 
* @author 作者 Daniel
* @date 2018年8月16日 上午8:40:51 
* @version 
* @description:
*/
public class SimilarFace {
	private String personFaceId;
	private String personFacePath;
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
