package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.egroup.util.AttributeCheck;
import com.egroup.util.CmdUtil;

/**
 * @author 作者 Daniel
 * @date 2018年8月12日 下午1:00:53
 * @version
 * @description:
 */
public class RecognizeFace {
  private static Logger LOGGER = LoggerFactory.getLogger(CmdUtil.class);

  public enum RECOGNIZEMODE_ {
    LIVENESS("liveness"), GENERAL("general");

    private String value;

    RECOGNIZEMODE_(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }


  private Double threshold;
  private String resolution;
  private String outputFramePath;
  private String outputFacePath;
  private String outputMotionFramePath;
  private String webcam;
  private String rtsp;
  private String videoPath;
  private String photoListPath;
  private Integer minimumFaceSize;
  private Integer threads;
  private String trainedFaceDBPath;
  private String jsonPath;
  private StringBuilder cli;
  private List<String> commandList;
  private List<String> commandList_server;
  private String disk;
  private String enginePath;
  private boolean isHideMainWindow = true;
  private boolean isHideThreadWindow = true;
  private boolean isTesting = false;
  private boolean isIterationSearch = false;
  private boolean isOnface = false;
  private Integer sampleRate;
  private String sectionId;
  private String mainResolution;
  // init program process
  private long responseTime;
  private boolean isOutputFrame;
  // init func
  private AttributeCheck attributeCheck;

  public Double getThreshold() {
    return threshold;
  }

  public void setThreshold(Double threshold) {
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

  public String getWebcam() {
    return webcam;
  }

  public void setWebcam(String webcam) {
    this.webcam = webcam;
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

  public String getTrainedFaceDBPath() {
    return trainedFaceDBPath;
  }

  public void setTrainedFaceDBPath(String trainedFaceDBPath) {
    this.trainedFaceDBPath = trainedFaceDBPath;
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
    if (attributeCheck == null) {
      attributeCheck = new AttributeCheck();
    }
    this.disk = enginePath.substring(0, 1);
    if (attributeCheck.stringsNotNull(enginePath, disk, trainedFaceDBPath, jsonPath)) {
      String inputSource = " --cam " + webcam;
      if (attributeCheck.stringsNotNull(rtsp)) {
        inputSource = " --rtsp " + rtsp;
      } else if (attributeCheck.stringsNotNull(videoPath)) {
        inputSource = " --video " + videoPath;
      } else if (attributeCheck.stringsNotNull(photoListPath)) {
        inputSource = " --photo-list " + photoListPath;
      }
      cli = new StringBuilder("cd " + enginePath + " && " + disk + ": && RecognizeFace " + "--threshold " + threshold + " "
          + (isHideMainWindow == false ? " --show-main-window " : "") + (isHideThreadWindow == false ? " --show-thread-window " : "")
          + (attributeCheck.stringsNotNull(resolution) ? " --resolution " + resolution + " " : "--resolution 720p ")
          + (isOutputFrame == false || !attributeCheck.stringsNotNull(outputFramePath) ? "" : " --output-frame \"" + outputFramePath + "\" ")
          + (!attributeCheck.stringsNotNull(outputFacePath) ? "" : "--output-face \"" + outputFacePath + "\" ") + inputSource + " "
          + (minimumFaceSize != null ? "--minimum-face-size " + minimumFaceSize + " " : "")
          + (attributeCheck.stringsNotNull(mainResolution) ? "--output-window-resolution " + mainResolution + " " : "")
          + (threads != null ? "--threads " + threads + " " : "--threads 1 ")
          + (sampleRate != null ? "--sample-rate " + sampleRate + " " : "--sample-rate 5 ") + (isTesting == true ? "--no-imageqa " : "")
          + (isIterationSearch == true ? "--enable-iteration-search " : "") + (isOnface == true ? "--one-face " : "") + " \"" + trainedFaceDBPath
          + "\" \"" + jsonPath + "\"");
    } else {
      cli = null;
    }
    LOGGER.info("RecognizeFace cli : " + cli);
  }

  public void getStopCli(RECOGNIZEMODE_ recognizeMode_) {
    if (attributeCheck == null) {
      attributeCheck = new AttributeCheck();
    }
    this.disk = enginePath.substring(0, 1);
    if (attributeCheck.stringsNotNull(enginePath, disk)) {
      if (!recognizeMode_.getValue().equalsIgnoreCase("liveness")) {
        cli = new StringBuilder("cd " + enginePath + " && " + disk + ": && StopRecognize.bat");
      } else {
        cli = new StringBuilder("cd " + enginePath + " && " + disk + ": && StopLiveness.bat");
      }
    } else {
      cli = null;
    }
    LOGGER.info("Close RecognizeFace cli : " + cli);
  }

  public void generateCli_server(RECOGNIZEMODE_ recognizemode_) {
    if (attributeCheck == null) {
      attributeCheck = new AttributeCheck();
    }
    this.disk = enginePath.substring(0, 1);
    if (attributeCheck.stringsNotNull(enginePath, disk, trainedFaceDBPath)) {
      if (!recognizemode_.getValue().equalsIgnoreCase("liveness")) {
        cli =
            new StringBuilder("cd " + enginePath + " && " + disk + ": && RecognizeFace " + (threshold != null ? "--threshold " + threshold + " " : "")
                + (resolution != null ? "--resolution " + resolution + " " : "") + (isIterationSearch == true ? "--enable-iteration-search " : "")
                + (isOnface == true ? "--one-face " : "") + " \"" + trainedFaceDBPath + "\" ");
      } else {
        cli = new StringBuilder(
            "cd " + enginePath + " && " + disk + ": && LivenessDetectionServer " + (resolution != null ? "--resolution " + resolution + " " : "")
                + (threshold != null ? "--threshold " + threshold + " " : "") + (isIterationSearch == true ? "--enable-iteration-search " : "")
                + (isOnface == true ? "--one-face " : "") + " \"" + trainedFaceDBPath + "\" ");
      }
    } else {
      cli = null;
    }
    LOGGER.info("RecognizeFace server cli : " + cli);
  }

  // public void generateCli_liveness_server() {
  // if(attributeCheck==null){
  // attributeCheck = new AttributeCheck();
  // }
  // this.disk = enginePath.substring(0, 1);
  // if (attributeCheck.stringsNotNull(enginePath, disk,trainedFaceDBPath)) {
  // cli = new StringBuilder("cd " + enginePath + " && " + disk + ": && LivenessDetectionServer "
  // + (resolution != null ? "--resolution " + resolution + " " : "")
  // + (threshold != null ? "--threshold " + threshold + " " : "")
  // + (isIterationSearch==true?"--enable-iteration-search ":"")
  // + (isOnface==true?"--one-face ":"")
  // + " \""+trainedFaceDBPath+"\" ");
  // } else {
  // cli = null;
  // }
  // logUtil.setLog("RecognizeFace liveness servercli : "+cli, LogType.INFO);
  // }

  public List<String> getCommandList() {
    if (attributeCheck == null) {
      attributeCheck = new AttributeCheck();
    }
    if (attributeCheck.stringsNotNull(cli.toString())) {
      commandList = new ArrayList<String>();
      commandList.add("cmd");
      commandList.add("/C");
      commandList.add(disk + ": && " + cli.toString().replace("/", "/"));
    }
    return commandList;
  }

  public void setCommandList(List<String> commandList) {
    this.commandList = commandList;
  }

  public List<String> getCommandList_server() {
    if (attributeCheck == null) {
      attributeCheck = new AttributeCheck();
    }
    if (attributeCheck.stringsNotNull(cli.toString())) {
      commandList_server = new ArrayList<String>();
      commandList_server.add("cmd");
      commandList_server.add("/C");
      commandList_server.add(disk + ": && " + cli.toString().replace("/", "/"));
    }
    return commandList_server;
  }

  public void setCommandList_server(List<String> commandList_server) {
    this.commandList_server = commandList_server;
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

  public String getMainResolution() {
    return mainResolution;
  }

  public void setMainResolution(String mainResolution) {
    this.mainResolution = mainResolution;
  }

  public boolean isOutputFrame() {
    return isOutputFrame;
  }

  public void setOutputFrame(boolean isOutputFrame) {
    this.isOutputFrame = isOutputFrame;
  }

  public boolean isTesting() {
    return isTesting;
  }

  public void setTesting(boolean isTesting) {
    this.isTesting = isTesting;
  }

  public boolean isOnface() {
    return isOnface;
  }

  public void setOnface(boolean isOnface) {
    this.isOnface = isOnface;
  }

  public boolean isIterationSearch() {
    return isIterationSearch;
  }

  public void setIterationSearch(boolean isIterationSearch) {
    this.isIterationSearch = isIterationSearch;
  }


}
