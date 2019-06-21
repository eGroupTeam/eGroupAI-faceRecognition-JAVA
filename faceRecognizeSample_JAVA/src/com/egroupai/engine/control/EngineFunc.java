package com.egroupai.engine.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.egroupai.engine.util.AttributeCheck;
import com.egroupai.engine.util.CmdUtil;
import com.egroupai.engine.util.TxtUtil;
import com.egroupai.controller.BaseController;
import com.egroupai.engine.entity.ModelAppend;
import com.egroupai.engine.entity.ModelAppendInfo;
import com.egroupai.engine.entity.ModelAppendResult;
import com.egroupai.engine.entity.ModelInsert;
import com.egroupai.engine.entity.ModelInsertInfo;
import com.egroupai.engine.entity.ModelInsertResult;
import com.egroupai.engine.entity.ModelSwitch;
import com.egroupai.engine.entity.ModelSwitchResult;
import com.egroupai.engine.entity.RecognizeFace;
import com.egroupai.engine.entity.TrainFace;
import com.egroupai.engine.entity.TrainInfo;
import com.egroupai.engine.entity.TrainResult;
import com.google.gson.Gson;

/**
 * EngineControl Func
* @author 作者 eGroupAI
* @date 2018年8月10日 上午10:54:20 
* @version 
* @description:
 */
public class EngineFunc extends BaseController{
//	public static void main(String args[]){
//		// RecognizeFace
//		RecognizeFace recognizeFace = new RecognizeFace();
//		recognizeFace.setThreshold(0.7);
//		recognizeFace.setHideMainWindow(false);
//		recognizeFace.setResolution("720p");
//		recognizeFace.setOutputFacePath("outputFace");
//		recognizeFace.setOutputFramePath("outputFrame");
//		recognizeFace.setCam("0");
//		recognizeFace.setMinimumFaceSize(100);
//		recognizeFace.setTrainedBinaryPath("eGroup\\eGroup.Model.binary");
//		recognizeFace.setTrainedFaceInfoPath("eGroup\\eGroup.Model.faceInfo");
//		recognizeFace.setJsonPath("output");
//		recognizeFace(recognizeFace);
		
//		// TrainFace
//		TrainFace trainFace = new TrainFace();
//		trainFace.setModelExist(false);
//		trainFace.setTrainListPath("list1.txt");
//		trainFace.setModelPath("eGroup1\\eGroup.Model");
//		trainFace(trainFace);
//		
//		trainFace.setModelExist(false);
//		trainFace.setTrainListPath("list2.txt");
//		trainFace.setModelPath("eGroup2\\eGroup.Model");
//		trainFace(trainFace);
		
//		// ModelMerge
//		ModelMerge modelMerge = new ModelMerge();
//		modelMerge.setListPath(ENGINEPATH+"\\ModelList.egroup.List");
//		modelMerge.setTrainedBinaryPath(ENGINEPATH+"\\eGroup_merge\\eGroup_merged.binary");
//		modelMerge.setTrainedFaceInfoPath(ENGINEPATH+"\\Engine\\eGroup_merge\\eGroup_merged.");
//		modelMerge.setModel1Binary(ENGINEPATH+"\\eGroup1\\eGroup.Model.binary");
//		modelMerge.setModel1FaceInfo(ENGINEPATH+"\\eGroup1\\eGroup.Model.faceInfo");
//		modelMerge.setModel2Binary(ENGINEPATH+"\\eGroup2\\eGroup.Model.binary");
//		modelMerge.setModel2FaceInfo(ENGINEPATH+"\\eGroup2\\eGroup.Model.faceInfo");
//		modelMerge(modelMerge);
		
//		// ModelSwitch
//		ModelSwitch modelSwitch = new ModelSwitch();
//		modelSwitch.setNewModelBinaryPath(ENGINEPATH+"\\eGroup5\\eGroup.Model.binary");
//		modelSwitch.setNewModelFaceInfoPath(ENGINEPATH+"\\eGroup5\\eGroup.Model.faceInfo");
//		modelSwitch.setSwitchFilePath(ENGINEPATH+"\\Singal_For_Model_Switch.txt");
//		modelSwitch(modelSwitch);
//	}
	
	public  boolean recognizeFace(RecognizeFace recognizeFace){		
		boolean flag = false;
		// init func 
		recognizeFace.generateCli();
		if(recognizeFace.getCommandList()!=null){
			final CmdUtil cmdUtil = new CmdUtil();
			flag = cmdUtil.cmdProcessBuilder(recognizeFace.getCommandList());				
		}
		return flag;
	}
		
	public TrainResult trainFace(TrainFace trainFace){		
		// init variabl
		TrainResult trainResult = new TrainResult();
		
		trainFace.generateCli();
		if(trainFace.getCommandList()!=null){
			final CmdUtil cmdUtil = new CmdUtil();
			if(cmdUtil.cmdProcessBuilder(trainFace.getCommandList())){	
				// init variabl
				final String logTrainResultLog_path = LOG_TRAINRESULT_PATH;	
				trainResult = trainFace_check(logTrainResultLog_path);
			}else{
				trainResult.setTrainCmdSuccess(false);
			}
		}else{
			trainResult.setTrainCmdSuccess(false);			
		}		
		return trainResult;
	}
	
	private TrainResult trainFace_check(String trainResultLog_path){	
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		final TxtUtil txtUtil = new TxtUtil();
		// init variable
		long time1, time2;
		time1 = System.currentTimeMillis();
		TrainResult trainResult = new TrainResult();
		List<TrainInfo> trainInfoList = new ArrayList<>();
				
		// Get the trainFace result
		final List<String> trainResultLineList = txtUtil.read_lineList(trainResultLog_path);
		if(attributeCheck.listNotNull_Zero(trainResultLineList)){
			TrainInfo trainInfo = new TrainInfo();
			
			for(String trainResultLine : trainResultLineList){
				String trainArray[] = trainResultLine.split("\\t");
				trainInfo = new TrainInfo();			
				switch (trainArray.length) {
				case 4:
					System.out.println("trainArray[1]="+trainArray[1]);
					if(trainArray[1].equals("Pass")){
						trainResult.getPassFacePathList().add(trainArray[2]);
					}else{
						trainResult.getFailFacePathList().add(trainArray[2]);
					}
					trainInfo.setFacePath(trainArray[2]);
					trainInfo.setStatus(trainArray[1]);
					trainInfo.setTime(trainArray[0]);
					trainInfo.setPersonId(trainArray[3]);
					trainInfoList.add(trainInfo);
					break;
				case 2:
					if(trainArray[1].equals("faces were trained in the list file")){
						trainResult.setFaceSize(Integer.valueOf(trainArray[0].replaceAll(":", "").toString()));
					}else if(trainArray[1].equals("list file size")){
						trainResult.setFileSize(Integer.valueOf(trainArray[0].replaceAll(":", "").toString()));
					}
					break;
				case 1:
					if(trainArray[0].startsWith("Processing Time")){
						trainResult.setProcessingTime(trainArray[0].toString());
					}else{
						trainResult.setAvgPprocessingTime(trainArray[0].toString());
					}
					break;
				default:
					break;
				}
			}
			trainResult.setTrainInfoList(trainInfoList);
			
			System.out.println("trainResult="+new Gson().toJson(trainResult));
			time2 = System.currentTimeMillis();
			System.out.println("read trainResult log：" + (time2-time1) + "ms");
		}else{
			trainResult.setTrainResultFileExist(false);
		}
		return trainResult;
	}
	
	public static ModelAppendResult modelAppend(ModelAppend modelAppend){
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		// init variable
		ModelAppendResult modelAppendResult = new ModelAppendResult();	
//		System.out.println("modelAppend.getEnginePath()="+modelAppend.getEnginePath());
//		System.out.println("modelAppend.getListPath()="+modelAppend.getListPath());
//		System.out.println("modelAppend.getTrainedBinaryPath()="+modelAppend.getTrainedBinaryPath());
//		System.out.println("modelAppend.getTrainedFaceInfoPath()="+modelAppend.getTrainedFaceInfoPath());
//		System.out.println("attributeCheck.listNotNull_Zero(modelAppend.getModelBinaryList(),modelAppend.getModelFaceInfoList()="+attributeCheck.listNotNull_Zero(modelAppend.getModelBinaryList(),modelAppend.getModelFaceInfoList()));
//		System.out.println("modelAppend.getModelHashmap().size()>0="+(modelAppend.getModelHashmap().size()>0));
		if(modelAppend != null&&attributeCheck.stringsNotNull(modelAppend.getEnginePath(),modelAppend.getListPath(),modelAppend.getTrainedBinaryPath(),modelAppend.getTrainedFaceInfoPath())
				&&(attributeCheck.listNotNull_Zero(modelAppend.getModelBinaryList(),modelAppend.getModelFaceInfoList())
						||modelAppend.getModelHashmap().size()>0)){
			
			System.out.println("modelAppend.getTrainedBinaryPath()="+modelAppend.getTrainedBinaryPath());
			// init func
			final TxtUtil txtUtil = new TxtUtil();
			// init variable
			final List<String> dataList = new ArrayList<>();
			
			if(modelAppend.getModelHashmap().size()>0){
				modelAppend.getModelHashmap().forEach((key, value) -> {
					dataList.add(key +"	"+ value);
		        });
			}else{
				final int modelCount = modelAppend.getModelBinaryList().size();
				for(int i=0;i<modelCount;i++){
					dataList.add(modelAppend.getModelBinaryList().get(i)+"	"+modelAppend.getModelFaceInfoList().get(i));
				}
			}
			txtUtil.create(modelAppend.getListPath(), dataList);
			
			modelAppend.generateCli(modelAppend.getEnginePath());
			if(modelAppend.getCommandList()!=null){
				final CmdUtil cmdUtil = new CmdUtil();
				if(cmdUtil.cmdProcessBuilder(modelAppend.getCommandList())){
					modelAppendResult = modelAppend_check(modelAppend);
				}else{
					modelAppendResult.setAppendCmdSuccess(false);
				}
			}else{
				modelAppendResult.setAppendCmdSuccess(false);
			}
		}
		return modelAppendResult;
	}
	
	private static ModelAppendResult modelAppend_check(ModelAppend modelAppend){	
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		final TxtUtil txtUtil = new TxtUtil();

		// init variable
		final String modelAppend_path = modelAppend.getEnginePath()+"/Log.ModelAppend.eGroup";
		final File modelAppend_file = new File(modelAppend.getEnginePath()+"/Log.ModelAppend.eGroup");
		int waitCount = 0;
		long time1= System.currentTimeMillis(), time2;
		ModelAppendResult modelAppendResult = new ModelAppendResult();
		List<ModelAppendInfo> modelAppendInfoList = new ArrayList<>();
		// Check whether modelAppend log exist, wait 1.5 sec
		try {
			while(true){
				if(modelAppend_file.exists()||waitCount==3){
					break;
				}
				waitCount++;
				Thread.sleep(300);
			}			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get the model append log
		final List<String> modelAppendLineList = txtUtil.read_lineList(modelAppend_path);
		System.out.println("modelAppendLineList="+new Gson().toJson(modelAppendLineList));
		if(attributeCheck.listNotNull_Zero(modelAppendLineList)){
			ModelAppendInfo modelAppendInfo = new ModelAppendInfo();			
			for(String modelAppendLine : modelAppendLineList){
				String modelAppendArray[] = modelAppendLine.split("\\t");
				System.out.println("modelAppendArray="+new Gson().toJson(modelAppendArray));
				switch (modelAppendArray.length) {	
				case 5:
					if(modelAppendArray[2].contains("DBSize")){
						System.out.println("DBSize="+modelAppendArray[3].substring(6, modelAppendArray[3].length()));
						modelAppendInfo.setDBSizeCheckStatus(modelAppendArray[1]);
						modelAppendInfo.setDBSize(Integer.valueOf(modelAppendArray[2].substring(7, modelAppendArray[2].length()).trim()));
						modelAppendInfo.setDBBinaryPath(modelAppendArray[3]);
						modelAppendInfo.setDBFaceInfoPath(modelAppendArray[4]);
						modelAppendInfoList.add(modelAppendInfo);
						modelAppendInfo = new ModelAppendInfo();
					}
					break;
				case 4:
					System.out.println("modelAppendArray[2]="+modelAppendArray[1]);
					if(modelAppendArray[2].equals("Parsing")){
						modelAppendResult.setModelListPath(modelAppendArray[3].replaceAll("file: ", ""));
						modelAppendResult.setModelListCheckStatus(modelAppendArray[1]);
					}else if(modelAppendArray[2].equals("WorkingFolder")){
						System.out.println("modelAppendArray[3].substring(1, (modelAppendArray[3].indexOf(GB)))="+modelAppendArray[3].substring(1, (modelAppendArray[3].indexOf("GB"))));
						modelAppendInfo.setWorkingFolderCheckStatus(modelAppendArray[1]);
						modelAppendInfo.setWorkingFolderSize(Double.valueOf(modelAppendArray[3].substring(1, (modelAppendArray[3].indexOf("GB")))));
						modelAppendInfo.setWorkingFolderStatus(modelAppendArray[3].substring((modelAppendArray[3].indexOf("GB")+3), (modelAppendArray[3].lastIndexOf("("))).trim());
					}else if(modelAppendArray[2].equals("OutputBinary")){
						System.out.println("modelAppendArray[3].substring(1, (modelAppendArray[3].indexOf(GB)))="+modelAppendArray[3].substring(1, (modelAppendArray[3].indexOf("GB"))));
						modelAppendInfo.setOutputBinaryCheckStatus(modelAppendArray[1]);
						modelAppendInfo.setOutputBinarySize(Double.valueOf(modelAppendArray[3].substring(1, (modelAppendArray[3].indexOf("GB")))));
						modelAppendInfo.setOutputBinaryStatus(modelAppendArray[3].substring((modelAppendArray[3].indexOf("GB")+3), (modelAppendArray[3].lastIndexOf("("))).trim());
					}else if(modelAppendArray[2].equals("OutputFaceInfo")){
						System.out.println("modelAppendArray[3].substring(1, (modelAppendArray[3].indexOf(GB)))="+modelAppendArray[3].substring(1, (modelAppendArray[3].indexOf("GB"))));
						modelAppendInfo.setOutputFaceInfoCheckStatus(modelAppendArray[1]);
						modelAppendInfo.setOutputFaceInfoSize(Double.valueOf(modelAppendArray[3].substring(1, (modelAppendArray[3].indexOf("GB")))));
						modelAppendInfo.setOutputFaceInfoStatus(modelAppendArray[3].substring((modelAppendArray[3].indexOf("GB")+3), (modelAppendArray[3].lastIndexOf("("))).trim());
					}
					break;
				case 1:
					if(modelAppendArray[0].startsWith("Total faces: ")){
						System.out.println(modelAppendArray[0].substring( modelAppendArray[0].indexOf("Total faces: "), modelAppendArray[0].indexOf("in the appended new model")).trim());
						modelAppendResult.setTotalFaceCount(Integer.valueOf(modelAppendArray[0].substring(modelAppendArray[0].indexOf("Total faces: ")+12, modelAppendArray[0].indexOf("in the appended new model")).trim()));
					}else{
						System.out.println(modelAppendArray[0].substring(0, modelAppendArray[0].indexOf("of models appended pass")));
						System.out.println(modelAppendArray[0].substring( modelAppendArray[0].indexOf("/")+1, modelAppendArray[0].indexOf("of models appended failed")));
						modelAppendResult.setAppendPassCount(Integer.valueOf(modelAppendArray[0].substring(0, modelAppendArray[0].indexOf("of models appended pass")).trim()));
						modelAppendResult.setAppendFailCount(Integer.valueOf(modelAppendArray[0].substring( modelAppendArray[0].indexOf("/")+1,modelAppendArray[0].indexOf("of models appended failed")).trim()));
					}		
					break;
				default:
					break;
				}
				
			}
			// delete the model append log file
			modelAppend_file.delete();
			// set the model append  result info
			modelAppendResult.setModelAppendInfoList(modelAppendInfoList);
			// calculate process time
			System.out.println("modelAppendResult="+new Gson().toJson(modelAppendResult));
			time2 = System.currentTimeMillis();
			System.out.println("read modelAppend log：" + (time2-time1) + "ms");
		}
		return modelAppendResult;
	}	
	
	public ModelSwitchResult modelSwitch(ModelSwitch modelSwitch){
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		// init variable
		boolean flag = false;
		ModelSwitchResult modelSwitchResult = new ModelSwitchResult();
		System.out.println("modelSwitch.getNewModelPath()="+modelSwitch.getNewModelPath());
		System.out.println("modelSwitch.getSwitchFilePath()="+modelSwitch.getSwitchFilePath());
		System.out.println("modelSwitch.getEnginePath()="+modelSwitch.getEnginePath());
		System.out.println("modelSwitch.getModelSwitchLogPath()="+modelSwitch.getModelSwitchLogPath());
		if(modelSwitch!=null&&attributeCheck.stringsNotNull(modelSwitch.getNewModelPath(),modelSwitch.getSwitchFilePath(),modelSwitch.getEnginePath(),modelSwitch.getModelSwitchLogPath())){
			// init variable
			final String newModelBinary_path = modelSwitch.getNewModelPath()+".binary";
			final String newModelFaceInfo_path = modelSwitch.getNewModelPath()+".faceInfo";
			final File newModelBinary_file = new File(newModelBinary_path);
			final File newModelFaceInfo_file = new File(newModelFaceInfo_path);
			// Check Model Files 
			System.out.println("newModelBinary_path="+newModelBinary_path);
			System.out.println("newModelFaceInfo_path="+newModelFaceInfo_path);
			System.out.println("newModelBinary_file.exists()="+newModelBinary_file.exists());
			System.out.println("newModelFaceInfo_file.exists()="+newModelFaceInfo_file.exists());
			if(newModelBinary_file.exists()&&newModelFaceInfo_file.exists()) {			
				// Model 
				final List<String> dataList = new ArrayList<>();
				dataList.add(newModelBinary_path);
				dataList.add(newModelFaceInfo_path);
				
				// init func
				final TxtUtil txtUtil = new TxtUtil();
				System.out.println("modelSwitch.getSwitchFilePath()--="+modelSwitch.getSwitchFilePath());
				flag = txtUtil.create(modelSwitch.getSwitchFilePath(), dataList);
				System.out.println("modelSwitch.getSwitchFilePath()-flag="+flag);
				File file;
				try {
					while(true){					
						if(flag){
							file = new File(modelSwitch.getModelSwitchLogPath());
							if(file.exists()){
								modelSwitchResult = modelSwitch_check(modelSwitch);
								flag = modelSwitchResult.isSuccess();
								break;
							}
							System.out.println("Model is Switching...");
							Thread.sleep(1000);
						}else{
							flag = false;
							break;
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		modelSwitchResult.setSuccess(flag);
		return modelSwitchResult;
	}
	
	private ModelSwitchResult modelSwitch_check(ModelSwitch modelSwitch){
		long time1, time2;
		time1 = System.currentTimeMillis();		
		// init variable
		boolean flag = true;
		final TxtUtil txtUtil = new TxtUtil();
		final AttributeCheck attributeCheck = new AttributeCheck();
		ModelSwitchResult modelSwitchResult = new ModelSwitchResult();
		List<String> modelSwitchLineList = txtUtil.read_lineList(modelSwitch.getModelSwitchLogPath());
		if(attributeCheck.listNotNull_Zero(modelSwitchLineList)){
			for(String modelSwitchLine : modelSwitchLineList){
				String modelSwitchArray[] = modelSwitchLine.split("\\t");
				if(modelSwitchArray[1].equals("Pass")){
					if(modelSwitchArray[2].equals("CheckBinary")){
						modelSwitchResult.setCheckBinaryPass(true);
					}else if(modelSwitchArray[2].equals("CheckFaceInfo")){
						modelSwitchResult.setCheckFaceInfoPass(true);
					}
				}else if(modelSwitchArray[1].equals("Report")){
					if(modelSwitchArray[3].startsWith("Overall reload time:")){
						modelSwitchResult.setFaceReload(modelSwitchArray[3]);
					}else{
						modelSwitchResult.setReloadTime(modelSwitchArray[3]);
					}
				}else{
					flag = false;
				}
			}		
			time2 = System.currentTimeMillis();
			System.out.println("read modelSwitchResult log：" + (time2-time1) + "ms");
		}else{
			flag = false;
		}
		modelSwitchResult.setSuccess(flag);
		System.out.println("modelSwitchResult="+new Gson().toJson(modelSwitchResult));
		return modelSwitchResult;
	}
	
	public static ModelInsertResult modelInsert(ModelInsert modelInsert){
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		// init variable
		ModelInsertResult modelInsertResult = new ModelInsertResult();
		
		if(modelInsert != null&&attributeCheck.listNotNull_Zero(modelInsert.getModelBinaryList(),modelInsert.getModelFaceInfoList())
				&&attributeCheck.stringsNotNull(modelInsert.getListPath())){
			System.out.println("modelInsert="+new Gson().toJson(modelInsert));
			// init func
			final TxtUtil txtUtil = new TxtUtil();
			// init variable
			final int modelCount = modelInsert.getModelBinaryList().size();
			final List<String> dataList = new ArrayList<>();
			final String modelInsertLog_path = modelInsert.getEnginePath()+"\\Log.ModelInsert.eGroup";
			final File modelInsertLog_file = new File(modelInsertLog_path);
			
			for(int i=0;i<modelCount;i++){
				dataList.add(modelInsert.getModelBinaryList().get(i)+"	"+modelInsert.getModelFaceInfoList().get(i));
			}
			txtUtil.create(modelInsert.getListPath(), dataList);

			modelInsertResult = modelInsert_check(modelInsert);	
			modelInsertLog_file.delete();
		}
		return modelInsertResult;
	}
	
	private static ModelInsertResult modelInsert_check(ModelInsert modelInsert){
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		final TxtUtil txtUtil = new TxtUtil();
		// init variable
		final String modelInsertLog_path = modelInsert.getEnginePath()+"\\Log.ModelInsert.eGroup";
		final File modelInsertLog_file = new File(modelInsertLog_path);
		long time1 = System.currentTimeMillis(), time2;
		ModelInsertResult modelInsertResult = new ModelInsertResult();	
		int waitCount = 0;
		// Check whether modelAppend log exist, wait 1.5 sec
		try {
			while(true){
				if(modelInsertLog_file.exists()||waitCount==3){
					break;
				}
				waitCount++;
				Thread.sleep(300);
			}			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> modelInsertLineList = txtUtil.read_lineList(modelInsertLog_path);
		if(attributeCheck.listNotNull_Zero(modelInsertLineList)){
			// init variable
			List<ModelInsertInfo> modelInsertInfoList = new ArrayList<>();
			ModelInsertInfo modelInsertInfo = new ModelInsertInfo();
			
			for(String modelInsertLine : modelInsertLineList){
				String modelInsertArray[] = modelInsertLine.split("\\t");
				System.out.println("modelInsertArray="+new Gson().toJson(modelInsertArray));
				switch (modelInsertArray.length) {			
				case 5:
					System.out.println("modelInsertArray[2]="+modelInsertArray[1]);
					if(modelInsertArray[2].equals("InsertFace")){
						modelInsertInfo.setDatetimeString(modelInsertArray[0]);
						modelInsertInfo.setInsertModelStatus(modelInsertArray[1]);
						modelInsertInfo.setInsertPeopleCount(Integer.valueOf(modelInsertArray[3].substring(0, modelInsertArray[3].indexOf("/")).trim()));
						modelInsertInfo.setInsertFacesCount(Integer.valueOf(modelInsertArray[3].substring(modelInsertArray[3].indexOf("/")+1, modelInsertArray[3].indexOf(" of faces/people were inserted")).trim()));
						modelInsertInfo.setCurrDBFaceCout(Integer.valueOf(modelInsertArray[4].substring(modelInsertArray[4].indexOf("CurrPeopleCount=")+16, modelInsertArray[4].indexOf(" CurrFaceCount=")).trim()));
						modelInsertInfo.setCurrDBPeopleCount(Integer.valueOf(modelInsertArray[4].substring(modelInsertArray[4].indexOf("CurrFaceCount=")+14, modelInsertArray[4].length()).trim()));
					}
					break;
				case 4:
					if(modelInsertArray[3].startsWith("Overall insert time: ")){
						System.out.println(modelInsertArray[3].substring( modelInsertArray[3].indexOf("Overall insert time: "), modelInsertArray[3].indexOf(" sec. ")).trim());
						modelInsertInfo.setInsertProcessTime(modelInsertArray[3].substring( modelInsertArray[3].indexOf("Overall insert time: "), modelInsertArray[3].indexOf(" sec. ")).trim());
					}		
					modelInsertInfoList.add(modelInsertInfo);	
					modelInsertInfo = new ModelInsertInfo();		
					break;
				default:
					break;
				}
				
			}
			modelInsertResult.setModelInsertInfoList(modelInsertInfoList);
			modelInsertLog_file.delete();
			System.out.println("modelInsertResult="+new Gson().toJson(modelInsertResult));
			time2 = System.currentTimeMillis();
			System.out.println("read modelAppend log：" + (time2-time1) + "ms");
		}
		return modelInsertResult;
	}
}
