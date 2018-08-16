package com.egroupai.engine.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.egroupai.engine.entity.ModelMerge;
import com.egroupai.engine.entity.ModelSwitch;
import com.egroupai.engine.entity.RetrieveFace;
import com.egroupai.engine.entity.TrainFace;
import com.egroupai.engine.util.AttributeCheck;
import com.egroupai.engine.util.CmdUtil;
import com.egroupai.engine.util.TxtUtil;

/**
 * EngineControl Func
* @author 作者 Daniel
* @date 2018年8月10日 上午10:54:20 
* @version 
* @description:
 */
public class EngineFunc{
	static protected String ENGINEPATH = "C:/Users/eGroup/Desktop/Engine";
	public static void main(String args[]){
		// RetrieveFace
		RetrieveFace retrieveFace = new RetrieveFace();
		retrieveFace.setThreshold(0.7);
		retrieveFace.setHideMainWindow(false);
		retrieveFace.setResolution("720p");
		retrieveFace.setOutputFacePath("outputFace");
		retrieveFace.setOutputFramePath("outputFrame");
		retrieveFace.setCam("0");
		retrieveFace.setMinimumFaceSize(100);
		retrieveFace.setThreshold(0.7);
		retrieveFace.setTrainedBinaryPath("eGroup\\eGroup.Model.binary");
		retrieveFace.setTrainedFaceInfoPath("eGroup\\eGroup.Model.faceInfor");
		retrieveFace.setJsonPath("output");
		retrieveFace(retrieveFace);
		
//		// TrainFace
//		TrainFace trainFace = new TrainFace();
//		trainFace.setModelExist(false);
//		trainFace.setTrainListPath("list.txt");
//		trainFace.setModelPath("eGroup5\\eGroup.Model");
//		trainFace(trainFace);
//		
//		// ModelMerge
//		ModelMerge modelMerge = new ModelMerge();
//		modelMerge.setListPath("ModelList.egroup.List");
//		modelMerge.setTrainedBinaryPath("eGroup\\eGroup_merged.binary");
//		modelMerge.setTrainedFaceInfoPath("eGroup\\eGroup_merged.faceInfor");
//		modelMerge(modelMerge);
		
//		// ModelSwitch
//		ModelSwitch modelSwitch = new ModelSwitch();
//		modelSwitch.setNewModelBinaryPath(ENGINEPATH+"/eGroup5/eGroup.Model.binary");
//		modelSwitch.setNewModelFaceInfoPath(ENGINEPATH+"/eGroup5/eGroup.Model.faceInfor");
//		modelSwitch.setSwitchFilePath(ENGINEPATH+"/Singal_For_Model_Switch.txt");
//		modelSwitch(modelSwitch);
	}
		
	private static boolean trainFace(TrainFace trainFace){		
		boolean flag = false;
		// init func 
		trainFace.generateCli();
		if(trainFace.getCommandList()!=null){
			final CmdUtil cmdUtil = new CmdUtil();
			flag = cmdUtil.cmdProcessBuilder(trainFace.getCommandList());				
		}
		return flag;
	}
	
	private static boolean retrieveFace(RetrieveFace retrieveFace){		
		boolean flag = false;
		// init func 
		retrieveFace.generateCli();
		if(retrieveFace.getCommandList()!=null){
			final CmdUtil cmdUtil = new CmdUtil();
			flag = cmdUtil.cmdProcessBuilder(retrieveFace.getCommandList());				
		}
		return flag;
	}
	
	private static boolean modelMerge(ModelMerge modelMerge){
		boolean flag = false;
		// init func 
		modelMerge.generateCli();
		if(modelMerge.getCommandList()!=null){
			final CmdUtil cmdUtil = new CmdUtil();
			flag = cmdUtil.cmdProcessBuilder(modelMerge.getCommandList());				
		}
		return flag;
	}
	
	private static void modelSwitch(ModelSwitch modelSwitch){
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		
		// init variable
		final File newModelBinary = new File(modelSwitch.getNewModelBinaryPath());
		final File newModelFaceInfo = new File(modelSwitch.getNewModelFaceInfoPath());
		
		// Check Model Files 
		if(newModelBinary.exists()&&newModelFaceInfo.exists()&&attributeCheck.stringsNotNull(modelSwitch.getSwitchFilePath())) {			
			// Model 
			final List<String> dataList = new ArrayList<>();
			dataList.add(modelSwitch.getNewModelBinaryPath());
			dataList.add(modelSwitch.getNewModelFaceInfoPath());
			
			// init func
			final TxtUtil txtUtil = new TxtUtil();
			txtUtil.create(modelSwitch.getSwitchFilePath(), dataList);
		}	
	}
}
