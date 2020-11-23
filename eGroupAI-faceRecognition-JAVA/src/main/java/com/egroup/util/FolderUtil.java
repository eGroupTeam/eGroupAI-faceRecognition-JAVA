
package com.egroup.util;

import java.io.File;

/** 
* @author 作者 eGroupAI Team
* @date 2019年4月26日 上午9:17:43 
* @version 
* @description:
*/
public class FolderUtil {
	public boolean checkEmpty(String folderPath){		
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		// init variable 
		boolean flag =true;		
		if(attributeCheck.stringsNotNull(folderPath)){
			final File file = new File(folderPath);
		    if (file.exists()&&file.isDirectory()&&file.list().length > 0) {
	        	flag = false;
		    }
		}		
		return flag;
    }
	
	public boolean clean(String folderPath){	
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		// init variable 
		boolean flag =false;		
		if(attributeCheck.stringsNotNull(folderPath)){
			final File file = new File(folderPath);
		    if (file.exists()&&file.isDirectory()&&file.list().length > 0) {
	        	flag = true;
		    }
		}		
		return flag;
    }
}
