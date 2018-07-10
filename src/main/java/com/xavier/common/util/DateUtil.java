package com.xavier.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static final String YMD_DASH_WITH_SECONED_12 = "yyyy-MM-dd hh:mm:ss a";
	public static final String YMD_DASH_WITH_SECONED_24 = "yyyy-MM-dd HH:mm:ss";

	public static final String YMD_WITH_SECONED_24 = "yyyyMMddHHmmss";

	public static String getNowDateString(String format){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
	}
}
