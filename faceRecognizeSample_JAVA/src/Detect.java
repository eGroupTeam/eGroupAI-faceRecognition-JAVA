import sample.util.faceRecognizeUtil;

public class Detect extends faceRecognizeUtil{
	public static void main(String args[]) {		
		// init variable
		final String enginePath = "C:/Users/Daniel/Desktop/Face Engine/RetrieveFace_ByCam_Release";
		final String modelName = "eGroup.model";
		
		// detectFace won't output frame and face
		detectFace(enginePath,modelName,0.75,2,720,100,0,true);
	}
}
