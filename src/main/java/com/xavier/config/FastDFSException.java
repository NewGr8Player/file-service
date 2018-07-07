package com.xavier.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FastDFS 上传下载时可能出现的一些异常信息
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FastDFSException extends Exception {

	/**
	 * 错误码
	 */
	private String code;

	/**
	 * 错误消息
	 */
	private String message;

}
