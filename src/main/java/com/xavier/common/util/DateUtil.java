package com.xavier.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 * @author NewGr8Player
 */
public class DateUtil {

	public static final String YMD_DASH_WITH_SECONED_12 = "yyyy-MM-dd hh:mm:ss a";
	public static final String YMD_DASH_WITH_SECONED_24 = "yyyy-MM-dd HH:mm:ss";

	public static final String YMD_WITH_SECONED_24 = "yyyyMMddHHmmss";

	/**
	 * 获得指定格式的当前日期
	 */
	public static String getNowDateString(String format){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
	}
}
