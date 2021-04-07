package com.mbvan.mbvan.common.util;

import java.util.UUID;

public class IDGenerateUtil {

	//generate uuid without -
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}	
	
}
