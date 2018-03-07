package sample.entity;

public class SimilarFace {
	private String faceId;
	private String similarity;
	private String facePath;
	
	public String getFaceId() {
		return faceId;
	}
	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
	public String getSimilarity() {
		return similarity;
	}
	public void setSimilarity(String similarity) {
		this.similarity = similarity;
	}
	public String getFacePath() {
		return facePath;
	}
	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}
	
}
