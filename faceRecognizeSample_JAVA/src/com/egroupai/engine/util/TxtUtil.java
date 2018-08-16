package com.egroupai.engine.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/** 
* @author 作者 Daniel
* @date 2018年8月16日 上午9:50:45 
* @version 
* @description:
*/
public class TxtUtil {
	public void create(String filePath,List<String> dataList){
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		if(attributeCheck.listNotNull_Zero(dataList)){
			final Path file = Paths.get(filePath);
			try {
				Files.write(file, dataList, Charset.forName("Big5"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}		
	}
}
