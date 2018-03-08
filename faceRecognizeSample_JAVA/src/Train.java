import java.util.ArrayList;
import java.util.List;

import sample.util.faceRecognizeUtil;

public class Train extends faceRecognizeUtil{
	
	public static void main(String args[]) {		
		// init variable
		final String enginePath = "C:/Users/Daniel/Desktop/Face Engine/RetrieveFace_ByCam_Release";
		final String modelName = "eGroup.model";		
		
		//1. Generate list.txt to training
		List<String> photoList = new ArrayList<>();
		photoList.add(enginePath+"/output_face/3-6.1.0.jpg");
		photoList.add(enginePath+"/output_face/3-6.2.0.jpg");
		photoList.add(enginePath+"/output_face/3-6.3.0.jpg");
		photoList.add(enginePath+"/output_face/3-6.4.0.jpg");
		photoList.add(enginePath+"/output_face/3-7.1.0.jpg");
		photoList.add(enginePath+"/output_face/3-7.2.0.jpg");
		photoList.add(enginePath+"/output_face/3-7.3.0.jpg");
		List<String> dateList = generateTrainContent(photoList, "daniel");
		generateTrainTxt(enginePath,dateList);
		
		//2.trainFace
		trainFace(enginePath, modelName);	
	}	
}
