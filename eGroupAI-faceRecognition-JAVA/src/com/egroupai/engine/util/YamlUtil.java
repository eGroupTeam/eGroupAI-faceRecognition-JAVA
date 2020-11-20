package com.egroupai.engine.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.yaml.snakeyaml.Yaml;

import com.egroupai.yaml.entity.Initialization;
import com.google.gson.Gson;

/** 
* @author 作者 Daniel
* @date 2018年8月30日 上午11:06:17 
* @version 
* @description:
*/
public class YamlUtil {
	/**
	 * 
	 * @param filePath file to load
	 * @return
	 */
	public Initialization loadInitializationYaml(String filePath){
		// init variable
		final Yaml yaml = new Yaml();
		final File initialFile = new File(filePath);
		Initialization initializeation = null;
		
		if(initialFile.exists()){
			try {
				initializeation = yaml.loadAs(new FileInputStream(initialFile), Initialization.class);
				System.out.println("initializeation="+new Gson().toJson(initializeation));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
		return initializeation;		
	}
}
