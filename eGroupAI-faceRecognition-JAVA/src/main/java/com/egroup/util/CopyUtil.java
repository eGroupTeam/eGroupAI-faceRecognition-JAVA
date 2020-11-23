package com.egroup.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

public class CopyUtil {
  private Logger LOGGER = LoggerFactory.getLogger(CmdUtil.class);

  /***
   * Copy file method using classic approach by using streams.
   * 
   * @param source file
   * @param dest file
   * @throws IOException
   */
  public boolean copyFile(File source, File dest) throws IOException {
    if (!source.getParentFile().exists()) {
      source.getParentFile().mkdirs();
    }
    if (!dest.getParentFile().exists()) {
      dest.getParentFile().mkdirs();
    }
    boolean flag = false;
    try (InputStream inputStream = new FileInputStream(source); OutputStream outputStream = new FileOutputStream(dest);) {
      final byte[] buf = new byte[1024];
      int bytesRead;
      while ((bytesRead = inputStream.read(buf)) > 0) {
        outputStream.write(buf, 0, bytesRead);
      }
      flag = true;
    } catch (Exception e) {
      LOGGER.error(new Gson().toJson(e));
    }
    return flag;
  }

  /**
   * copy folder
   * 
   * @param source folder be copy
   * @param dest folder copy to
   * @throws IOException
   */
  public void copyFolder(File source, File dest) throws IOException {
    if (source.isDirectory()) {
      if (!dest.exists()) {
        dest.mkdirs();
      }

      final String files[] = source.list();
      File srcFile = null;
      File destFile = null;
      for (String file : files) {
        /**
         * construct the src and dest file structure by adding the current folder
         */
        srcFile = new File(source, file);
        destFile = new File(dest, file);
        /** recursive copy the files or sub-folders */
        copyFolder(srcFile, destFile);
      }
    } else {
      copyFile(source, dest);
    }
  }
}
