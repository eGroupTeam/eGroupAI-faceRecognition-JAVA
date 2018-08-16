package com.egroupai.engine.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyUtil {
	/**
	 * Copy file method using classic approach by using streams.
	 * @author Daniel
	 *
	 * @param source
	 * @param dest
	 * @return
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
	 * Copy folder
	 * @author Daniel
	 *
	 * @param source
	 * @param dest
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
}
