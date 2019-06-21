package com.egroupai.engine.entity;

import java.util.ArrayList;
import java.util.List;

import com.egroupai.engine.control.EngineFunc;
import com.egroupai.engine.util.AttributeCheck;

/** 
* @author 作者 eGroupAI
* @date 2018年8月11日 下午10:30:05 
* @version 
* @description:
*/
public class TrainFace{
	private AttributeCheck attributeCheck = new AttributeCheck();
	private boolean isModelExist;
	private String trainListPath;
	private String modelPath;
	private StringBuilder cli;	
	private List<String> commandList = new ArrayList<String>();
	private String disk;
	private TrainResult trainResult = new TrainResult();
	private String trainResultLogPath;	
	
	// programe control
	private List<String> imagePathList = new ArrayList<>();
	private String imagePath;
	private String personId;
	private String imagePathJson;
	private boolean uploadFace = false;
	private String batchTrainPath;
	// blackwhite variable
	private Integer hasTrainFaceCount;
	private Integer train_successCount;
	private Integer train_failCount;
	private String enginePath;
	private List<String> trainResultList;
	
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
		this.disk = this.enginePath.substring(0,1);
		if(attributeCheck.stringsNotNull(this.enginePath,disk,trainListPath,modelPath)){
			if(this.isModelExist){
				cli = new StringBuilder("cd "+this.enginePath+" && "+disk+": && TrainFace --append "+trainListPath+" "+modelPath+"");		
			}else{
				cli = new StringBuilder("cd "+this.enginePath+" && "+disk+": && TrainFace "+trainListPath+" "+modelPath+"");					
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
	public List<String> getImagePathList() {
		return imagePathList;
	}
	public void setImagePathList(List<String> imagePathList) {
		this.imagePathList = imagePathList;
	}
	public String getImagePathJson() {
		return imagePathJson;
	}
	public void setImagePathJson(String imagePathJson) {
		this.imagePathJson = imagePathJson;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public boolean isUploadFace() {
		return uploadFace;
	}
	public void setUploadFace(boolean uploadFace) {
		this.uploadFace = uploadFace;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Integer getHasTrainFaceCount() {
		return hasTrainFaceCount;
	}
	public void setHasTrainFaceCount(Integer hasTrainFaceCount) {
		this.hasTrainFaceCount = hasTrainFaceCount;
	}
	public String getEnginePath() {
		return enginePath;
	}
	public void setEnginePath(String enginePath) {
		this.enginePath = enginePath;
	}
	public Integer getTrain_successCount() {
		return train_successCount;
	}
	public void setTrain_successCount(Integer train_successCount) {
		this.train_successCount = train_successCount;
	}
	public Integer getTrain_failCount() {
		return train_failCount;
	}
	public void setTrain_failCount(Integer train_failCount) {
		this.train_failCount = train_failCount;
	}
	public List<String> getTrainResultList() {
		return trainResultList;
	}
	public void setTrainResultList(List<String> trainResultList) {
		this.trainResultList = trainResultList;
	}
	public TrainResult getTrainResult() {
		return trainResult;
	}
	public void setTrainResult(TrainResult trainResult) {
		this.trainResult = trainResult;
	}
	public String getTrainResultLogPath() {
		return trainResultLogPath;
	}
	public void setTrainResultLogPath(String trainResultLogPath) {
		this.trainResultLogPath = trainResultLogPath;
	}
	public String getBatchTrainPath() {
		return batchTrainPath;
	}
	public void setBatchTrainPath(String batchTrainPath) {
		this.batchTrainPath = batchTrainPath;
	}
}
