package com.egroup.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;


/**
 * @author 作者 eGroupAI Team
 * @date 2018年8月16日 上午9:50:45
 * @version
 * @description:
 */
public class TxtUtil{
	private Logger LOGGER = LoggerFactory.getLogger(CmdUtil.class);
	
	public enum Charsets {
		BIG5("Big5"),
		UTF8("UTF-8"),
		ISO_8859_1("ISO_8859_1");

		private String value;

		Charsets(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	public boolean create(String filePath, List<String> dataList,Charsets charsets) {
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		if (attributeCheck.listNotEmpty(dataList)&&attributeCheck.stringsNotNull(filePath)) {
			// init variable
			final File file = new File(filePath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			// final Path path = Paths.get(filePath);
			final Path path = file.toPath();
			try {
				// Create file
				Files.write(path, dataList, Charset.forName(charsets.getValue()));
				
				// Check file exist
				while(file.exists()){
					if(file.exists()){
						return true;
					}else{
						Thread.sleep(10);
					}
				}
			} catch (IOException e) {
				LOGGER.error(new Gson().toJson(e));
			} catch (InterruptedException e) {
				LOGGER.error(new Gson().toJson(e));
				Thread.currentThread().interrupt();
			}			
		}
		return false;
	}
	
	public boolean create(String filePath, String content,Charsets charsets) {
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		if (attributeCheck.stringsNotNull(filePath,content)) {
			// init variable
			final File file = new File(filePath);
			
			// Check parent path is exist
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			
			try(FileOutputStream fileOutputStream = new FileOutputStream(file);
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,charsets.getValue())){
				outputStreamWriter.write(content);
				outputStreamWriter.flush();
				while(true){
					if(file.exists()&&file.length()>0){
						return true;
					}else{
						Thread.sleep(10);
					}
				}
			} catch (InterruptedException e) {
				LOGGER.error(new Gson().toJson(e));
				Thread.currentThread().interrupt();
			}	 catch (IOException e) {
				LOGGER.error(new Gson().toJson(e));
			}
		}
		return false;
	}
	
	public synchronized boolean createSingalForRecognition(String filePath, List<String> dataList) {
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		// init variable
		boolean flag = false;		
		if (attributeCheck.listNotEmpty(dataList)&&attributeCheck.stringsNotNull(filePath)) {
			// init variable
			final File file = new File(filePath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();				
			}
			try(BufferedWriter bufferedWriter = new BufferedWriter (new FileWriter (filePath));) {
				for (String line : dataList) {
					bufferedWriter.write(line + "\n");
				}	
			} catch (IOException e) {
				LOGGER.error(new Gson().toJson(e));
			}
			flag = true;
		}
		return flag;
	}

	/**
	 * Read the txt and return content
	 * @author eGroupAI Team
	 *
	 * @param txtPath
	 * @return
	 */
	public String read_content(String txtPath) {
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();		
		// init variable
		String contents = null;
		if(attributeCheck.stringsNotNull(txtPath)){
			// init variable
			final File txtFile = new File(txtPath);
			List<String> lines = null;
			if(txtFile.exists()){
				try {
					lines = Files.readAllLines(Paths.get(txtPath));
				} catch (IOException e) {
					LOGGER.error(new Gson().toJson(e));
				}
				if(attributeCheck.listNotEmpty(lines)){
					StringBuilder stringBuilder = new StringBuilder();
					for (String line : lines) {
						if (stringBuilder.length() > 0) {
							stringBuilder.append("\n");
						}
						stringBuilder.append(line);
					}
					contents = stringBuilder.toString();
				}
			}
			
		}		
		return contents;		
	}
	
	/**
	 * Read the txt and return line content list
	 * @author eGroupAI Team
	 *
	 * @param txtPath
	 * @return
	 */
	public List<String> read_lineList(String txtPath, Charsets charsets) {
		// init variable
		List<String> lineList = null;
		final File file = new File(txtPath);
		
		if(file.exists()){
			try {
				lineList = Files.readAllLines(Paths.get(txtPath), Charset.forName(charsets.getValue()));
			} catch (IOException e) {
				LOGGER.error(new Gson().toJson(e));
			}
		}
		return lineList;
	}
}
