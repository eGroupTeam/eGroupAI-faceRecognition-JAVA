package com.egroupai.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.egroupai.yaml.entity.Initialization;
import com.google.gson.Gson;

import com.egroupai.engine.entity.TrainFace;
import com.egroupai.engine.scenario.entity.TimeRecord;
import com.egroupai.engine.util.YamlUtil;

public class BaseController{	
	static protected HashMap<String, List<TimeRecord>> TIMERECOREDHASHMAP = new HashMap<>();			// 每個人的辨識紀錄 
    static protected HashMap<String, Integer> AUTOASSORTHASHMAP = new HashMap<>();						
	static protected Initialization INITIALIZATION;	
    static protected Thread RECOGNITION_THREAD;
    static protected Thread GET_RESULT_THREAD;
    static protected Thread BATCH_TRAIN_THREAD;
    static protected Thread MONITOR_SYSTEM_THREAD;
    static protected Thread MOTION_FRAME_THREAD;
    static protected long RESPONSE_TIME = 500;  
    static protected String FORMAL_MODEL_PATH;
    static protected String BACKUP_MODEL_PATH;
    static protected String WAIT_APPEND_FOLDER_PATH;
    static protected String LOG_TRAINRESULT_PATH;
	
	protected static boolean init(){
		final YamlUtil yamlUtil = new YamlUtil();
    	INITIALIZATION =  yamlUtil.loadInitializationYaml("Yaml資料夾自訂路徑/Initialization.yaml");
    	System.out.println("INITIALIZATION="+new Gson().toJson(INITIALIZATION));
    	if(INITIALIZATION!=null){
    		// init variable
    	    FORMAL_MODEL_PATH = "formal.model";
    	    BACKUP_MODEL_PATH = "backup.model";
    	    WAIT_APPEND_FOLDER_PATH = "wait_append";
    	    // setting variable    	    
    		BACKUP_MODEL_PATH = INITIALIZATION.getModelPath()+BACKUP_MODEL_PATH;
    		FORMAL_MODEL_PATH = INITIALIZATION.getModelPath()+FORMAL_MODEL_PATH;
        	System.out.println("**FORMAL_MODEL_PATH**="+FORMAL_MODEL_PATH);
    		WAIT_APPEND_FOLDER_PATH = INITIALIZATION.getModelPath()+WAIT_APPEND_FOLDER_PATH; 
    		TIMERECOREDHASHMAP = new HashMap<>();	// 每個人的辨識紀錄 Initialization   
    		LOG_TRAINRESULT_PATH = INITIALIZATION.getEnginePath()+"\\Log.TrainResult"+INITIALIZATION.getEngineType().toUpperCase()+".eGroup";
    		// Check folder whether exist
    		final File ouput_face_file = new File(INITIALIZATION.getOutputFacePath());
    		final File ouput_frame_file = new File(INITIALIZATION.getOutputFramePath());
    		final File batch_train_file = new File(INITIALIZATION.getBatchTrainPath());
    		final File stream_file = new File(INITIALIZATION.getOutputMotionFramePath());
    		if(!ouput_face_file.exists()){
    			ouput_face_file.mkdirs();
    		}
    		if(!ouput_frame_file.exists()){
    			ouput_frame_file.mkdirs();
    		}
    		if(!batch_train_file.exists()){
    			batch_train_file.mkdirs();
    		}
    		if(!stream_file.exists()){
    			stream_file.mkdirs();
    		}
    		System.out.println("FORMAL_MODEL_PATH="+FORMAL_MODEL_PATH);
    		
    		return true;
    	}
		return false;
    	
	}
	
	
}
