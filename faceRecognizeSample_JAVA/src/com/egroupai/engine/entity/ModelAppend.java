package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.egroupai.engine.control.EngineFunc;
import com.egroupai.engine.util.AttributeCheck;

/** 
* @author 作者 eGroupAI Team
* @date 2018年8月12日 下午10:47:59 
* @version 
* @description:
*/
public class ModelAppend extends EngineFunc{
	private AttributeCheck attributeCheck;
	private String ListPath;
	private String trainedBinaryPath;
	private String trainedFaceInfoPath;
	private List<String> modelBinaryList; 
	private List<String> modelFaceInfoList; 
	private StringBuilder cli;	
	private List<String> commandList = new ArrayList<String>();
	private String disk;
	// program control
	private String enginePath;
	private HashMap<String, String> modelHashmap = new HashMap<>();
	
	public String getListPath() {
		return ListPath;
	}
	public void setListPath(String listPath) {
		ListPath = listPath;
	}
	public String getTrainedBinaryPath() {
		return trainedBinaryPath;
	}
	public void setTrainedBinaryPath(String trainedBinaryPath) {
		this.trainedBinaryPath = trainedBinaryPath;
	}
	public String getTrainedFaceInfoPath() {
		return trainedFaceInfoPath;
	}
	public void setTrainedFaceInfoPath(String trainedFaceInfoPath) {
		this.trainedFaceInfoPath = trainedFaceInfoPath;
	}
	public StringBuilder getCli() {
		return cli;
	}
	public void setCli(StringBuilder cli) {
		this.cli = cli;
	}
	public List<String> getCommandList() {
		if(attributeCheck==null){
			attributeCheck = new AttributeCheck();
		}
		if(attributeCheck.stringsNotNull(cli.toString())){
			commandList = new ArrayList<String>();
			commandList.add("cmd");
			commandList.add("/C");
			commandList.add(disk+": && "+cli.toString().replace("/", "/"));
		}
		return commandList;
	}
	public void setCommandList(List<String> commandList) {
		this.commandList = commandList;
	}
	public String getDisk() {
		return disk;
	}
	public void setDisk(String disk) {
		this.disk = disk;
	}	
	public void generateCli(String enginePath) {
		if(attributeCheck==null){
			attributeCheck = new AttributeCheck();
		}
		this.disk = enginePath.substring(0,1);
		if(attributeCheck.stringsNotNull(enginePath,disk,ListPath,trainedBinaryPath,trainedFaceInfoPath)){
			cli = new StringBuilder("cd "+enginePath+" && "+disk+": && ModelAppend "+ListPath+" "+trainedBinaryPath+" "+trainedFaceInfoPath);
			
		}else{
			cli = null;
		}
		 System.out.println("cli="+cli);
	}
	public List<String> getModelBinaryList() {
		if(attributeCheck==null){
			attributeCheck = new AttributeCheck();
		}
		if(!attributeCheck.listNotNull_Zero(modelBinaryList)){
			modelBinaryList = new ArrayList<>();
		}
		return modelBinaryList;
	}
	public void setModelBinaryList(List<String> modelBinaryList) {
		this.modelBinaryList = modelBinaryList;
	}
	public List<String> getModelFaceInfoList() {
		if(!attributeCheck.listNotNull_Zero(modelFaceInfoList)){
			modelFaceInfoList = new ArrayList<>();
		}
		return modelFaceInfoList;
	}
	public void setModelFaceInfoList(List<String> modelFaceInfoList) {
		this.modelFaceInfoList = modelFaceInfoList;
	}
	public HashMap<String, String> getModelHashmap() {
		return modelHashmap;
	}
	public void setModelHashmap(HashMap<String, String> modelHashmap) {
		this.modelHashmap = modelHashmap;			     	         
		this.modelBinaryList = new ArrayList<String>(modelHashmap.keySet());
		this.modelFaceInfoList = new ArrayList<String>(modelHashmap.values());
	}
	public String getEnginePath() {
		return enginePath;
	}
	public void setEnginePath(String enginePath) {
		this.enginePath = enginePath;
	}
}
