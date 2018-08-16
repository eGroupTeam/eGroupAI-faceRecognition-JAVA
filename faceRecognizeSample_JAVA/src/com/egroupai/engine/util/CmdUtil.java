package com.egroupai.engine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;

public class CmdUtil {
	
	public boolean cmdProcessBuilder(List<String> commandList) {
		System.out.println("commandlist="+new Gson().toJson(commandList));
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
}
