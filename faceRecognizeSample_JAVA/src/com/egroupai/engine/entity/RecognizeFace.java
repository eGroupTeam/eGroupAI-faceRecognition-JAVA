package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.egroupai.engine.util.AttributeCheck;

/** 
* @author 作者 eGroupAI
* @date 2018年8月12日 下午1:00:53 
* @version 
* @description:
*/
public class RecognizeFace{
	private AttributeCheck attributeCheck = new AttributeCheck();
	private double threshold;
	private String resolution;	
	private String outputFramePath;
	private String outputFacePath;
	private String outputMotionFramePath;
	private String cam;
	private String rtsp;
	private String videoPath;
	private String photoListPath;
	private Integer minimumFaceSize;
	private Integer threads;
	private String trainedBinaryPath;
	private String trainedFaceInfoPath;
	private String jsonPath;
	private StringBuilder cli;
	private List<String> commandList = new ArrayList<String>();
	private String disk;
	private String enginePath;
	private boolean isHideMainWindow = true;	
	private boolean isHideThreadWindow = true;
	private Integer sampleRate;
	private String sectionId;
	
	private long responseTime; 
		
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	public boolean isHideMainWindow() {
		return isHideMainWindow;
	}
	public void setHideMainWindow(boolean isHideMainWindow) {
		this.isHideMainWindow = isHideMainWindow;
	}	
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getOutputFramePath() {
		return outputFramePath;
	}
	public void setOutputFramePath(String outputFramePath) {
		this.outputFramePath = outputFramePath;
	}
	public String getOutputFacePath() {
		return outputFacePath;
	}
	public void setOutputFacePath(String outputFacePath) {
		this.outputFacePath = outputFacePath;
	}
	public String getCam() {
		return cam;
	}
	public void setCam(String cam) {
		this.cam = cam;
	}	
	public String getRtsp() {
		return rtsp;
	}
	public void setRtsp(String rtsp) {
		this.rtsp = rtsp;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public String getPhotoListPath() {
		return photoListPath;
	}
	public void setPhotoListPath(String photoListPath) {
		this.photoListPath = photoListPath;
	}	
	public Integer getMinimumFaceSize() {
		return minimumFaceSize;
	}
	public void setMinimumFaceSize(Integer minimumFaceSize) {
		this.minimumFaceSize = minimumFaceSize;
	}
	public Integer getThreads() {
		return threads;
	}
	public void setThreads(Integer threads) {
		this.threads = threads;
	}
	
	public String getTrainedBinaryPath() {
		return trainedBinaryPath;
	}
	public void setTrainedBinaryPath(String trainedBinaryPath) {
		this.trainedBinaryPath = trainedBinaryPath;
	}
	public String getTrainedFaceInfoPath() {
		return trainedFaceInfoPath;
	}
	public void setTrainedFaceInfoPath(String trainedFaceInfoPath) {
		this.trainedFaceInfoPath = trainedFaceInfoPath;
	}
	
	public String getJsonPath() {
		return jsonPath;
	}
	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}
	public StringBuilder getCli() {
		return cli;
	}
	public void setCli(StringBuilder cli) {
		this.cli = cli;
	}
	public String getEnginePath() {
		return enginePath;
	}
	public void setEnginePath(String enginePath) {
		this.enginePath = enginePath;
	}
	public void generateCli() {
		this.disk = enginePath.substring(0,1);
		if(attributeCheck.stringsNotNull(enginePath,disk)){
			String inputSource = " --cam "+cam;
			if(attributeCheck.stringsNotNull(rtsp)){
				inputSource = " --rtsp "+rtsp;	
			}else if(attributeCheck.stringsNotNull(videoPath)){
				inputSource = " --video "+videoPath;	
			}else if(attributeCheck.stringsNotNull(photoListPath)){
				inputSource = " --photo-list "+photoListPath;
			}		
			cli = new StringBuilder(
					"cd "+enginePath+" && "+disk+": && RecognizeFace "
					+ "--threshold "+threshold+" "
					+ (isHideMainWindow==true?" --hide-main-window ":"")
					+ (isHideThreadWindow==true?"":" --show-thread-window ")
					+ "--resolution "+resolution+" "
					+ "--output-frame \""+outputFramePath+"\" "
					+ "--output-face \""+outputFacePath+"\" "
					+ "--output-motion-frame \""+outputMotionFramePath+"\" "
					+ inputSource+" "
					+ "--minimum-face-size "+minimumFaceSize+" "
					+ "--threads "+threads+" "
					+ "--sample-rate "+sampleRate+" "
					+ trainedBinaryPath+" "+trainedFaceInfoPath+" "+jsonPath+"");
		}else{
			cli = null;
		}
		System.out.println("cli="+cli);
	}	
	public List<String> getCommandList() {
		if(attributeCheck.stringsNotNull(cli.toString())){
			commandList = new ArrayList<String>();
			commandList.add("cmd");
			commandList.add("/"+disk);
			commandList.add(cli.toString().replace("/", "/"));
		}
		return commandList;
	}
	public void setCommandList(List<String> commandList) {
		this.commandList = commandList;
	}
	public String getDisk() {
		return disk;
	}
	public void setDisk(String disk) {
		this.disk = disk;
	}
	public boolean isHideThreadWindow() {
		return isHideThreadWindow;
	}
	public void setHideThreadWindow(boolean isHideThreadWindow) {
		this.isHideThreadWindow = isHideThreadWindow;
	}
	public Long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public Integer getSampleRate() {
		return sampleRate;
	}
	public void setSampleRate(Integer sampleRate) {
		this.sampleRate = sampleRate;
	}
	public String getOutputMotionFramePath() {
		return outputMotionFramePath;
	}
	public void setOutputMotionFramePath(String outputMotionFramePath) {
		this.outputMotionFramePath = outputMotionFramePath;
	}	
	
}
