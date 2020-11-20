package com.egroupai.yaml.entity;

public class Initialization {
	public enum Mode {
		SORT_FACE("sortFace"),RETRAIN_FACE("retrain");

		private String value;

		Mode(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}	

	private String engineType;
	private String enginePath;
	private String modelPath;
	private String outputFramePath;
	private String inputJsonPath;
	private String batchTrainSec;
	private String threshold;
	private String jsonListPath;
	private String outputFacePath;
	private String summaryResultPath;
	private String mode;
	private String minimumFaceSize;
	private String trainFacePath;
	private String outputMotionFramePath;
	private String rtsp;
	private String webcam;
	private String threads;
	private String resolution;
	private String batchTrainPath;
	private String sampleRate;
	private Integer streamingDelay;	
	
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public String getEnginePath() {
		return enginePath.replaceAll("%20", " ");
	}
	public void setEnginePath(String enginePath) {
		this.enginePath = enginePath;
	}
	public String getModelPath() {
		return modelPath.replaceAll("%20", " ");
	}
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}
	public String getOutputFramePath() {
		return outputFramePath.replaceAll("%20", " ");
	}
	public void setOutputFramePath(String outputFramePath) {
		this.outputFramePath = outputFramePath;
	}
	public String getInputJsonPath() {
		return inputJsonPath.replaceAll("%20", " ");
	}
	public void setInputJsonPath(String inputJsonPath) {
		this.inputJsonPath = inputJsonPath;
	}
	public String getBatchTrainSec() {
		return batchTrainSec;
	}
	public void setBatchTrainSec(String batchTrainSec) {
		this.batchTrainSec = batchTrainSec;
	}
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	public String getJsonListPath() {
		return jsonListPath.replaceAll("%20", " ");
	}
	public void setJsonListPath(String jsonListPath) {
		this.jsonListPath = jsonListPath;
	}	
	public String getOutputFacePath() {
		return outputFacePath.replaceAll("%20", " ");
	}
	public void setOutputFacePath(String outputFacePath) {
		this.outputFacePath = outputFacePath;
	}
	public String getSummaryResultPath() {
		return summaryResultPath.replaceAll("%20", " ");
	}
	public void setSummaryResultPath(String summaryResultPath) {
		this.summaryResultPath = summaryResultPath;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(Mode mode) {
		this.mode = mode.getValue();
	}	
	public String getMinimumFaceSize() {
		return minimumFaceSize;
	}
	public void setMinimumFaceSize(String minimumFaceSize) {
		this.minimumFaceSize = minimumFaceSize;
	}
	public String getTrainFacePath() {
		return trainFacePath.replaceAll("%20", " ");
	}
	public void setTrainFacePath(String trainFacePath) {
		this.trainFacePath = trainFacePath;
	}
	public String getOutputMotionFramePath() {
		return outputMotionFramePath.replaceAll("%20", " ");
	}
	public void setOutputMotionFramePath(String outputMotionFramePath) {
		this.outputMotionFramePath = outputMotionFramePath;
	}
	public String getRtsp() {
		return rtsp;
	}
	public void setRtsp(String rtsp) {
		this.rtsp = rtsp;
	}
	public String getWebcam() {
		return webcam;
	}
	public void setWebcam(String webcam) {
		this.webcam = webcam;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getThreads() {
		return threads;
	}
	public void setThreads(String threads) {
		this.threads = threads;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getBatchTrainPath() {
		return batchTrainPath;
	}
	public void setBatchTrainPath(String batchTrainPath) {
		this.batchTrainPath = batchTrainPath;
	}
	public String getSampleRate() {
		return sampleRate;
	}
	public void setSampleRate(String sampleRate) {
		this.sampleRate = sampleRate;
	}
	public Integer getStreamingDelay() {
		return streamingDelay;
	}
	public void setStreamingDelay(Integer streamingDelay) {
		this.streamingDelay = streamingDelay;
	}
}

