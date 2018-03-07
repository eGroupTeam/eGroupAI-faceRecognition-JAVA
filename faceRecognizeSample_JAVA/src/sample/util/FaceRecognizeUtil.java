package sample.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import sample.entity.Face;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Face Recognize Engin
 * 
 * @author Daniel
 *
 */
public class FaceRecognizeUtil extends CmdUtil {	
	// init desktop path
//	static final String enginPath = "C:/Users/Daniel/Desktop/RetrieveFace_ByCam_Release_20171221";
//	static final String modelPath = "C:/Users/Daniel/Desktop/FaceModel/20171221_";
	// init server path
//	static final String enginPath = "C:/Users/Administrator/Desktop/RetrieveFace_ByCam_Release_20171221";
//	static final String modelPath = "C:/Users/Administrator/Desktop/FaceModel/20171221_";
	

	/**
	 * get the faceRetrieve result by id
	 * @param loginID
	 * @param startIndex
	 * @return
	 */
	public List<Face> getResult(String MODEL_PATH,int startIndex) {
		// init func
		final Gson gson = new Gson();
		final CopyUtil copyUtil = new CopyUtil();

		// init variable
		final Type faceListType = new TypeToken<ArrayList<Face>>() {}.getType();
		List<Face> faceList = new ArrayList<Face>();
		// final int startIndex = Integer.valueOf(startIndexString);

		// 讀取辨識結果JSON
		final StringBuilder jsonFileName = new StringBuilder(MODEL_PATH + "/output1.json");
		final File source = new File(MODEL_PATH.toString() + "/output.json");
		final File dest = new File(jsonFileName.toString());
		if(source.exists()) {
			try {
				copyUtil.copyFile(source, dest);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			final File jsonFile = new File(jsonFileName.toString());
			FileReader fileReader = null;
			BufferedReader bufferedReader = null;

			// 如果有產生JSON檔
			if (jsonFile.exists()) {
				try {
					fileReader = new FileReader(jsonFileName.toString());
				} catch (FileNotFoundException e) {
				}
				// 如果有讀到JSON檔
				if (fileReader != null) {
					bufferedReader = new BufferedReader(fileReader);
					// 讀取JSON檔案
					final StringBuilder jsonContent = new StringBuilder();
					String line;
					try {
						// 讀入JSON資料
						line = bufferedReader.readLine();
						while (line != null) {
							jsonContent.append(line + "\n");
							line = bufferedReader.readLine();
						}
						// 如果JSON有資料
						if (jsonContent.toString() != null) {
							// 取得最後一筆完整的辨識資料
							int endIndex = jsonContent.lastIndexOf("}\n\t,");
							String json;
							// 重組JSON格式
							if (endIndex != -1 && startIndex != endIndex && startIndex < endIndex) {
								if (startIndex > 0) {
									json = "[" + jsonContent.toString().substring(startIndex + 2, endIndex) + "}]";
								} else {
									json = jsonContent.toString().substring(startIndex, endIndex) + "}]";
								}
								faceList = gson.fromJson(json, faceListType);
								faceList.get(faceList.size() - 1).setEndIndex(endIndex + 2);
							}
						}
					} catch (IOException e) {
					} finally {
						try {
							bufferedReader.close();
						} catch (IOException e) {
						}
					}
				}
			}
			System.out.println("faceList list =" + gson.toJson(faceList));
			if (faceList.size() > 0) {
				System.out.println(faceList.get(faceList.size() - 1).getEndIndex());
			}
		}		
		return faceList;
	}
	
	public boolean generateTxt(String MODEL_PATH,String loginID,List<String> dataList) {
		final File modelFile = new File(MODEL_PATH + loginID + "/video");
		if(!modelFile.exists()) {
			modelFile.mkdirs();
		}
		final Path file = Paths.get(MODEL_PATH + loginID + "/video/list.txt");
		System.out.println(MODEL_PATH + loginID + "/video/list.txt");
		try {
			Files.write(file, dataList, Charset.forName("BIG5"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;	
	}
}
