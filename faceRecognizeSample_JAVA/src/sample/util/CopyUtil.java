package sample.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyUtil {
	/***
	 * Copy file method using classic approach by using streams.
	 * 
	 * @param source file
	 * @param dest file
	 * @throws IOException
	 */
	public boolean copyFile(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			final byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
			System.out.println("File copied from " + source + "  to " + dest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}
		return false;
	}

	/**
	 * copy folder
	 * @param source folder be copy
	 * @param dest folder copy to
	 * @throws IOException
	 */
	public void copyFolder(File source, File dest) throws IOException {
		if (source.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdirs();
				System.out.println("Directory copied from " + source + "  to " + dest);
			}

			final String files[] = source.list();
			File srcFile = null;
			File destFile = null;
			for (String file : files) {
				/**
				 * construct the src and dest file structure by adding the current folder
				 */
				srcFile = new File(source, file);
				destFile = new File(dest, file);
				/** recursive copy the files or sub-folders */
				copyFolder(srcFile, destFile);
			}
		} else {
			copyFile(source, dest);
		}
	}
	
//	public static void main(String args[]) {
//		File file1 = new File("C:/Users/Daniel/Desktop/FaceModel/20171221_d7c4021c48ac4185a2ff91363ce7f5aa/video/output_face/12-28.6.1.jpg");
//		File file2 = new File("C:/Users/Daniel/Desktop/FaceModel/20171221_d7c4021c48ac4185a2ff91363ce7f5aa/copy/4c49fffb6daf4e598b75b86dc2a5e97edc3sOf8C.jpg");
//		File file3 = new File("C:/Users/Daniel/Desktop/FaceModel/20171221_d7c4021c48ac4185a2ff91363ce7f5aa/copy/");
//		if(!file3.exists()) {
//			file3.mkdirs();
//		}
//		CopyUtil copyUtil = new CopyUtil();
//		try {
//			copyUtil.copyFile(file1, file2);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
