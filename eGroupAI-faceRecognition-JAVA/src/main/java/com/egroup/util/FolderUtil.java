
package com.egroup.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/**
 * @author 作者 eGroupAI Team
 * @date 2019年4月26日 上午9:17:43
 * @version
 * @description:
 */
public class FolderUtil {

  public List<String> listName(File folder) {
    final List<String> fileNameList = new ArrayList<>();
    if (folder.exists()) {
      // init variable
      final File[] fileNames = folder.listFiles();

      for (File file : fileNames) {
        // if directory call the same method again
        if (file.isDirectory()) {
          fileNameList.addAll(listName(file));
        } else {
          fileNameList.add(FilenameUtils.getBaseName(file.getAbsolutePath()));
        }
      }
    }
    return fileNameList;
  }

  public List<File> listFile(File folder) {
    final List<File> fileNameList = new ArrayList<>();
    if (folder.exists()) {
      // init variable
      final File[] fileNames = folder.listFiles();

      for (File file : fileNames) {
        // if directory call the same method again
        if (file.isDirectory()) {
          fileNameList.addAll(listFile(file));
        } else {
          fileNameList.add(file);
        }
      }
    }
    return fileNameList;
  }

  public List<String> listPath(File folder) {
    final List<String> fileNameList = new ArrayList<>();
    if (folder.exists()) {
      // init variable
      final File[] fileNames = folder.listFiles();

      for (File file : fileNames) {
        // if directory call the same method again
        if (file.isDirectory()) {
          fileNameList.addAll(listName(file));
        } else {
          fileNameList.add(file.getAbsolutePath());
        }
      }
    }
    return fileNameList;
  }

  public boolean checkEmpty(String folderPath) {
    // init func
    final AttributeCheck attributeCheck = new AttributeCheck();
    // init variable
    boolean flag = true;
    if (attributeCheck.stringsNotNull(folderPath)) {
      final File file = new File(folderPath);
      if (file.exists() && file.isDirectory() && file.list().length > 0) {
        flag = false;
      }
    }
    return flag;
  }

  public boolean clean(String folderPath) {
    // init func
    final AttributeCheck attributeCheck = new AttributeCheck();
    // init variable
    boolean flag = false;
    if (attributeCheck.stringsNotNull(folderPath)) {
      final File file = new File(folderPath);
      if (file.exists() && file.isDirectory() && file.list().length > 0) {
        flag = true;
      }
    }
    return flag;
  }
}
