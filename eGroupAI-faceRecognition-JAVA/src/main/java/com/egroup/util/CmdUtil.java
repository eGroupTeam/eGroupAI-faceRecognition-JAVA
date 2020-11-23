package com.egroup.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

public class CmdUtil {
  private static Logger LOGGER = LoggerFactory.getLogger(CmdUtil.class);
  private static final String TASKLIST = "tasklist";
  private static final String KILL = "taskkill /F /IM ";
  private static InputStreamReader INPUTSTREAMREADER = null;
  private static BufferedReader BUFFEREDREADER = null;

  public boolean cmdProcessBuilder(List<String> commandList) {
    // init func
    Process process = null;
    InputStreamReader inputStreamReader = null;
    BufferedReader bufferedreader = null;
    final ProcessBuilder processBuilder = new ProcessBuilder(commandList);
    processBuilder.redirectErrorStream(true); // redirect stderr to stdin
    try {
      process = processBuilder.start();
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }

    /* Read the output of command prompt */
    inputStreamReader = new InputStreamReader(process.getInputStream());
    bufferedreader = new BufferedReader(inputStreamReader);

    String line = "";
    try {
      while ((line = bufferedreader.readLine()) != null) {
        LOGGER.info(line);
      }
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }
    try {
      process.waitFor();
    } catch (InterruptedException e) {
      LOGGER.error(new Gson().toJson(e));
      Thread.currentThread().interrupt();
    }

    process.destroy();

    try {
      bufferedreader.close();
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }

    try {
      inputStreamReader.close();
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }
    return true;
  }

  public boolean server_cmdProcessBuilder(List<String> commandList) {
    // init func
    Process process = null;
    InputStreamReader inputStreamReader = null;
    BufferedReader bufferedreader = null;
    final ProcessBuilder processBuilder = new ProcessBuilder(commandList);
    processBuilder.redirectErrorStream(true); // redirect stderr to stdin
    try {
      process = processBuilder.start();
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }

    /* Read the output of command prompt */
    inputStreamReader = new InputStreamReader(process.getInputStream());
    bufferedreader = new BufferedReader(inputStreamReader);

    String line = "";
    try {
      while ((line = bufferedreader.readLine()) != null) {
        LOGGER.info(line);
      }
    } catch (IOException e) {
    }
    try {
      process.waitFor();
    } catch (InterruptedException e) {
      LOGGER.error(new Gson().toJson(e));
      Thread.currentThread().interrupt();
    }

    process.destroy();

    try {
      bufferedreader.close();
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }

    try {
      inputStreamReader.close();
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }
    return true;
  }

  /**
   * Delete process run by cmdUtil
   * 
   * @param processName - the process you want to kill that you create to windows like RetrieveFace_ByCam.exe
   */
  public void cmdProcessTerminate(String processName) {
    // Detect the process run by cmd
    if (isProcessRunning(processName)) {
      // Kill the process run by windows
      killProcess(processName);
    }
    try {
      if (BUFFEREDREADER != null) {
        BUFFEREDREADER.close();
      }
      if (INPUTSTREAMREADER != null) {
        INPUTSTREAMREADER.close();
      }
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }
  }

  /**
   * Detect the process run by cmd
   * 
   * @param serviceName
   * @return boolean
   */
  public boolean isProcessRunning(String serviceName) {
    // Start the Process
    Process process = null;
    try {
      process = Runtime.getRuntime().exec(TASKLIST);
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }

    // Read and list the process run by windows
    final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line;
    try {
      while ((line = reader.readLine()) != null) {
        if (line.contains(serviceName)) {
          return true;
        }
      }
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }
    return false;
  }

  /**
   * Kill the process run by windows
   * 
   * @param serviceName
   */
  protected void killProcess(String serviceName) {
    try {
      Runtime.getRuntime().exec(KILL + serviceName);
    } catch (IOException e) {
      LOGGER.error(new Gson().toJson(e));
    }
  }


  /**
   * Check process run by cmdUtil
   * 
   * @param processName - the process you want to kill that you create to windows like RecognizeFace.exe
   */
  public boolean cmdProcessCheck(String processName) {
    // Detect the process run by cmd
    if (isProcessRunning(processName)) {
      // Kill the process run by windows
      return true;
    }
    return false;
  }
}
