package com.egroupai.engine.tutorial;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.egroup.util.FolderUtil;
import com.egroupai.engine.control.CreateEngineFileUtil;
import com.egroupai.engine.control.EngineUtil;
import com.egroupai.engine.control.GetResultUtil;
import com.egroupai.engine.entity.Face;
import com.egroupai.engine.entity.ModelAppend;
import com.egroupai.engine.entity.ModelAppendResult;
import com.egroupai.engine.entity.ModelInsert;
import com.egroupai.engine.entity.ModelInsertResult;
import com.egroupai.engine.entity.RecognizeFace;
import com.egroupai.engine.entity.TrainFace;
import com.egroupai.engine.entity.TrainResult;
import com.google.gson.Gson;

/**
 * @author eGroupAI Team
 * @date 2020年11月23日 下午2:52:03
 * @version
 * @description: eGroupAI document : https://reurl.cc/GrRo5x
 */
public class QuickStart {
  private static Logger LOGGER = LoggerFactory.getLogger(QuickStart.class);
  // init func
  final static EngineUtil engineUtil = new EngineUtil();
  final static CreateEngineFileUtil createEngineFileUtil = new CreateEngineFileUtil();
  final static FolderUtil folderUtil = new FolderUtil();
  final static GetResultUtil getResultUtil = new GetResultUtil();
  // init variable
  final static boolean logDeleteFlag = false;
  // init path
  final static String quickStartPath = "C:\\QuickStart";
  final static StringBuilder enginePath = new StringBuilder(quickStartPath + "\\eGroupAI_FaceEngine_CPU_Windows_V4.2.2");
  final static StringBuilder faceDBPath = new StringBuilder(enginePath + "\\eGroup");
  final static StringBuilder resourcesPath = new StringBuilder(enginePath + "\\resources");
  final static StringBuilder trainResultLogPath = new StringBuilder(enginePath + "\\Status.TrainResultCPU.eGroup");
  final static StringBuilder trainListPath = new StringBuilder(resourcesPath + "\\list.txt");
  final static StringBuilder modelAppendListPath = new StringBuilder(faceDBPath + "\\modelList.egroup.List");
  final static StringBuilder outputfacePath = new StringBuilder(enginePath + "\\outputFace");
  final static StringBuilder outputframepath = new StringBuilder(enginePath + "\\outputFrame");
  final static StringBuilder jsonFolderPath = new StringBuilder(enginePath + "\\json");
  final static StringBuilder catchJsonName = new StringBuilder("output.cache.egroup");
  // final static StringBuilder allJsonName = new StringBuilder("output." + LocalDate.now() + ".egroup");
  final static StringBuilder modelInserFilePath = new StringBuilder(enginePath + "\\Singal_For_Model_Insert.txt");
  final static StringBuilder videoPath = new StringBuilder("resources\\example.mp4");
  final static String resolution = "720p";
  // init file
  final static File outputfaceFile = new File(outputfacePath.toString());
  final static File outputframeFile = new File(outputframepath.toString());
  final static File jsonFolderFile = new File(jsonFolderPath.toString());
  // init person path
  final static StringBuilder jerryFaceDBPath = new StringBuilder(faceDBPath + "\\jerry");
  final static StringBuilder leonardFaceDBPath = new StringBuilder(faceDBPath + "\\leonard");
  final static StringBuilder danielFaceDBPath = new StringBuilder(faceDBPath + "\\daniel");
  // init person image
  final static StringBuilder jerryFaceImageFolderPath = new StringBuilder(resourcesPath + "\\jerry");
  final static StringBuilder danielFaceImageFolderPath = new StringBuilder(resourcesPath + "\\daniel");
  final static StringBuilder lenardFaceImageFolderPath = new StringBuilder(resourcesPath + "\\leonard");
  final static File jerryFaceImageFolder = new File(jerryFaceImageFolderPath.toString());
  final static File danielFaceImageFolder = new File(danielFaceImageFolderPath.toString());
  final static File leonardFaceImageFolder = new File(lenardFaceImageFolderPath.toString());

  public static void main(String args[]) {
    // ================================================== Step1 : Training========================================================= //
    // Example: Input jerry's Face, Create jerry's Face Model.
    // Document: https: // reurl.cc/Y6r94a
    training("jerry");

    // ==================================================Step2 : Recognition======================================================== //
    // Example: Input jerry Face, Recognized with jerry’s Face Model and get Result（JSON）.
    // Document: https://reurl.cc/Y6r9Ya
    Thread recognitionThread = new Thread(new Runnable() {
      @Override
      public void run() {
        recognition(jerryFaceDBPath + ".faceDB");
      }
    });
    recognitionThread.start();

    // =================================================Step3 : ModelInsert========================================================= //
    // Document: https://reurl.cc/EzMpQm
    // 1.Example: Input leonard Face(Untrained Face) and Trained immediately
    training("leonard");
    // 2.then Insert leonard Face Model to Face Model and get Recognition Resultat the same time
    modelInsert("leonard");

    // ================================================wait recognition thread done================================================== //

    //
    synchronized (recognitionThread) {
      try {
        recognitionThread.wait();
      } catch (InterruptedException e) {
        LOGGER.error(new Gson().toJson(e));
      }
    }

    // ==================================================Step4 : Model Append======================================================== //
    // Example: Append daniel and leonard Face Model into all face DB.
    // Document: https://reurl.cc/EzMpQm
    // 1.Execute train face instructions (see Training Procedure for details)
    training("daniel");
    // 2.Execute Model Append instructions (see Model Append Procedure for details)
    modelAppend();
    // 3.Recognition - Example: Recognized with all face DB and get Result（JSON）.
    recognitionThread = new Thread(new Runnable() {
      @Override
      public void run() {
        recognition(faceDBPath + ".faceDB");
      }
    });
    recognitionThread.start();
  }


  /**
   * Input Face, Create Face Model. Document : https: // reurl.cc/Y6r94a
   * 
   * @author eGroupAI Team
   *
   * @param name
   */
  public static void training(String name) {
    // https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/getting-started#0054007200610069006e0069006e0067
    // init variable
    final List<TrainFace> trainFaceList = new ArrayList<>();
    // Set training variable
    final TrainFace trainFace = new TrainFace();
    trainFace.setTrainListPath(trainListPath.toString());
    trainFace.setModelPath(getFaceDBPath(name));
    trainFace.setEnginePath(enginePath.toString());
    trainFace.setPersonId(name);
    // Get image in folder and set training image
    trainFace.setImagePathList(getFaceImageFolder(name));
    // Add to trainFace list
    trainFaceList.add(trainFace);
    // Create train face list
    createEngineFileUtil.createTrainFaceTxt(trainListPath.toString(), trainFaceList);
    // Start training and get result
    final TrainResult trainResult = engineUtil.trainFace(trainFace, logDeleteFlag);
    LOGGER.info("trainResult=" + new Gson().toJson(trainResult));
  }

  /**
   * Example: Recognized with Face Model and get Result（JSON）. Document: https://reurl.cc/Y6r9Ya
   * 
   * @author eGroupAI Team
   *
   * @param usedFaceDB
   */
  public static void recognition(String usedFaceDB) {
    // Create folder
    if (!outputfaceFile.exists()) {
      outputfaceFile.mkdirs();
    }
    if (!outputframeFile.exists()) {
      outputframeFile.mkdirs();
    }
    if (!jsonFolderFile.exists()) {
      jsonFolderFile.mkdirs();
    }

    // Set recognition variable
    final RecognizeFace recognizeFace = new RecognizeFace();
    recognizeFace.setEnginePath(enginePath.toString());
    recognizeFace.setTrainedFaceDBPath(usedFaceDB);
    recognizeFace.setOutputFacePath(outputfacePath.toString());
    recognizeFace.setOutputFramePath(outputframepath.toString());
    recognizeFace.setJsonPath(jsonFolderPath + "\\output");
    recognizeFace.setHideMainWindow(false);
    recognizeFace.setOutputFrame(true);
    recognizeFace.setOutputFace(true);
    recognizeFace.setOnface(true);
    recognizeFace.setThreshold(0.6);
    recognizeFace.setResolution(resolution);
    recognizeFace.setVideoPath(videoPath.toString());
    recognizeFace.setMinimumFaceSize(100);
    recognizeFace.setThreads(2);
    // Start recognition
    engineUtil.recognizeFace(recognizeFace);
    // Get all result after recognize is done
    List<Face> faceList = getResultUtil.cacheResult(jsonFolderPath.toString(), catchJsonName.toString());
    LOGGER.info("Faces : " + new Gson().toJson(faceList));
  }

  /**
   * Example: Input Face(Untrained Face) and Trained immediately, then Insert Face Model to Recognition and get Result at the same time. Document:
   * https://reurl.cc/EzMpQm
   * 
   * @author eGroupAI Team
   *
   * @param name
   */
  public static void modelInsert(String name) {
    // Set insert facedb list
    List<String> faceDBList = new ArrayList<>();
    faceDBList.add(getFaceDBPath(name) + ".faceDB");
    // Set model insert variable
    ModelInsert modelInsert = new ModelInsert();
    modelInsert.setEnginePath(enginePath.toString());
    modelInsert.setFaceDBList(faceDBList);
    modelInsert.setListPath(modelInserFilePath.toString());
    ModelInsertResult modelInsertResult = engineUtil.modelInsert(modelInsert, false, 3000);
    LOGGER.info("modelInsertResult : " + new Gson().toJson(modelInsertResult));
  }

  /**
   * Example: Append person Face Model into all face DB. Document: https://reurl.cc/EzMpQm
   * 
   * @author eGroupAI Team
   *
   */
  public static void modelAppend() {
    // https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/getting-started#0054007200610069006e0069006e0067
    // Set append faceDB list
    List<String> faceDBList = new ArrayList<>();
    faceDBList.add(jerryFaceDBPath + ".faceDB");
    faceDBList.add(leonardFaceDBPath + ".faceDB");
    faceDBList.add(danielFaceDBPath + ".faceDB");

    // Model Append
    final ModelAppend modelAppend = new ModelAppend();
    modelAppend.setTrainedFaceDBPath(faceDBPath + ".faceDB");
    modelAppend.setFaceDBList(faceDBList);
    modelAppend.setListPath(modelAppendListPath.toString());
    modelAppend.setEnginePath(enginePath.toString());
    final ModelAppendResult modelAppendResult = engineUtil.modelAppend(modelAppend, false, 2500);
    LOGGER.info("modelInsertResult : " + new Gson().toJson(modelAppendResult));
  }

  /**
   * 
   * Get face face DB path by person name
   * 
   * @author eGroupAI Team
   *
   * @param name
   * @return
   */
  public static String getFaceDBPath(String name) {
    String faceDB = faceDBPath.toString();
    switch (name.toLowerCase()) {
      case "daniel":
        faceDB = danielFaceDBPath.toString();
        break;
      case "leonard":
        faceDB = leonardFaceDBPath.toString();
        break;
      case "jerry":
        faceDB = jerryFaceDBPath.toString();
        break;
      default:
        break;
    }
    return faceDB;
  }

  /**
   * Get face image path in folder by person name
   * 
   * @author eGroupAITeam
   *
   * @param name
   * @return
   */
  public static List<String> getFaceImageFolder(String name) {
    List<String> imagePathList = new ArrayList<>();
    switch (name.toLowerCase()) {
      case "daniel":
        imagePathList = folderUtil.listPath(danielFaceImageFolder);
        break;
      case "leonard":
        imagePathList = folderUtil.listPath(leonardFaceImageFolder);
        break;
      case "jerry":
        imagePathList = folderUtil.listPath(jerryFaceImageFolder);
        break;
      default:
        break;
    }
    return imagePathList;
  }
}
