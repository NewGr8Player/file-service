package com.xavier.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 压缩/解压
 *
 * @author NewGr8Player
 */
public class ZipUtil {

	/**
	 * 压缩字符串
	 *
	 * @param str 压缩的字符串
	 * @return 压缩后的字符串
	 */
	public static String compress(String str) {

		if (StringUtils.isBlank(str)) {
			return str;
		}

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
		     GZIPOutputStream os = new GZIPOutputStream(bos)) {
			os.write(str.getBytes());
			byte[] bs = bos.toByteArray();
			return new String(bs, "ISO-8859-1");
		} catch (IOException e) {
			e.printStackTrace();
			return str;
		}
	}

	/**
	 * 解压缩字符串
	 *
	 * @param str 解压缩的字符串
	 * @return 解压后的字符串
	 */
	public static String decompress(String str) {

		if (StringUtils.isBlank(str)) {
			return str;
		}

		try (ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
		     ByteArrayOutputStream bos = new ByteArrayOutputStream();
		     GZIPInputStream is = new GZIPInputStream(bis)) {
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			return new String(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
			return str;
		}

	}

}
