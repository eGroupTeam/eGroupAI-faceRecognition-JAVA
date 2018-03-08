import java.util.ArrayList;
import java.util.List;

import sample.entity.Face;
import sample.util.faceRecognizeUtil;

public class GetResult extends faceRecognizeUtil{
	public static void main(String args[]) {			
		// init variable
		final String enginePath = "C:/Users/Daniel/Desktop/Face Engine/RetrieveFace_ByCam_Release";
		final List<Face> faceList = new ArrayList<>();
		List<Face> getFaceList = new ArrayList<>();
		int startIndex = 0;
		
		// Get recognize Result
		try {
			while(true) {
				getFaceList = getResult(enginePath,startIndex);
				if(getFaceList!=null&&getFaceList.size()>0) {
					faceList.addAll(getFaceList);
					startIndex = getFaceList.get(getFaceList.size()-1).getEndIndex();
				}
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
