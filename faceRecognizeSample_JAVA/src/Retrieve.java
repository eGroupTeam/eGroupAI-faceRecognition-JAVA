import sample.util.faceRecognizeUtil;

public class Retrieve extends faceRecognizeUtil{
	public static void main(String args[]) {		
		// init variable
		final String enginePath = "C:/Users/Daniel/Desktop/Face Engine/RetrieveFace_ByCam_Release";
		final String modelName = "eGroup.model";
		
		// retriveFace will output frame and face
		retriveFace(enginePath,modelName,0.75,2,720,100,0,true);
	}
}
