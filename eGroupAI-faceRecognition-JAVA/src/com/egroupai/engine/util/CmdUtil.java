package com.egroupai.engine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;

public class CmdUtil {
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";
	private static InputStreamReader INPUTSTREAMREADER = null;
	private static BufferedReader BUFFEREDREADER = null;
	
	public boolean cmdProcessBuilder(List<String> commandList) {
//		System.out.println("commandlist="+new Gson().toJson(commandList));
		Process process = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedreader = null;
		final ProcessBuilder processBuilder = new ProcessBuilder(commandList);
		try {
			process = processBuilder.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 		
		/* Read the output of command prompt */
		inputStreamReader = new InputStreamReader(process.getInputStream());
		bufferedreader = new BufferedReader(inputStreamReader);
		String line="";
		try {
			while ((line=bufferedreader.readLine()) != null) {}			
		} catch (IOException e) {
		}		
		try {
			process.waitFor();
		} catch (InterruptedException e) {
		}		
			process.destroy();		
		try {
			bufferedreader.close();
		} catch (IOException e) {
		}		
		try {
			inputStreamReader.close();
		} catch (IOException e) {
		}		
		return true;
	}
	
	/**
	 * Delete process run by cmdUtil
	 * @param processName - the process you want to kill that you create to windows like RecognizeFace.exe
	 */
	public static void cmdProcessTerminate(String processName){
		// Detect the process run by cmd 
		if (isProcessRunning(processName)) {
			// Kill the process run by windows
			killProcess(processName);
		}		
		try {
			if(BUFFEREDREADER!=null) {
				BUFFEREDREADER.close();				
			}
			if(INPUTSTREAMREADER!=null) {
				INPUTSTREAMREADER.close();				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete process run by cmdUtil
	 * @param processName - the process you want to kill that you create to windows like RecognizeFace.exe
	 */
	public static boolean cmdProcessCheck(String processName){
		// Detect the process run by cmd 
		if (isProcessRunning(processName)) {
			// Kill the process run by windows
			return true;
		}
		return false;
	}
	
	
	/**
	 * Detect the process run by cmd 
	 * @param serviceName 
	 * @return boolean
	 */
	protected static boolean isProcessRunning(String serviceName) {
		//Start the Process
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(TASKLIST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Read and list the process run by windows
		final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		try {
			while ((line = reader.readLine()) != null) {
//				System.out.println(line);
				if (line.contains(serviceName)) {
					return true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
 	/**
	 * Kill the process run by windows
	 * @param serviceName 
	 */
	protected static void killProcess(String serviceName) {
		try {
			Runtime.getRuntime().exec(KILL + serviceName);
		} catch (IOException e) {
			// TODO Auto-senerated catch block
			e.printStackTrace();
		}
	}
}
