package com.egroupai.engine.control;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.egroup.logback.entity.LogReport;
import com.egroup.logback.util.LogUtil;
import com.egroup.logback.util.LogUtil.LogType;
import com.egroup.util.AttributeCheck;
import com.egroup.util.CopyUtil;
import com.egroup.util.TxtUtil;
import com.egroupai.engine.entity.Face;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author 作者 eGroupAI Team
 * @date 2018年8月16日 上午8:35:11
 * @version
 * @description:
 */
public class GetResultUtil {
  private static Logger LOGGER = LoggerFactory.getLogger(GetResultUtil.class);

  /**
   * Get Retrieve result json
   * 
   * @author eGroupAI Team
   *
   * @param jsonPath
   * @param startIndex
   * @return
   */
  public List<Face> allResult(String jsonFolderPath, String jsonName, int startIndex, boolean isDynamicJson, long waiteJsonMs) {
    // init func
    final Gson gson = new Gson();
    final CopyUtil copyUtil = new CopyUtil();
    final AttributeCheck attributeCheck = new AttributeCheck();

    // init variable
    final Type faceListType = new TypeToken<ArrayList<Face>>() {}.getType();
    List<Face> faceList = new ArrayList<Face>();

    // Get retrieve result
    final File sourceJson = new File(jsonFolderPath.toString() + "/" + jsonName + ".json");
    final StringBuilder jsonFileName = new StringBuilder(jsonFolderPath + "/" + jsonName + "_copy.json");
    final File destJson = new File(jsonFileName.toString());

    try {
      Thread.sleep(waiteJsonMs);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (sourceJson.exists() && sourceJson.length() > 0) {
      // init func
      final TxtUtil txtUtil = new TxtUtil();
      final LogUtil logUtil = new LogUtil();
      // init variable
      String jsonContent;
      jsonContent = txtUtil.read_content(jsonFileName.toString());

      try {
        copyUtil.copyFile(sourceJson, destJson);
      } catch (IOException e) {
        logUtil.setLog(gson.toJson(e.getMessage()), LogType.ERROR);
      }

      // If has data
      if (attributeCheck.stringsNotNull(jsonContent)) {
        // Get last one object
        if (isDynamicJson) {
          int endIndex = jsonContent.lastIndexOf("}\n\t,");
          if (endIndex == -1) {
            endIndex = jsonContent.lastIndexOf("}\n]");
          }
          String json;
          // Reorganization json
          if (endIndex != -1 && startIndex != endIndex && startIndex < endIndex) {
            if (startIndex > 0) {
              json = "[" + jsonContent.toString().substring(startIndex + 2, endIndex) + "}]";
            } else {
              json = jsonContent.toString().substring(startIndex, endIndex) + "}]";
            }
            if (attributeCheck.stringsNotNull(json)) {
              faceList = gson.fromJson(json, faceListType);
              faceList.get(faceList.size() - 1).setEndIndex(endIndex + 2);
            }
          }
        } else {
          // If has data
          if (attributeCheck.stringsNotNull(jsonContent.toString())) {
            faceList = gson.fromJson(jsonContent.toString(), faceListType);
          }
        }
      }
    }

    return faceList;
  }

  /**
   * Get Retrieve result json
   * 
   * @author eGroupAI Team
   *
   * @param jsonPath
   * @param startIndex
   * @return
   */
  public List<Face> cacheResult(String jsonFolderPath, String jsonName) {
    // init func
    final AttributeCheck attributeCheck = new AttributeCheck();
    // init variable
    List<Face> faceList = null;

    if (attributeCheck.stringsNotNull(jsonFolderPath, jsonName)) {
      // init func
      final Gson gson = new Gson();
      final CopyUtil copyUtil = new CopyUtil();
      final LogUtil logUtil = new LogUtil();

      // init variable
      final Type faceListType = new TypeToken<ArrayList<Face>>() {}.getType();

      // Get retrieve result
      final File sourceJson = new File(jsonFolderPath + "\\" + jsonName + ".json");
      final StringBuilder jsonFileName = new StringBuilder(jsonFolderPath + "\\" + jsonName + "_copy.json");
      final File destJson = new File(jsonFileName.toString());
      if (sourceJson.exists() && sourceJson.length() != destJson.length()) {
        // init func
        final TxtUtil txtUtil = new TxtUtil();
        // init variable
        String jsonContent;
        try {
          copyUtil.copyFile(sourceJson, destJson);
        } catch (IOException e) {
          logUtil.setLog(gson.toJson(e.getMessage()), LogType.ERROR);
        }
        jsonContent = txtUtil.read_content(jsonFileName.toString());

        // If has data
        if (attributeCheck.stringsNotNull(jsonContent)) {
          // Get last one object
          int endIndex = jsonContent.lastIndexOf("}\n]");
          if (endIndex == -1) {
            endIndex = jsonContent.lastIndexOf("}\n\t,");
          }
          if (endIndex > 0) {
            final String json = jsonContent.substring(0, endIndex) + "}]";
            faceList = gson.fromJson(json, faceListType);
          }
        }
      }
    }
    return faceList;
  }

  /**
   * Get Retrieve result json
   * 
   * @author eGroupAI Team
   *
   * @param jsonPath
   * @param startIndex
   * @return
   */
  public List<Face> serverPhotoResult(String jsonPath, String jsonName, Boolean deleteJson) {
    // init func
    final Gson gson = new Gson();
    final LogUtil logUtil = new LogUtil();

    // init variable
    final Type faceListType = new TypeToken<ArrayList<Face>>() {}.getType();
    List<Face> faceList = new ArrayList<Face>();

    // Get retrieve result
    final String sourceJson = jsonPath.toString() + "\\" + jsonName + ".json";
    final File sourceJson_file = new File(sourceJson);
    if (sourceJson_file.exists()) {
      // init func
      final TxtUtil txtUtil = new TxtUtil();
      final AttributeCheck attributeCheck = new AttributeCheck();
      // init variable
      final Path sourceJson_filePath = Paths.get(sourceJson);
      final StringBuilder jsonFileName = new StringBuilder(jsonPath + "\\" + jsonName + ".json");

      String jsonContent = txtUtil.read_content(jsonFileName.toString());

      if (attributeCheck.stringsNotNull(jsonContent)) {
        faceList = gson.fromJson(jsonContent, faceListType);
      }
      if (deleteJson) {
        try {
          Files.delete(sourceJson_filePath);
        } catch (IOException e) {
          final LogReport logReport = new LogReport();
          logReport.setFunction("photoResult");
          logReport.setMessage(e.getMessage());
          logReport.setObject(sourceJson_filePath);
          logUtil.setLog(logReport, LogType.ERROR);
        }
      }
    }
    return faceList;
  }

  // public static void main(String args[]){
  // String jsonPath = "E:\\Desktop\\AP_Engine\\product_facego\\eGroupAI_FaceEngine_CPU_V4.2.0";
  // String jsonName = "output.cache.egroup";
  // List<Face> faceList = cacheResult(jsonPath, jsonName);
  // System.out.println("faceList="+new Gson().toJson(faceList));
  //
  // jsonPath = "E:\\Desktop\\AP_Engine\\product_api_server\\engine_liveness_server";
  // jsonName = "8a0a55a969e24484b60c295e55e3833f";
  // faceList = serverPhotoResult(jsonPath, jsonName,false);
  // System.out.println("faceList="+new Gson().toJson(faceList));

  // String jsonName = "output.2020-04-16.egroup";
  // allResult(jsonPath, jsonName, 0, true);
  // List<Face> faceList = allResult(jsonPath, jsonName, 0, true);
  // System.out.println("faceList="+new Gson().toJson(faceList));
  // }
}
