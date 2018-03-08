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
public class faceRecognizeUtil extends CmdUtil {	
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
		
		//3.retriveFace will output frame and face
		retriveFace(enginePath,modelName,0.75,2,720,100,0,false);
		
		//4.detectFace won't output frame and face
		detectFace(enginePath,modelName,0.75,2,720,100,0,false);
		
		//5.terminateRetrieveProcess
		terminateRetrieveProcess("RetrieveFace_ByCam.exe");
		
		//6.Get recognize Result
		getResult(modelName,0);
	}
	
	/**
	 * Train face
	 * You can use list.txt that contain face's Path name face's name to train the model 
	 * @param RetrieveFace_ByCamPath 
	 * @param modelName
	 */
	protected static void trainFace(String retrieveFace_ByCamPath,String modelName) {
		// init variable
		final File file = new File(retrieveFace_ByCamPath+"/eGroup/"+modelName);
		StringBuilder cmd = null;
		if(file.exists()&&file.getTotalSpace()>0) {
			cmd = new StringBuilder("cd "+retrieveFace_ByCamPath+" && C: && "
				+ "TrainFace -a list.txt eGroup/"+modelName+" eGroup/"+modelName);
		}else {
			cmd = new StringBuilder("cd "+retrieveFace_ByCamPath+" && C: && "
					+ "TrainFace list.txt eGroup/"+modelName+" eGroup/"+modelName);
		}
		if(cmd!=null) {
			final List<String> commandList = new ArrayList<String>();
			commandList.add("cmd");
			commandList.add("/c");
			commandList.add(cmd.toString().replace("/", "\\"));
			System.out.println("commandList="+commandList);
			cmdProcessBuilder(commandList);
		}		
	}
	
	/**
	 * Retrieve face , will retrieve frame or face to folder you set
	 * You can start the recognize engine , if you set the parameter 
	 * -f engine will retrieve frame output to folder 
	 * -p engine will retrieve face output to folder
	 * your ap can get the frame's and face's path in the ouput.json 
	 * @param RetrieveFace_ByCamPath - your engine path
	 * @param modelName - your model name
	 * @param threshold - the similarity threshold 
	 * @param rate - retrieve frame rate 1~30/sec, but detect's and recognize's rate is depends on your PC performance, 
	 * e.g Intel i5 can detect and recognize 1~2/sec , i7 can detect and recognize 2~3/sec
	 * @param resolution - 360p~1080p
	 * @param minFace - face's min pixel in the frame resolution, min up to 100pixel 
	 * @param inputSource - you can use webcam,ipcam,video
	 * @param showOpenC - you can show and picture face in the windows by opencv but will reduce efficiency 10% ~ 20%
	 */
	protected static void retriveFace(String RetrieveFace_ByCamPath,String modelName,Double threshold,Integer rate
			,Integer resolution,Integer minFace,Integer inputSource,boolean showOpenCv) {
		// init variable
		final StringBuilder cmd = new StringBuilder("cd "+RetrieveFace_ByCamPath+" && C: && "
			+ "RetrieveFace_ByCam.exe -k 10 -t "+threshold+" -r "+rate+" -s "+resolution + " -m "+minFace 
			+ " "+(showOpenCv==true?"":"-h")+ " -c "+inputSource
			+" -f output_frame -p output_face eGroup/"+modelName+".binary eGroup/"+modelName+".faceInfor output.json");
		if(cmd!=null) {
			final List<String> commandList = new ArrayList<String>();
			commandList.add("cmd");
			commandList.add("/c");
			commandList.add(cmd.toString().replace("/", "\\"));
			System.out.println("commandList="+commandList);
			cmdProcessBuilder(commandList);
		}		
	}
	
	/**
	 * Detect face , won't retrieve frame or face to folder you set
	 * You can start the recognize engine , if you set the parameter 
	 * -f engine will retrieve frame output to folder 
	 * -p engine will retrieve face output to folder
	 * your ap can get the frame's and face's path in the ouput.json 
	 * @param RetrieveFace_ByCamPath - your engine path
	 * @param modelName - your model name
	 * @param threshold - the similarity threshold 
	 * @param rate - retrieve frame rate 1~30/sec, but detect's and recognize's rate is depends on your PC performance, 
	 * e.g Intel i5 can detect and recognize 1~2/sec , i7 can detect and recognize 2~3/sec
	 * @param resolution - 360p~1080p
	 * @param minFace - face's min pixel in the frame resolution, min up to 100pixel 
	 * @param inputSource - you can use webcam,ipcam,video
	 * @param showOpenC - you can show and picture face in the windows by opencv but will reduce efficiency 10% ~ 20%
	 */
	protected static void detectFace(String RetrieveFace_ByCamPath,String modelName,Double threshold,Integer rate
			,Integer resolution,Integer minFace,Integer inputSource,boolean showOpenCv) {
		// init variable
		final StringBuilder cmd = new StringBuilder("cd "+RetrieveFace_ByCamPath+" && C: && "
			+ "RetrieveFace_ByCam.exe -k 10 -t "+ threshold +" -r "+rate +" -s "+resolution + " -m "+minFace 
			+ " "+(showOpenCv==true?"":"-h")+" -c "+inputSource+" eGroup/"+modelName+".binary eGroup/"+modelName+".faceInfor output.json");
		
		if(cmd!=null) {
			final List<String> commandList = new ArrayList<String>();
			commandList.add("cmd");
			commandList.add("/c");
			commandList.add(cmd.toString().replace("/", "\\"));
			System.out.println("commandList="+commandList);
			cmdProcessBuilder(commandList);
		}		
	}
	
	/**
	 * Terminate Process
	 * @param processName you can see by windsows's task
	 */
	protected static void terminateRetrieveProcess(String processName) {
		cmdProcessTerminate(processName);		
		System.out.println("Terminate process="+processName);
	}
	
	
	/**
	 * Get the ouptput result
	 * you can set the func by while loop when engine start,then you can get the result from output.json 
	 * @param enginePath - engine path
	 * @param startIndex - the endIndex you get by previous list's last one endIndex
	 * @return
	 */
	protected static List<Face> getResult(String enginePath,int startIndex) {
		// init func
		final Gson gson = new Gson();
		final CopyUtil copyUtil = new CopyUtil();

		// init variable
		final Type faceListType = new TypeToken<ArrayList<Face>>() {}.getType();
		List<Face> faceList = new ArrayList<Face>();

		// 讀取辨識結果JSON
		final StringBuilder jsonFileName = new StringBuilder(enginePath + "/output1.json");
		final File source = new File(enginePath.toString() + "/output.json");
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
	
	/**
	 * Generate txt has train content
	 * @param photoPathList
	 * @param name
	 * @return
	 */
	protected static List<String> generateTrainContent(List<String> photoPathList,String name) {
		final List<String> conentList = new ArrayList<>();
		for(int i=0;i<photoPathList.size();i++) {
			conentList.add(photoPathList.get(i)+"\t"+name);
		}
		return conentList;
	}
	
	/**
	 * Generate Train's txt
	 * @param MODEL_PATH
	 * @param dataList
	 * @return if list.txt is generate
	 */
	protected static boolean generateTrainTxt(String enginePath,List<String> dataList) {
		// init variable
		final File modelFile = new File(enginePath);
		
		// check if model exists
		if(!modelFile.exists()) {
			// if model file not exists
			modelFile.mkdirs();
		}

		//write data to list.txt
		final Path listPath = Paths.get(enginePath+"/list.txt");	
		try {
			Files.write(listPath, dataList, Charset.forName("BIG5"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		// check if list.txt is generate
		final File listFile = new File(enginePath+"/list.txt");
		if(listFile.exists()) {
			return true;
		}
		return false;	
	}
	
	// Generate txt
	protected static void generateTxt(String txtPath,List<String> dataList) {
		Path path = Paths.get(txtPath);
		try {
			Files.write(path, dataList, Charset.forName("Big5"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
