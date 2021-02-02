package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.egroup.util.AttributeCheck;
import com.egroup.util.CmdUtil;

/**
 * @author 作者 eGroupAI Team
 * @date 2018年8月12日 下午10:47:59
 * @version
 * @description:
 */
public class ModelAppend {
  private static Logger LOGGER = LoggerFactory.getLogger(CmdUtil.class);

  private String listPath;
  private String trainedFaceDBPath;
  private List<String> faceDBList;
  private StringBuilder cli;
  private List<String> commandList = new ArrayList<String>();
  private String disk;
  // program control
  private String enginePath;
  private HashSet<String> faceDBHashset = new HashSet<>();
  // init func
  private AttributeCheck attributeCheck;

  public String getListPath() {
    return listPath;
  }

  public void setListPath(String listPath) {
    this.listPath = listPath;
  }

  public StringBuilder getCli() {
    return cli;
  }

  public void setCli(StringBuilder cli) {
    this.cli = cli;
  }

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

  public String getDisk() {
    return disk;
  }

  public void setDisk(String disk) {
    this.disk = disk;
  }

  public void generateCli(String enginePath) {
    if (attributeCheck == null) {
      attributeCheck = new AttributeCheck();
    }
    this.disk = enginePath.substring(0, 1);
    if (attributeCheck.stringsNotNull(enginePath, disk, listPath, trainedFaceDBPath)) {
      cli = new StringBuilder("cd " + enginePath + " && " + disk + ": && ModelAppend \"" + listPath + "\" \"" + trainedFaceDBPath + "\"");

    } else {
      cli = null;
    }

    LOGGER.info("cli=" + cli);
  }

  public String getTrainedFaceDBPath() {
    return trainedFaceDBPath;
  }

  public void setTrainedFaceDBPath(String trainedFaceDBPath) {
    this.trainedFaceDBPath = trainedFaceDBPath;
  }

  public List<String> getFaceDBList() {
    if (attributeCheck == null) {
      attributeCheck = new AttributeCheck();
    }
    if (!attributeCheck.listNotEmpty(faceDBList)) {
      faceDBList = new ArrayList<>();
    }
    return faceDBList;
  }

  public void setFaceDBList(List<String> faceDBList) {
    this.faceDBList = faceDBList;
  }

  public HashSet<String> getFaceDBHashset() {
    return faceDBHashset;
  }

  public void setFaceDBHashset(HashSet<String> faceDBHashset) {
    this.faceDBHashset = faceDBHashset;
    this.faceDBList = new ArrayList<String>(faceDBHashset);
  }

  public String getEnginePath() {
    return enginePath;
  }

  public void setEnginePath(String enginePath) {
    this.enginePath = enginePath;
  }
}
