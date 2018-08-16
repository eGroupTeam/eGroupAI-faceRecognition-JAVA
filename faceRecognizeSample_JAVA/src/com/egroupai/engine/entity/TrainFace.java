package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.egroupai.engine.control.EngineFunc;
import com.egroupai.engine.util.AttributeCheck;


/** 
* @author 作者 Daniel
* @date 2018年8月11日 下午10:30:05 
* @version 
* @description:
*/
/** 
* @author 作者 Daniel
* @date 2018年8月11日 下午10:30:05 
* @version 
* @description:
*/
public class TrainFace extends EngineFunc{
	private AttributeCheck attributeCheck = new AttributeCheck();
	private boolean isModelExist;
	private String trainListPath;
	private String modelPath;
	private StringBuilder cli;	
	private List<String> commandList = new ArrayList<String>();
	private String disk;
		
	public boolean isModelExist() {
		return isModelExist;
	}
	public void setModelExist(boolean isModelExist) {
		this.isModelExist = isModelExist;
	}
	public String getTrainListPath() {
		return trainListPath;
	}
	public void setTrainListPath(String trainListPath) {
		this.trainListPath = trainListPath;
	}
	public String getModelPath() {
		return modelPath;
	}
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}
	public StringBuilder getCli() {
		return cli;
	}
	public void setCli(StringBuilder cli) {
		this.cli = cli;
	}
	
	public void generateCli() {
		this.disk = ENGINEPATH.substring(0,1);
		if(attributeCheck.stringsNotNull(ENGINEPATH,disk,trainListPath,modelPath)){
			if(this.isModelExist){
				cli = new StringBuilder("cd "+ENGINEPATH+" && "+disk+": && TrainFace --append "+trainListPath+" "+modelPath+"");		
			}else{
				cli = new StringBuilder("cd "+ENGINEPATH+" && "+disk+": && TrainFace "+trainListPath+" "+modelPath+"");					
			}
		}else{
			cli = null;
		}
		System.out.println("cli="+cli);
	}
	
	public List<String> getCommandList() {
		if(attributeCheck.stringsNotNull(cli.toString())){
			commandList = new ArrayList<String>();
			commandList.add("cmd");
			commandList.add("/"+disk);
			commandList.add(cli.toString().replace("/", "/"));
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

	
}
