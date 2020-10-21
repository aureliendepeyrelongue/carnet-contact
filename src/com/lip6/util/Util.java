package com.lip6.util;

import org.apache.commons.lang3.StringUtils;

public class Util {
	
	public static String cleanString(String str) {
		return StringUtils.strip(str.trim().toLowerCase());
	}
	

}
