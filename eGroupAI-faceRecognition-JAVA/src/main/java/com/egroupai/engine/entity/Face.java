package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.egroup.util.AttributeCheck;

import io.swagger.v3.oas.annotations.media.Schema;


/** 
* @author 作者 eGroupAI Team
* @date 2018年8月16日 上午8:40:44 
* @version 
* @description:
*/
public class Face {	
	public enum FACEQUALITY {
		C1("人臉圖模糊"),
		C2("人臉圖過暗"),
		C3("人臉圖過亮"),
		C4("人臉圖非正臉"),
		PASS("人臉圖品質通過");
		
		private String value;
		
		FACEQUALITY(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	@Schema(description = "有無找到人物")
	private String hasFound;
	@Schema(description = "人物識別碼")
	private String personId;
	@Schema(description = "相似人臉列表")
	private List<SimilarFace> similarFaceList;
	@Schema(description = "影格人臉資訊，內容包含")
	private FrameFace frameFace;
	@Schema(description = "影格路徑")
	private String framePath;
	@Schema(description = "系統時間，單位至毫秒")
	private String systemTime;
	@Schema(description = "影片時間，單位至秒")
	private String videoTime;
	@Schema(description = "影片影格編號")
	private String videoFrameNo;
	@Schema(description = "照片來源路徑")
	private String imageSourcePath;
	@Schema(description = "照片品質")
	private String faceQuality;
	@Schema(description = "照片品質-模糊")
	private String faceQualityBlurness;
	@Schema(description = "照片品質-低光照度")
	private String faceQualityLowLuminance;
	@Schema(description = "照片品質-高光照度")
	private String faceQualityHighLuminance;
	@Schema(description = "照片品質-正臉")
	private String faceQualityHeadpose;
	@Schema(description = "照片品質名稱")
	private String faceQualityName;
	@Schema(description = "照片品質大小")
	private Integer faceSize;
	@Schema(description = "生物辨識-frameId")
	private String currentFrameID;
	@Schema(description = "生物辨識-X軸座標")
	private String livenessHeadposeX;
	@Schema(description = "生物辨識-Y軸座標")
	private String livenessHeadposeY;
	@Schema(description = "生物辨識-Z軸座標")
	private String livenessHeadposeZ;
	@Schema(description = "生物辨識-角度")
	private String livenessHeadposeClass;
	@Schema(description = "生物辨識-結果")
	private String result;
	@Schema(description = "生物辨識-題目編號")
	private String questionID;
	@Schema(description = "生物辨識-臉部標籤")
	private String faceLabel;
	@Schema(description = "生物辨識-深度資訊")
	private String depthInfo;
	
	// init func
	private AttributeCheck attributeCheck;

	// Programe control
	private Integer startIndex;
	private Integer endIndex;
	private boolean success;
	private String message;
	private Integer statusCode;
	
	public String getHasFound() {
		return hasFound;
	}
	public void setHasFound(String hasFound) {
		this.hasFound = hasFound;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public List<SimilarFace> getSimilarFaceList() {
		if(attributeCheck==null){
			attributeCheck = new AttributeCheck();
		}
		if(!attributeCheck.listNotEmpty(similarFaceList)){
			similarFaceList = new ArrayList<>();
		}
		return similarFaceList;
	}
	public void setSimilarFaceList(List<SimilarFace> similarFaceList) {
		this.similarFaceList = similarFaceList;
	}
	public FrameFace getFrameFace() {
		if(frameFace==null){
			frameFace = new FrameFace();
		}
		return frameFace;
	}
	public void setFrameFace(FrameFace frameFace) {
		this.frameFace = frameFace;
	}
	public String getFramePath() {
		return framePath;
	}
	public void setFramePath(String framePath) {
		this.framePath = framePath;
	}
	public String getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
	public String getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}
	public String getVideoFrameNo() {
		return videoFrameNo;
	}
	public void setVideoFrameNo(String videoFrameNo) {
		this.videoFrameNo = videoFrameNo;
	}
	public String getImageSourcePath() {
		return imageSourcePath;
	}
	public void setImageSourcePath(String imageSourcePath) {
		this.imageSourcePath = imageSourcePath;
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
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getFaceQuality() {
		return faceQuality;
	}
	public void setFaceQuality(String faceQuality) {
		this.faceQuality = faceQuality;
	}
	public void setFaceQualityName(String faceQualityName) {
		this.faceQualityName = faceQualityName;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public Integer getFaceSize() {
		return faceSize;
	}
	public void setFaceSize(Integer faceSize) {
		this.faceSize = faceSize;
	}
	public String getFaceQualityBlurness() {
		return faceQualityBlurness;
	}
	public void setFaceQualityBlurness(String faceQualityBlurness) {
		this.faceQualityBlurness = faceQualityBlurness;
	}
	public String getFaceQualityLowLuminance() {
		return faceQualityLowLuminance;
	}
	public void setFaceQualityLowLuminance(String faceQualityLowLuminance) {
		this.faceQualityLowLuminance = faceQualityLowLuminance;
	}
	public String getFaceQualityHighLuminance() {
		return faceQualityHighLuminance;
	}
	public void setFaceQualityHighLuminance(String faceQualityHighLuminance) {
		this.faceQualityHighLuminance = faceQualityHighLuminance;
	}
	public String getFaceQualityHeadpose() {
		return faceQualityHeadpose;
	}
	public void setFaceQualityHeadpose(String faceQualityHeadpose) {
		this.faceQualityHeadpose = faceQualityHeadpose;
	}
	public String getCurrentFrameID() {
		return currentFrameID;
	}
	public void setCurrentFrameID(String currentFrameID) {
		this.currentFrameID = currentFrameID;
	}
	public String getLivenessHeadposeX() {
		return livenessHeadposeX;
	}
	public void setLivenessHeadposeX(String livenessHeadposeX) {
		this.livenessHeadposeX = livenessHeadposeX;
	}
	public String getLivenessHeadposeY() {
		return livenessHeadposeY;
	}
	public void setLivenessHeadposeY(String livenessHeadposeY) {
		this.livenessHeadposeY = livenessHeadposeY;
	}
	public String getLivenessHeadposeZ() {
		return livenessHeadposeZ;
	}
	public void setLivenessHeadposeZ(String livenessHeadposeZ) {
		this.livenessHeadposeZ = livenessHeadposeZ;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getFaceLabel() {
		return faceLabel;
	}
	public void setFaceLabel(String faceLabel) {
		this.faceLabel = faceLabel;
	}
	public String getDepthInfo() {
		return depthInfo;
	}
	public void setDepthInfo(String depthInfo) {
		this.depthInfo = depthInfo;
	}
	public String getFaceQualityName() {
		return faceQualityName;
	}
	public String getLivenessHeadposeClass() {
		return livenessHeadposeClass;
	}
	public void setLivenessHeadposeClass(String livenessHeadposeClass) {
		this.livenessHeadposeClass = livenessHeadposeClass;
	}
	
}
