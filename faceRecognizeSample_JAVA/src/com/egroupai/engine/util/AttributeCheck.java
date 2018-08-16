package com.egroupai.engine.util;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Attribute verify 
* @author 作者 Daniel
* @date 2018年6月15日 下午2:22:26 
* @version 
* @description:
 */
public class AttributeCheck {

	/**
	 * Verify that the object not null
	 * @param objects
	 * @return
	 */
	public boolean objectNotNull(Object... objects) {
		final int size = objects.length;
		for (int i = 0; i < size; i++) {
			if (objects[i] == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify that the string not 
	 * @param strings
	 * @return
	 */
	public boolean stringsNotNull(String... strings) {
		final int size = strings.length;
		for (int i = 0; i < size; i++) {
			if (strings[i] == null || strings[i].equals("") || strings[i].equals("null")
					|| strings[i].equals("undefined") || strings[i].equals("[]")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify that the string is uppercase
	 * @param strings
	 * @return
	 */
	public boolean isUppercase(String... strings) {
		final int size = strings.length;
		for (int i = 0; i < size; i++) {
			if (!strings[i].matches("[A-Z]+")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify that String is a number
	 * @param strings
	 * @return
	 */
	public boolean isInteger(String... strings) {
		final Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		final int size = strings.length;
		for (int i = 0; i < size; i++) {
			if (!pattern.matcher(strings[i]).matches()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isDouble(String... strings) {		
		final int size = strings.length;
		for (int i = 0; i < size; i++) {
			try {
		        Double.valueOf(strings[i]);
		    } catch (NumberFormatException numberFormatException) {
		        return false;
		    }
		}
		return true;
	}
	
	/**
	 * Verify that Integer not null or 0
	 * @param integers
	 * @return
	 */
	public boolean integerNotNull_Zero(Integer... integers) {
		final int size = integers.length;
		for (int i = 0; i < size; i++) {
			if (integers[i]==null&&integers[i]==0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Verify that Integer not null or 0
	 * @author Daniel
	 * @param lists 
	 * @return
	 */
	public boolean listNotNull_Zero(List<?>... lists) {
		final int size = lists.length;
		for (int i = 0; i < size; i++) {
			if (lists[i]==null||(lists[i]!=null&&lists[i].size()==0)) {
				return false;
			}
		}
		return true;
	}
}
