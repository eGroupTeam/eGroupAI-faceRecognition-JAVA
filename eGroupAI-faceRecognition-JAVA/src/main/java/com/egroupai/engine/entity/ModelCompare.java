package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.egroup.util.AttributeCheck;
import com.egroup.util.CmdUtil;

/**
 * @author 作者 daniel
 * @date 2019年12月24日 下午3:35:12
 * @version
 * @description:
 */

public class ModelCompare {
  private static Logger LOGGER = LoggerFactory.getLogger(CmdUtil.class);

  private Double threshold;
  private String modelFaceDBPathA;
  private String modelFaceDBPathB;
  private String outputCsvPath;
  private StringBuilder cli;
  private List<String> commandList;
  private String disk;
  private String enginePath;
  // init func
  private AttributeCheck attributeCheck;

  public Double getThreshold() {
    return threshold;
  }

  public void setThreshold(Double threshold) {
    this.threshold = threshold;
  }

  public String getModelFaceDBPathA() {
    return modelFaceDBPathA;
  }

  public void setModelFaceDBPathA(String modelFaceDBPathA) {
    this.modelFaceDBPathA = modelFaceDBPathA;
  }

  public String getModelFaceDBPathB() {
    return modelFaceDBPathB;
  }

  public void setModelFaceDBPathB(String modelFaceDBPathB) {
    this.modelFaceDBPathB = modelFaceDBPathB;
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

  public String getOutputCsvPath() {
    return outputCsvPath;
  }

  public void setOutputCsvPath(String outputCsvPath) {
    this.outputCsvPath = outputCsvPath;
  }

  public void generateCli() {
    if (attributeCheck == null) {
      attributeCheck = new AttributeCheck();
    }
    this.disk = enginePath.substring(0, 1);
    if (attributeCheck.stringsNotNull(enginePath, disk)) {
      cli = new StringBuilder("cd " + enginePath + " && " + disk + ": && ModelCompare " + threshold + " " + " \"" + modelFaceDBPathA + "\" \""
          + modelFaceDBPathB + "\" \"" + outputCsvPath + "\"");
    } else {
      cli = null;
    }
    LOGGER.info("RecognizeFace cli : " + cli);
  }
}
