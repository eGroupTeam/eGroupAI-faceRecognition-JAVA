package sample.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CmdUtil {
	
//	public static void main(String args[]) {		
//		// init variable
//		final String RetrieveFace_ByCamPath = "F:/RetrieveFace_ByCam_Release_20171107/RetrieveFace_ByCam_Release_20171107/";
//		
//		final StringBuilder cmd = new StringBuilder("cd "+RetrieveFace_ByCamPath+" && C: && "
//				+ "TrainFace list.txt eGroup/eGroup.Model eGroup/eGroup.Face");
//		final List<String> commandList = new ArrayList<String>();
//		commandList.add("cmd");
//		commandList.add("/f");
//		commandList.add(cmd.toString().replace("/", "/"));
//		cmdProcessBuilder(commandList);
//	}
	
	public void trainFace(String RetrieveFace_ByCamPath,String modelName) {
		// init variable
		File file = new File(RetrieveFace_ByCamPath+"/eGroup/"+modelName);
		StringBuilder cmd = null;
		if(file.exists()&&file.getTotalSpace()>0) {
			cmd = new StringBuilder("cd "+RetrieveFace_ByCamPath+" && C: && "
				+ "TrainFace -a list.txt eGroup/"+modelName+" eGroup/"+modelName);
		}else {
			cmd = new StringBuilder("cd "+RetrieveFace_ByCamPath+" && C: && "
					+ "TrainFace list.txt eGroup/"+modelName+" eGroup/"+modelName);
		}
		System.out.println("trainFace cmd="+cmd);
		if(cmd!=null) {
			final List<String> commandList = new ArrayList<String>();
			commandList.add("cmd");
			commandList.add("/c");
			commandList.add(cmd.toString().replace("/", "\\"));
			System.out.println("commandList="+commandList);
			cmdProcessBuilder(commandList);
		}		
	}
	
//	public void detectFace(String RetrieveFace_ByCamPath) {
//		// init variable
////		final String RetrieveFace_ByCamPath = "C:/Users/Daniel/Desktop/RetrieveFace_ByCam_Release_20171107/";
//		
//		File file = new File(RetrieveFace_ByCamPath+"/eGroup/eGroup.Model.binary");
//		StringBuilder cmd = null;
//		cmd = new StringBuilder("cd "+RetrieveFace_ByCamPath+" && C: && "
//			+ "RetrieveFace_ByCam.exe -k 10 -t 0.4 -r 5 -s 720p -f output_frame -p "
//			+ "output_face eGroup/eGroup.Model.binary eGroup/eGroup.Model.faceInfor output.json");
//		if(cmd!=null) {
//			final List<String> commandList = new ArrayList<String>();
//			commandList.add("cmd");
//			commandList.add("/c");
//			commandList.add(cmd.toString().replace("/", "\\"));
//			System.out.println("commandList="+commandList);
//			cmdProcessBuilder(commandList);
//		}		
//	}
	
	public void retriveFace(String RetrieveFace_ByCamPath,String modelName,Double threshold,Integer rate,Integer resolution,Integer minFace,Integer inputSource) {
		// init variable
		final StringBuilder cmd = new StringBuilder("cd "+RetrieveFace_ByCamPath+" && C: && "
			+ "RetrieveFace_ByCam.exe -k 10 -t "+threshold+" -r "+rate+" -s "+resolution + " -m "+minFace + " -c "+inputSource
			+" -f output_frame -p output_face eGroup/"+modelName+".binary eGroup/"+modelName+".faceInfor output.json");
		if(cmd!=null) {
			final List<String> commandList = new ArrayList<String>();
			commandList.add("cmd");
			commandList.add("/c");
			commandList.add(cmd.toString().replace("/", "\\"));
			System.out.println("commandList="+commandList);
			cmdProcessBuilder(commandList);
		}		
	}
	
	public void detectFace(String RetrieveFace_ByCamPath,String modelName,Double threshold,Integer rate,Integer resolution,Integer minFace,Integer inputSource) {
		// init variable
		final StringBuilder cmd = new StringBuilder("cd "+RetrieveFace_ByCamPath+" && C: && "
			+ "RetrieveFace_ByCam.exe -k 10 -t "+ threshold +" -r "+rate +" -s "+resolution + " -m "+minFace + " -c "+inputSource
			+" eGroup/"+modelName+".binary eGroup/"+modelName+".faceInfor output.json");
		
		if(cmd!=null) {
			final List<String> commandList = new ArrayList<String>();
			commandList.add("cmd");
			commandList.add("/c");
			commandList.add(cmd.toString().replace("/", "\\"));
			System.out.println("commandList="+commandList);
			cmdProcessBuilder(commandList);
		}		
	}
	
	
	private static boolean cmdProcessBuilder(List<String> commandList) {
		/* Read the output of command prompt */
		Process process = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedreader = null;
		final ProcessBuilder processBuilder = new ProcessBuilder(commandList);
		// 執行指令
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
