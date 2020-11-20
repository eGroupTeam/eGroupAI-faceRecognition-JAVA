package com.egroupai.engine.util;

import java.io.File;

/** 
* @author 作者 eGroupAI
* @date 2019年4月26日 上午9:17:43 
* @version 
* @description:
*/
/** 
* @author daniel
* @date 2019年4月26日 上午9:17:43 
* @version 
* @description:
*/
public class FolderUtil {
	public boolean checkEmpty(String folderPath){		
		final File file = new File(folderPath);
	    if (file.isDirectory()&&file.list().length > 0) {
        	System.out.println("Directory '" + file.getAbsolutePath() + "' is not empty!");
        	return false;
	    }
		return true;
    }
	
	public boolean clean(String folderPath){		
		final File file = new File(folderPath);
	    if (file.isDirectory()&&file.list().length > 0) {
        	System.out.println("Directory '" + file.getAbsolutePath() + "' is not empty!");
        	return false;
	    }
		return true;
    }
}
