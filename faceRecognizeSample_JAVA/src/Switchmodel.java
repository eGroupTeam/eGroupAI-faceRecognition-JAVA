import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sample.util.faceRecognizeUtil;

public class Switchmodel extends faceRecognizeUtil{

	// The model your faceRecognize used now
	private static String modelName_now = "eGroup2.Model";
	
	public static void main(String args[]) {		
		// init variable
		final String enginePath = "C:/Users/Daniel/Desktop/Face Engine/RetrieveFace_ByCam_Release";
		
		// switch your new model, e.g model1 is used now and you train the new model name model2 
		// you can switch model2 don't need to restart the faceRecognize engine
		switchModel(enginePath, modelName_now.equals("eGroup1.Model")?"eGroup2.Model":"eGroup1.Model");
	}
	
	private static void switchModel(String enginPath,String modelName) {
		// new the Singal_For_Model_Switch.txt has model you want to switch
		final List<String> dataList = new ArrayList<>();
		dataList.add("eGroup\\\\"+modelName+".binary");
		dataList.add("eGroup\\\\"+modelName+".faceInfor");
		generateTxt(enginPath+"/Singal_For_Model_Switch.txt",dataList);	
		// switch model , if faceRecognize detect this txt , it will switch by itself
		final File switchFile = new File(enginPath+"/Singal_For_Model_Switch.txt");
		while(true) {
			if(switchFile.exists()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				//更新為新的binary
				modelName_now = modelName;
				break;
			}
		}
	}
}
