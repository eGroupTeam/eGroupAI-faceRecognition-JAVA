package com.egroup.util;

public class FileUtil {
	private StringBuilder originalName;
	private StringBuilder fileType;
	private StringBuilder fileName;
	private StringBuilder lowerCaseName;
	
	public FileUtil(){}
	
	public FileUtil(StringBuilder originalName) {
		super();
		this.originalName = originalName;
		this.fileType = new StringBuilder(originalName.substring(originalName.lastIndexOf(".")+1).toLowerCase());
		this.fileName = new StringBuilder(originalName.substring(0,originalName.lastIndexOf(".")));
		this.lowerCaseName = new StringBuilder(fileName+"."+fileType);
	}

	public StringBuilder getOriginalName() {
		return originalName;
	}

	public void setOriginalName(StringBuilder originalName) {
		this.originalName = originalName;
		this.fileType = new StringBuilder(originalName.substring(originalName.lastIndexOf(".")+1).toLowerCase());
		this.fileName = new StringBuilder(originalName.substring(0,originalName.lastIndexOf(".")));
		this.lowerCaseName = new StringBuilder(fileName+"."+fileType);
	}

	public StringBuilder getFileType() {
		return fileType;
	}

	public void setFileType(StringBuilder fileType) {
		this.fileType = fileType;
	}

	public StringBuilder getFileName() {
		return fileName;
	}

	public void setFileName(StringBuilder fileName) {
		this.fileName = fileName;
	}

	public StringBuilder getLowerCaseName() {
		return lowerCaseName;
	}

	public void setLowerCaseName(StringBuilder lowerCaseName) {
		this.lowerCaseName = lowerCaseName;
	}
}