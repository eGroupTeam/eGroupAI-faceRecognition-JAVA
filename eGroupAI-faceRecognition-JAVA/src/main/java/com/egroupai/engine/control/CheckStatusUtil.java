package com.egroupai.engine.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.egroup.util.AttributeCheck;
import com.egroup.util.CmdUtil;
import com.egroup.util.TxtUtil;
import com.egroup.util.TxtUtil.Charsets;
import com.egroupai.engine.entity.ModelAppendInfo;
import com.egroupai.engine.entity.ModelAppendResult;
import com.egroupai.engine.entity.ModelInsertInfo;
import com.egroupai.engine.entity.ModelInsertResult;
import com.egroupai.engine.entity.ModelSwitchResult;
import com.egroupai.engine.entity.RecognizeFace.RECOGNIZEMODE_;
import com.egroupai.engine.entity.TrainInfo;
import com.egroupai.engine.entity.TrainResult;
import com.egroupai.engine.status.entity.StartupStatus;
import com.google.gson.Gson;

/**
 * @author 作者 eGroupAI Team
 * @date 2019年10月9日 上午9:37:08
 * @version
 * @description:
 */

public class CheckStatusUtil {
  private static Logger LOGGER = LoggerFactory.getLogger(CmdUtil.class);

  // 驗證階段與錯誤訊息
  public enum Check {
    CHECK0("初始化驗證"), CHECK1("金鑰驗證"), CHECK2("辨識引擎啟動參數"), CHECK3("辨識引擎運作程式"), CHECK4("硬體驗證"), CHECK5("儲存空間"), CHECK6("影像來源"), CHECK7(
        "辨識引擎演算法程式"), CHECK8("模型檔案"), CHECK9("模型檔案載入"), CHECK10("輸出結果資料夾");

    private String value;

    Check(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * 辨識引擎啟動驗證
   * 
   * @author daniel
   *
   * @param startupStatusPath :(enginePath+"/Status.Startup.eGroup)
   * @return 10項啟動檢查
   */
  public static List<StartupStatus> recognizeServerStartup4(String enginePath, String modelPath, String startupStatusPath, long waitTimeMs,
      RECOGNIZEMODE_ recognizeMode_) {
    // init func
    final AttributeCheck attributeCheck = new AttributeCheck();
    // init variable
    final List<StartupStatus> startupStatusList = new ArrayList<>();

    StartupStatus startupStatus = new StartupStatus();
    boolean checkFlag = true;

    // // 檢查初始化設定-路徑
    if (attributeCheck.stringsNotNull(enginePath, modelPath)) {
      // init variable
      final String licenseKeyPath = enginePath + "\\license.key";
      final String recognizeExePath;
      if (recognizeMode_.getValue().equals("liveness")) {
        recognizeExePath = enginePath + "\\LivenessDetectionServer.exe";
      } else {
        recognizeExePath = enginePath + "\\RecognizeFace.exe";
      }
      final String trainExePath = enginePath + "\\TrainFace.exe";
      final String modelAppendExePath = enginePath + "\\ModelAppend.exe";
      final File engineFolder = new File(enginePath);
      final File modelFolder = new File(modelPath);
      final File licenseKeyFile = new File(licenseKeyPath);
      final File recognizeExeFile = new File(recognizeExePath);
      final File trainExeFile = new File(trainExePath);
      final File modelAppendExeFile = new File(modelAppendExePath);

      // 檢查初始化設定-資料夾
      if (!(engineFolder.exists() && modelFolder.exists() && licenseKeyFile.exists() && licenseKeyFile.exists() && recognizeExeFile.exists()
          && trainExeFile.exists() && modelAppendExeFile.exists())) {
        checkFlag = false;
      }
    } else {
      checkFlag = false;
    }
    startupStatus.setCheckId(0);
    startupStatus.setCheckFlag(checkFlag);
    startupStatus.setCheckName(Check.CHECK0.getValue());
    startupStatusList.add(startupStatus);

    if (checkFlag && attributeCheck.stringsNotNull(startupStatusPath)) {
      // init func
      final TxtUtil txtUtil = new TxtUtil();
      // init variable
      long startTime = System.currentTimeMillis(), endTime2;
      final File startupStatusFile = new File(startupStatusPath);
      int checkCount = 0;
      try {
        while (true) {
          endTime2 = System.currentTimeMillis();
          if (((endTime2 - startTime) > waitTimeMs) || (startupStatusFile.exists() && startupStatusPath.length() > 0)) {
            break;
          }
          Thread.sleep(200);
        }
      } catch (InterruptedException e) {
        LOGGER.error(new Gson().toJson(e));
        Thread.currentThread().interrupt();
      }

      // 取得啟動的Log結果
      if (startupStatusFile.exists() && startupStatusPath.length() > 0) {
        final List<String> startupStatusLineList = txtUtil.read_lineList(startupStatusPath, Charsets.BIG5);
        if (attributeCheck.listNotEmpty(startupStatusLineList)) {
          // 文字檔資料路徑
          for (String startupStatusLine : startupStatusLineList) {
            // 如果目前驗證階段皆Pass，繼續驗證下一階段，如果Fail則中斷此迴圈
            if (checkFlag) {
              LOGGER.info("checkCount=" + checkCount + ",startupStatusLine=" + startupStatusLine);
              // init variable
              startupStatus = new StartupStatus();
              String startupStatusArray[] = startupStatusLine.split("\\t");
              switch (startupStatusArray.length) {
                case 3: // 階段處理成功後訊息陣列長度為3，內容範例 : Check0 Pass(or
                        // Fail) 2019-11-04 15:00:56
                  switch (checkCount) {
                    case 0: // 驗證金鑰
                      if (startupStatusArray[0].equals("Check0")) {
                        if (startupStatusArray[1].equals("Pass")) {
                          checkFlag = true;
                          checkCount++;
                        } else {
                          checkFlag = false;
                        }
                        startupStatus.setCheckId(1);
                        startupStatus.setCheckName(Check.CHECK1.getValue());
                        startupStatus.setCheckFlag(checkFlag);
                        startupStatusList.add(startupStatus);
                      }
                      break;
                    case 1:
                      if (startupStatusArray[0].equals("Check1")) {
                        if (startupStatusArray[1].equals("Pass")) {
                          checkFlag = true;
                          checkCount++;
                        } else {
                          checkFlag = false;
                        }
                        startupStatus.setCheckId(2);
                        startupStatus.setCheckName(Check.CHECK2.getValue());
                        startupStatus.setCheckFlag(checkFlag);
                        startupStatusList.add(startupStatus);
                      }
                      break;
                    case 2:
                      if (startupStatusArray[0].equals("Check2")) {
                        if (startupStatusArray[1].equals("Pass")) {
                          checkFlag = true;
                          checkCount++;
                        } else {
                          checkFlag = false;
                        }
                        startupStatus.setCheckId(3);
                        startupStatus.setCheckName(Check.CHECK3.getValue());
                        startupStatus.setCheckFlag(checkFlag);
                        startupStatusList.add(startupStatus);
                      }
                      break;
                    case 3:
                      if (startupStatusArray[0].equals("Check3")) {
                        if (startupStatusArray[1].equals("Pass")) {
                          checkFlag = true;
                          checkCount++;
                        } else {
                          checkFlag = false;
                        }
                        startupStatus.setCheckId(4);
                        startupStatus.setCheckName(Check.CHECK4.getValue());
                        startupStatus.setCheckFlag(checkFlag);
                        startupStatusList.add(startupStatus);
                      }
                      break;
                    case 4:
                      if (startupStatusArray[0].equals("Check4")) {
                        if (startupStatusArray[1].equals("Pass")) {
                          checkFlag = true;
                          checkCount++;
                        } else {
                          checkFlag = false;
                        }
                        startupStatus.setCheckId(5);
                        startupStatus.setCheckName(Check.CHECK5.getValue());
                        startupStatus.setCheckFlag(checkFlag);
                        startupStatusList.add(startupStatus);
                      }
                      break;
                    case 5:
                      if (startupStatusArray[0].equals("Check5")) {
                        if (startupStatusArray[1].equals("Pass")) {
                          checkFlag = true;
                          checkCount++;
                        } else {
                          checkFlag = false;
                        }
                        startupStatus.setCheckId(6);
                        startupStatus.setCheckName(Check.CHECK6.getValue());
                        startupStatus.setCheckFlag(checkFlag);
                        startupStatusList.add(startupStatus);
                      }
                      break;
                    case 6:
                      if (startupStatusArray[0].equals("Check6")) {
                        if (startupStatusArray[1].equals("Pass")) {
                          checkFlag = true;
                          checkCount++;
                        } else {
                          checkFlag = false;
                        }
                        startupStatus.setCheckId(7);
                        startupStatus.setCheckName(Check.CHECK7.getValue());
                        startupStatus.setCheckFlag(checkFlag);
                        startupStatusList.add(startupStatus);
                      }
                      break;
                    case 7:
                      if (startupStatusArray[0].equals("Check7")) {
                        if (startupStatusArray[1].equals("Pass")) {
                          checkFlag = true;
                          checkCount++;
                        } else {
                          checkFlag = false;
                        }
                        startupStatus.setCheckId(8);
                        startupStatus.setCheckName(Check.CHECK8.getValue());
                        startupStatus.setCheckFlag(checkFlag);
                        startupStatusList.add(startupStatus);
                      }
                      break;
                    case 8:
                      if (startupStatusArray[0].equals("Check8")) {
                        if (startupStatusArray[1].equals("Pass")) {
                          checkFlag = true;
                          checkCount++;
                        } else {
                          checkFlag = false;
                        }
                        startupStatus.setCheckId(9);
                        startupStatus.setCheckName(Check.CHECK9.getValue());
                        startupStatus.setCheckFlag(checkFlag);
                        startupStatusList.add(startupStatus);
                      }
                      break;
                    default:
                      break;
                  }
                  break;
                default:
                  break;
              }
            } else {
              break;
            }
          }
        }
      }
    }
    return startupStatusList;
  }

  /**
   * 辨識引擎啟動驗證
   * 
   * @author daniel
   *
   * @param startupStatusPath :(enginePath+"/Status.Startup.eGroup)
   * @return 11項啟動檢查
   */
  public List<StartupStatus> recognizeMainStartup4(String enginePath, String modelPath, String outputFacePath, String outputFramePath,
      String showTrainFacePath, String trainFacePath, String startupStatusPath, long waitTimeMs) {
    // init func
    final AttributeCheck attributeCheck = new AttributeCheck();
    // init variable
    final List<StartupStatus> startupStatusList = new ArrayList<>();
    StartupStatus startupStatus = new StartupStatus();
    boolean checkFlag = true;

    // // 檢查初始化設定-路徑
    if (attributeCheck.stringsNotNull(enginePath, modelPath, outputFacePath, outputFramePath, showTrainFacePath, trainFacePath)) {
      // init variable
      final File engineFolder = new File(enginePath);
      final File modelFolder = new File(modelPath);
      final File outputFaceFolder = new File(outputFacePath);
      final File outputFrameFolder = new File(outputFramePath);
      final File showTrainFaceFolder = new File(showTrainFacePath);
      final File trainFaceFolder = new File(trainFacePath);

      // 檢查初始化設定-資料夾
      if (!(engineFolder.exists() && modelFolder.exists() && outputFaceFolder.exists() && outputFrameFolder.exists() && showTrainFaceFolder.exists()
          && trainFaceFolder.exists())) {
        checkFlag = false;
      }
    } else {
      checkFlag = false;
    }
    startupStatus.setCheckId(0);
    startupStatus.setCheckFlag(checkFlag);
    startupStatus.setCheckName(Check.CHECK0.getValue());
    startupStatusList.add(startupStatus);

    if (checkFlag) {
      if (attributeCheck.stringsNotNull(startupStatusPath)) {
        // init func
        final TxtUtil txtUtil = new TxtUtil();
        // init variable
        final File startupStatusFile = new File(startupStatusPath);
        int checkCount = 0;

        try {
          while (true) {
            if (startupStatusFile.exists() && startupStatusFile.length() > 0) {
              break;
            }
            Thread.sleep(200);
          }
        } catch (InterruptedException e) {
          LOGGER.error(new Gson().toJson(e));
          Thread.currentThread().interrupt();
        }

        // 取得啟動的Log結果
        if (startupStatusFile.exists() && startupStatusFile.length() > 0) {
          final List<String> startupStatusLineList = txtUtil.read_lineList(startupStatusPath, Charsets.BIG5);
          if (attributeCheck.listNotEmpty(startupStatusLineList)) {
            // 文字檔資料路徑
            for (String startupStatusLine : startupStatusLineList) {
              // 如果目前驗證階段皆Pass，繼續驗證下一階段，如果Fail則中斷此迴圈
              if (checkFlag) {
                // init variable
                startupStatus = new StartupStatus();
                String startupStatusArray[] = startupStatusLine.split("\\t");
                switch (startupStatusArray.length) {
                  case 3: // 階段處理成功後訊息陣列長度為3，內容範例 : Check0 Pass(or
                          // Fail) 2019-11-04 15:00:56
                    switch (checkCount) {
                      case 0: // 驗證金鑰
                        if (startupStatusArray[0].equals("Check0")) {
                          if (startupStatusArray[1].equals("Pass")) {
                            checkFlag = true;
                            checkCount++;
                          } else {
                            checkFlag = false;
                          }
                          startupStatus.setCheckId(1);
                          startupStatus.setCheckName(Check.CHECK1.getValue());
                          startupStatus.setCheckFlag(checkFlag);
                          startupStatusList.add(startupStatus);
                        }
                        break;
                      case 1:
                        if (startupStatusArray[0].equals("Check1")) {
                          if (startupStatusArray[1].equals("Pass")) {
                            checkFlag = true;
                            checkCount++;
                          } else {
                            checkFlag = false;
                          }
                          startupStatus.setCheckId(2);
                          startupStatus.setCheckName(Check.CHECK2.getValue());
                          startupStatus.setCheckFlag(checkFlag);
                          startupStatusList.add(startupStatus);
                        }
                        break;
                      case 2:
                        if (startupStatusArray[0].equals("Check2")) {
                          if (startupStatusArray[1].equals("Pass")) {
                            checkFlag = true;
                            checkCount++;
                          } else {
                            checkFlag = false;
                          }
                          startupStatus.setCheckId(3);
                          startupStatus.setCheckName(Check.CHECK3.getValue());
                          startupStatus.setCheckFlag(checkFlag);
                          startupStatusList.add(startupStatus);
                        }
                        break;
                      case 3:
                        if (startupStatusArray[0].equals("Check3")) {
                          if (startupStatusArray[1].equals("Pass")) {
                            checkFlag = true;
                            checkCount++;
                          } else {
                            checkFlag = false;
                          }
                          startupStatus.setCheckId(4);
                          startupStatus.setCheckName(Check.CHECK4.getValue());
                          startupStatus.setCheckFlag(checkFlag);
                          startupStatusList.add(startupStatus);
                        }
                        break;
                      case 4:
                        if (startupStatusArray[0].equals("Check4")) {
                          if (startupStatusArray[1].equals("Pass")) {
                            checkFlag = true;
                            checkCount++;
                          } else {
                            checkFlag = false;
                          }
                          startupStatus.setCheckId(5);
                          startupStatus.setCheckName(Check.CHECK5.getValue());
                          startupStatus.setCheckFlag(checkFlag);
                          startupStatusList.add(startupStatus);
                        }
                        break;
                      case 5:
                        if (startupStatusArray[0].equals("Check5")) {
                          if (startupStatusArray[1].equals("Pass")) {
                            checkFlag = true;
                            checkCount++;
                          } else {
                            checkFlag = false;
                          }
                          startupStatus.setCheckId(6);
                          startupStatus.setCheckName(Check.CHECK6.getValue());
                          startupStatus.setCheckFlag(checkFlag);
                          startupStatusList.add(startupStatus);
                        }
                        break;
                      case 6:
                        if (startupStatusArray[0].equals("Check6")) {
                          if (startupStatusArray[1].equals("Pass")) {
                            checkFlag = true;
                            checkCount++;
                          } else {
                            checkFlag = false;
                          }
                          startupStatus.setCheckId(7);
                          startupStatus.setCheckName(Check.CHECK7.getValue());
                          startupStatus.setCheckFlag(checkFlag);
                          startupStatusList.add(startupStatus);
                        }
                        break;
                      case 7:
                        if (startupStatusArray[0].equals("Check7")) {
                          if (startupStatusArray[1].equals("Pass")) {
                            checkFlag = true;
                            checkCount++;
                          } else {
                            checkFlag = false;
                          }
                          startupStatus.setCheckId(8);
                          startupStatus.setCheckName(Check.CHECK8.getValue());
                          startupStatus.setCheckFlag(checkFlag);
                          startupStatusList.add(startupStatus);
                        }
                        break;
                      case 8:
                        if (startupStatusArray[0].equals("Check8")) {
                          if (startupStatusArray[1].equals("Pass")) {
                            checkFlag = true;
                            checkCount++;
                          } else {
                            checkFlag = false;
                          }
                          startupStatus.setCheckId(9);
                          startupStatus.setCheckName(Check.CHECK9.getValue());
                          startupStatus.setCheckFlag(checkFlag);
                          startupStatusList.add(startupStatus);
                        }
                        break;
                      case 9:
                        if (startupStatusArray[0].equals("Check9")) {
                          if (startupStatusArray[1].equals("Pass")) {
                            checkFlag = true;
                            checkCount++;
                          } else {
                            checkFlag = false;
                          }
                          startupStatus.setCheckId(10);
                          startupStatus.setCheckName(Check.CHECK10.getValue());
                          startupStatus.setCheckFlag(checkFlag);
                          startupStatusList.add(startupStatus);
                        }
                        break;
                      default:
                        break;
                    }
                    break;
                  default:
                    break;
                }
              } else {
                break;
              }
            }
          }
        }
      }
    }
    return startupStatusList;
  }

  /**
   * 訓練人臉驗證
   * 
   * @author daniel
   *
   * @param trainResultPath
   * @return
   */
  public TrainResult trainFace(String trainResultPath) {
    // init func
    final AttributeCheck attributeCheck = new AttributeCheck();
    // init variable
    TrainResult trainResult = new TrainResult();
    if (attributeCheck.stringsNotNull(trainResultPath)) {
      // init func
      final TxtUtil txtUtil = new TxtUtil();
      // init variable
      final File trainResultFile = new File(trainResultPath);

      try {
        while (true) {
          if (trainResultFile.exists() && trainResultFile.length() > 0) {
            break;
          }
          Thread.sleep(250);
          LOGGER.info("訓練進行中，請稍等......");
        }
      } catch (InterruptedException e) {
        LOGGER.error(new Gson().toJson(e));
        Thread.currentThread().interrupt();
      }

      // Get the trainFace result
      final List<String> trainResultLineList = txtUtil.read_lineList(trainResultPath, Charsets.BIG5);
      if (attributeCheck.listNotEmpty(trainResultLineList)) {
        // init variable
        List<TrainInfo> trainInfoList = new ArrayList<>();
        TrainInfo trainInfo = new TrainInfo();

        for (String trainResultLine : trainResultLineList) {
          String trainArray[] = trainResultLine.split("\\t");
          switch (trainArray.length) {
            case 9:
              if (trainArray[1].equals("Pass")) {
                trainResult.getPassFacePathList().add(trainArray[2]);
              } else {
                trainResult.getFailFacePathList().add(trainArray[2]);
              }
              trainInfo.setFacePath(trainArray[2]);
              trainInfo.setStatus(trainArray[1]);
              trainInfo.setTime(trainArray[0]);
              trainInfo.setPersonId(trainArray[3]);
              trainInfo.setFaceQuality(trainArray[4].toLowerCase().contains("pass") ? true : false);
              trainInfo.setFaceQualityBlurness(trainArray[5].toLowerCase().contains("pass") ? true : false);
              trainInfo.setFaceQualityLowLuminance(trainArray[6].toLowerCase().contains("pass") ? true : false);
              trainInfo.setFaceQualityHighLuminance(trainArray[7].toLowerCase().contains("pass") ? true : false);
              trainInfo.setFaceQualityHeadpose(trainArray[8].toLowerCase().contains("pass") ? true : false);
              trainInfoList.add(trainInfo);
              trainInfo = new TrainInfo();
              break;
            case 5:
              if (trainArray[1].equals("Fail")) {
                trainResult.getFailFacePathList().add(trainArray[2]);
              }
              trainInfo.setFacePath(trainArray[3]);
              trainInfo.setStatus(trainArray[2]);
              trainInfo.setTime(trainArray[0]);
              trainInfo.setPersonId(trainArray[4]);
              trainInfoList.add(trainInfo);
              trainInfo = new TrainInfo();
              break;
            case 2:
              if (trainArray[1].equals("faces were trained in the list file")) {
                trainResult.setFaceSize(Integer.valueOf(trainArray[0].replaceAll(":", "").toString()));
              } else if (trainArray[1].equals("list file size")) {
                trainResult.setFileSize(Integer.valueOf(trainArray[0].replaceAll(":", "").toString()));
              }
              break;
            case 1:
              if (trainArray[0].startsWith("Processing Time")) {
                trainResult.setProcessingTime(trainArray[0].toString().replace("Processing Time: ", "").replace(" sec. ", ""));
              } else {
                trainResult.setAvgPprocessingSpped(trainArray[0].toString().replace("AVG processing speed = ", "").replace(" image/sec", ""));
              }
              break;
            default:
              break;
          }
        }
        trainResult.setTrainInfoList(trainInfoList);
      } else {
        trainResult.setTrainResultFileExist(false);
      }
    }
    return trainResult;
  }

  /**
   * 模型合併驗證
   * 
   * @author daniel
   *
   * @param modelAppend_path : modelAppend.getEnginePath()+"/Status.ModelAppend.eGroup"
   * @return
   */
  public ModelAppendResult modelAppend(String modelAppendPath, long waitTimeMs) {
    // init func
    final AttributeCheck attributeCheck = new AttributeCheck();
    // init variable
    ModelAppendResult modelAppendResult = null;

    if (attributeCheck.stringsNotNull(modelAppendPath)) {
      // init func
      TxtUtil txtUtil = new TxtUtil();
      // init variable
      final File modelAppendFile = new File(modelAppendPath);
      int waitCount = 0;

      try {
        while (true) {
          if ((modelAppendFile.exists() && modelAppendFile.length() > 0) || waitCount == 5) {
            break;
          }
          waitCount++;
          Thread.sleep(waitTimeMs / 5);
        }
      } catch (InterruptedException e) {
        LOGGER.error(new Gson().toJson(e));
        Thread.currentThread().interrupt();
      }

      // Get the model append log
      if (modelAppendFile.exists() && modelAppendFile.length() > 0) {
        modelAppendResult = new ModelAppendResult();
        final List<String> modelAppendLineList = txtUtil.read_lineList(modelAppendPath, Charsets.UTF8);
        if (attributeCheck.listNotEmpty(modelAppendLineList)) {
          // init variable
          List<ModelAppendInfo> modelAppendInfoList = new ArrayList<>();
          ModelAppendInfo modelAppendInfo = new ModelAppendInfo();

          for (String modelAppendLine : modelAppendLineList) {
            // init variable
            String modelAppendArray[] = modelAppendLine.split("\\t");
            switch (modelAppendArray.length) {
              case 5:
                if (modelAppendArray[2].contains("DBSize")) {
                  modelAppendInfo.setDBSizeCheckStatus(modelAppendArray[1]);
                  modelAppendInfo.setDBSize(Integer.valueOf(modelAppendArray[2].substring(7, modelAppendArray[2].length()).trim()));
                  modelAppendInfo.setDBFaceDBPath(modelAppendArray[3]);
                  modelAppendInfo.setSucess(true);
                  modelAppendInfoList.add(modelAppendInfo);
                  modelAppendInfo = new ModelAppendInfo();
                }
                break;
              case 4:
                if (modelAppendArray[1].equals("Fail")) {
                  modelAppendInfo.setSucess(false);
                  modelAppendInfo.setErrorMessage(modelAppendArray[2]);
                  modelAppendInfoList.add(modelAppendInfo);
                  modelAppendInfo = new ModelAppendInfo();
                }
                if (modelAppendArray[2].equals("Parsingfile")) {
                  modelAppendResult.setModelListPath(modelAppendArray[3].replaceAll("file: ", ""));
                  modelAppendResult.setModelListCheckStatus(modelAppendArray[1]);
                } else if (modelAppendArray[2].equals("WorkingFolder")) {
                  modelAppendInfo.setWorkingFolderCheckStatus(modelAppendArray[1]);
                  modelAppendInfo.setWorkingFolderSize(Double.valueOf(
                      modelAppendArray[3].replace("Not enough space to working folder (", "").substring(1, (modelAppendArray[3].indexOf("GB")))));
                  modelAppendInfo.setWorkingFolderStatus(
                      modelAppendArray[3].substring((modelAppendArray[3].indexOf("GB") + 3), (modelAppendArray[3].lastIndexOf("("))).trim());
                } else if (modelAppendArray[2].equals("OutputFaceDB")) {
                  modelAppendInfo.setOutputFaceDBCheckStatus(modelAppendArray[1]);
                  modelAppendInfo.setOutputFaceDBSize(Double.valueOf(
                      modelAppendArray[3].replace("Not enough space to Output Binary file (", "").substring(1, (modelAppendArray[3].indexOf("GB")))));
                  modelAppendInfo.setOutputFaceDBStatus(
                      modelAppendArray[3].substring((modelAppendArray[3].indexOf("GB") + 3), (modelAppendArray[3].lastIndexOf("("))).trim());
                }
                break;
              case 1:
                if (modelAppendArray[0].startsWith("Total faces: ")) {
                  modelAppendResult.setTotalFaceCount(Integer.valueOf(modelAppendArray[0]
                      .substring(modelAppendArray[0].indexOf("Total faces: ") + 12, modelAppendArray[0].indexOf("in the appended new model"))
                      .trim()));
                } else {
                  modelAppendResult.setAppendPassCount(
                      Integer.valueOf(modelAppendArray[0].substring(0, modelAppendArray[0].indexOf("of models appended pass")).trim()));
                  modelAppendResult.setAppendFailCount(Integer.valueOf(modelAppendArray[0]
                      .substring(modelAppendArray[0].indexOf("/") + 1, modelAppendArray[0].indexOf("of models appended failed")).trim()));
                }
                break;
              default:
                break;
            }

          }
          // set the model append result info
          modelAppendResult.setModelAppendInfoList(modelAppendInfoList);
        }
      }
    }
    return modelAppendResult;
  }

  /**
   * 模型替換
   * 
   * @author daniel
   *
   * @param modelSwitchStatusPath
   * @param waitTimeMs
   * @return
   */
  public ModelSwitchResult modelSwitch(String modelSwitchStatusPath) {
    // init func
    final AttributeCheck attributeCheck = new AttributeCheck();
    // init variable
    ModelSwitchResult modelSwitchResult = null;

    if (attributeCheck.stringsNotNull(modelSwitchStatusPath)) {
      // init variable
      final File modelSwitchStatusFile = new File(modelSwitchStatusPath);
      // init func
      final TxtUtil txtUtil = new TxtUtil();
      // init variable
      boolean flag = true;
      int waitCount = 0;

      try {
        while (true) {
          if ((modelSwitchStatusFile.exists() && modelSwitchStatusFile.length() > 0) || waitCount == 10) {
            break;
          }
          waitCount++;
          Thread.sleep(200);
        }
      } catch (InterruptedException e) {
        LOGGER.error(new Gson().toJson(e));
        Thread.currentThread().interrupt();
      }

      if (modelSwitchStatusFile.exists() && modelSwitchStatusFile.length() > 0) {
        modelSwitchResult = new ModelSwitchResult();
        final List<String> modelSwitchLineList = txtUtil.read_lineList(modelSwitchStatusPath, Charsets.UTF8);
        String faceDBPath = "";
        if (attributeCheck.listNotEmpty(modelSwitchLineList)) {
          for (String modelSwitchLine : modelSwitchLineList) {
            String modelSwitchArray[] = modelSwitchLine.split("\\t");
            if (modelSwitchArray.length > 1) {
              if (modelSwitchArray[1].equals("Fail")) {
                faceDBPath = modelSwitchArray[3].replace("Reload FaceDB file ", "");
                modelSwitchResult.setFaceDB(faceDBPath.substring(faceDBPath.lastIndexOf("\\") + 1, faceDBPath.length()));
                flag = false;
              } else if (modelSwitchArray[1].equals("Pass") && modelSwitchArray.length == 4) {
                faceDBPath = modelSwitchArray[3].replace("Reload FaceDB file ", "");
                modelSwitchResult.setFaceDB(faceDBPath.substring(faceDBPath.lastIndexOf("\\") + 1, faceDBPath.length()));
              } else if (modelSwitchArray[1].equals("Report")) {
                if (modelSwitchArray[3].startsWith("Overall reload:")) {
                  modelSwitchResult.setFaceReload(modelSwitchArray[3]);
                } else {
                  modelSwitchResult.setReloadTime(modelSwitchArray[3]);
                }
              }
            }
          }
        } else {
          flag = false;
        }
        modelSwitchResult.setSuccess(flag);
      }
    }
    return modelSwitchResult;
  }

  /**
   * 模型熱傳
   * 
   * @author daniel
   *
   * @param modelInsertStatuspath (modelInsert.getEnginePath()+"\\Status.ModelInsert.eGroup")
   * @return
   */
  public ModelInsertResult modelInsert(String modelInsertStatusPath, long waitTimeMs) {
    // init func
    final AttributeCheck attributeCheck = new AttributeCheck();
    // init variable
    ModelInsertResult modelInsertResult = null;
    if (attributeCheck.stringsNotNull(modelInsertStatusPath)) {
      // init variable
      final File modelInsertStatusFile = new File(modelInsertStatusPath);
      final TxtUtil txtUtil = new TxtUtil();
      int waitCount = 0;

      try {
        while (true) {
          if ((modelInsertStatusFile.exists() && modelInsertStatusFile.length() > 0) || waitCount == 10) {
            break;
          }
          waitCount++;
          Thread.sleep(waitTimeMs / 5);
        }
      } catch (InterruptedException e) {
        LOGGER.error(new Gson().toJson(e));
        Thread.currentThread().interrupt();
      }

      if (modelInsertStatusFile.exists() && modelInsertStatusFile.length() > 0) {
        modelInsertResult = new ModelInsertResult();
        final List<String> modelInsertLineList = txtUtil.read_lineList(modelInsertStatusPath, Charsets.UTF8);
        if (attributeCheck.listNotEmpty(modelInsertLineList)) {
          // init variable
          final List<ModelInsertInfo> modelInsertInfoList = new ArrayList<>();
          ModelInsertInfo modelInsertInfo;
          String faceAndPeople[];
          String currentfaceAndPeople[];
          int face;
          int people;
          int currentDBFaceCout;
          int currentDBPeopleCount;

          for (String modelInsertLine : modelInsertLineList) {
            // init variable
            modelInsertInfo = new ModelInsertInfo();
            final String modelInsertArray[] = modelInsertLine.split("\\t");

            switch (modelInsertArray.length) {
              case 3:
                if (modelInsertArray[1].equals("Pass")) {
                  modelInsertInfoList.add(modelInsertInfo);
                  modelInsertResult.setSuccess(true);
                }
                break;
              case 5:
                faceAndPeople = modelInsertArray[3].replaceAll("Overall insert:", "").replace("faces/people", "").trim().split("/");
                currentfaceAndPeople =
                    modelInsertArray[4].replaceAll("CurrentDBFaceCout=", "").replace("CurrentDBPeopleCount=", "").trim().split(" ");
                face = Integer.valueOf(faceAndPeople[0]);
                people = Integer.valueOf(faceAndPeople[1]);
                currentDBFaceCout = Integer.valueOf(currentfaceAndPeople[0]);
                currentDBPeopleCount = Integer.valueOf(currentfaceAndPeople[1]);

                modelInsertInfo.setInsertFacesCount(face);
                modelInsertInfo.setInsertPeopleCount(people);
                modelInsertInfo.setCurrDBFaceCout(currentDBFaceCout);
                modelInsertInfo.setCurrDBPeopleCount(currentDBPeopleCount);
                modelInsertInfoList.add(modelInsertInfo);
                modelInsertResult.setSuccess(true);
                break;
              case 4:
                if (modelInsertArray[3].startsWith("Overall insert time: ")) {
                  modelInsertInfo.setInsertProcessTime(modelInsertArray[3]
                      .substring(modelInsertArray[3].indexOf("Overall insert time: "), modelInsertArray[3].indexOf(" sec. ")).trim());
                }
                modelInsertInfoList.add(modelInsertInfo);
                break;
              default:
                break;
            }

          }
          modelInsertResult.setModelInsertInfoList(modelInsertInfoList);
        }
      }
    }
    return modelInsertResult;
  }
}
