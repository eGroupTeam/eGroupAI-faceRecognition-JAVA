package com.egroupai.engine.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
/**
 * @author 作者 eGroupAI
 * @date 2018年8月16日 上午9:50:45
 * @version
 * @description:
 */
public class TxtUtil{
	
	public boolean create(String filePath, List<String> dataList) {
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		// init variable
		final File file = new File(filePath);
		
		if (attributeCheck.listNotNull_Zero(dataList)) {
			System.out.println("filePath="+filePath);
			final Path path = Paths.get(filePath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			try {
				Files.write(path, dataList, Charset.forName("Big5"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File getFile = new File(filePath);
				try {
				while(getFile.exists()){
					if(getFile.exists()){
						 System.out.println("filePath="+filePath+" - exist");
						return true;
					}else{
						 System.out.println("filePath="+filePath+" - not exist");
							Thread.sleep(100);
					}
				}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else{
			return false;
		}
		return false;
	}
	
	/**
	 * Read the txt and return content
	 * @author Daniel
	 *
	 * @param txtPath
	 * @return
	 */
	public String read_content(String txtPath) {	
		// inti func 
		final AttributeCheck attributeCheck = new AttributeCheck();
		// init variable
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(txtPath);
		} catch (FileNotFoundException e) {
		}
		bufferedReader = new BufferedReader(fileReader);
		final StringBuilder jsonContent = new StringBuilder();
		String line = null;
		try {
			// Read line
			line = bufferedReader.readLine();
			while (attributeCheck.stringsNotNull(line)) {
				jsonContent.append(line);
				line = bufferedReader.readLine();
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(bufferedReader!=null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		return jsonContent.toString();		
	}

//	/**
//	 * Read the txt and return content
//	 * @author Daniel
//	 *
//	 * @param txtPath
//	 * @return
//	 */
//	public String read_content(String txtPath) {		
//		List<String> lines = null;
//		String contents = "";
//		try {
//			lines = Files.readAllLines(Paths.get(txtPath));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			MOTION_FRAME_THREAD.interrupt();
//		}
//		StringBuilder stringBuilder = new StringBuilder();
//		if(lines!=null){
//			for (String line : lines) {
//				if (stringBuilder.length() > 0) {
//					stringBuilder.append("\n");
//				}
//				stringBuilder.append(line);
//			}
//			contents = stringBuilder.toString();
//		}
//		return contents;		
//	}
	
	/**
	 * Read the txt and return line content list
	 * @author Daniel
	 *
	 * @param txtPath
	 * @return
	 */
	public List<String> read_lineList(String txtPath) {
		File file = new File(txtPath);
		System.out.println("txtPath="+txtPath+",file.exists()="+file.exists());
		if(file.exists()){
			List<String> lineList = null;
			try {
				lineList = Files.readAllLines(Paths.get(txtPath), StandardCharsets.ISO_8859_1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lineList;
		}
		return null;
	}
}
