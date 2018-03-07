package sample.entity;

import java.util.ArrayList;
import java.util.List;

public class Face {
	private String loginID;
	private String createDate;
	private String persistedFaceID;
	private String faceName;
	private String facePath;
	
	//ignore
	private String hasFace;
	private String faceSorting;	
	private String faceId;
	private String top;
	private String bottom;
	private String left;
	private String right;
	private String width;
	private String height;
	private String age;
	private String gender;
	private String eyeLeftX;
	private String eyeLeftY;
	private String eyeRightX;
	private String eyeRightY;
	private String noseX;
	private String noseY;
	private String mouthLeftX;
	private String mouthLeftY;
	private String mouthRightX;
	private String mouthRightY;
	private String face_src;
	private String frame_src;
	private String time;
	private List<SimilarFace> similarFaceList = new ArrayList<SimilarFace>();
	private Integer startIndex;
	private Integer endIndex;	
	private String videoTime;
	private String videoFrameID;	
	
	//程式控制
	private boolean hasNext;
	
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}	
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}	

	public String getPersistedFaceID() {
		return persistedFaceID;
	}
	public void setPersistedFaceID(String persistedFaceID) {
		this.persistedFaceID = persistedFaceID;
	}
	
	public String getFaceName() {
		return faceName;
	}
	public void setFaceName(String faceName) {
		this.faceName = faceName;
	}
	
	public String getFacePath() {
		return facePath;
	}
	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}
	
	public String getHasFace() {
		return hasFace;
	}
	public void setHasFace(String hasFace) {
		this.hasFace = hasFace;
	}
	
	public String getFaceSorting() {
		return faceSorting;
	}
	public void setFaceSorting(String faceSorting) {
		this.faceSorting = faceSorting;
	}
	
	public String getFaceId() {
		return faceId;
	}
	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}
	
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	
	public String getBottom() {
		return bottom;
	}
	public void setBottom(String bottom) {
		this.bottom = bottom;
	}
	
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getEyeLeftX() {
		return eyeLeftX;
	}
	public void setEyeLeftX(String eyeLeftX) {
		this.eyeLeftX = eyeLeftX;
	}
	
	public String getEyeLeftY() {
		return eyeLeftY;
	}
	public void setEyeLeftY(String eyeLeftY) {
		this.eyeLeftY = eyeLeftY;
	}
	
	public String getEyeRightX() {
		return eyeRightX;
	}
	public void setEyeRightX(String eyeRightX) {
		this.eyeRightX = eyeRightX;
	}
	
	public String getEyeRightY() {
		return eyeRightY;
	}
	public void setEyeRightY(String eyeRightY) {
		this.eyeRightY = eyeRightY;
	}
	
	public String getNoseX() {
		return noseX;
	}
	public void setNoseX(String noseX) {
		this.noseX = noseX;
	}
	
	public String getNoseY() {
		return noseY;
	}
	public void setNoseY(String noseY) {
		this.noseY = noseY;
	}
	
	public String getMouthLeftX() {
		return mouthLeftX;
	}
	public void setMouthLeftX(String mouthLeftX) {
		this.mouthLeftX = mouthLeftX;
	}
	
	public String getMouthLeftY() {
		return mouthLeftY;
	}
	public void setMouthLeftY(String mouthLeftY) {
		this.mouthLeftY = mouthLeftY;
	}
	
	public String getMouthRightX() {
		return mouthRightX;
	}
	public void setMouthRightX(String mouthRightX) {
		this.mouthRightX = mouthRightX;
	}
	
	public String getMouthRightY() {
		return mouthRightY;
	}
	public void setMouthRightY(String mouthRightY) {
		this.mouthRightY = mouthRightY;
	}
	
	public String getFace_src() {
		return face_src;
	}
	public void setFace_src(String face_src) {
		this.face_src = face_src;
	}
	
	public String getFrame_src() {
		return frame_src;
	}
	public void setFrame_src(String frame_src) {
		this.frame_src = frame_src;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public List<SimilarFace> getSimilarFaceList() {
		return similarFaceList;
	}
	public void setSimilarFaceList(List<SimilarFace> similarFaceList) {
		this.similarFaceList = similarFaceList;
	}
	
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
	public String getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}
	
	public String getVideoFrameID() {
		return videoFrameID;
	}
	public void setVideoFrameID(String videoFrameID) {
		this.videoFrameID = videoFrameID;
	}
}
