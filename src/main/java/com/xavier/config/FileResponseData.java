package com.xavier.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.xavier.common.util.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 上传文件后的数据返回对象，便于前台获取数据.
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@NoArgsConstructor
public class FileResponseData {

	/**
	 * 返回状态编码
	 */
	@JsonInclude(Include.NON_NULL)
	private String code;

	/**
	 * 返回信息
	 */
	@JsonInclude(Include.NON_NULL)
	private String message;

	/**
	 * 成功标识
	 */
	@JsonInclude
	private boolean success = true;

	/**
	 * 文件路径
	 */
	@JsonInclude(Include.NON_NULL)
	private String filePath;

	/**
	 * 文件名称
	 */
	@JsonInclude(Include.NON_NULL)
	private String fileName;

	/**
	 * 文件类型
	 */
	@JsonInclude(Include.NON_NULL)
	private String fileType;

	/**
	 * 文件大小(单位:B)
	 */
	@JsonInclude(Include.NON_NULL)
	private Long fileSize;

	/**
	 * Http URL(仅供测试)
	 */
	@JsonInclude(Include.NON_NULL)
	private String httpUrl;

	/**
	 * Http URL
	 */
	@JsonInclude(Include.NON_NULL)
	private String respDate = DateUtil.getNowDateString(DateUtil.YMD_DASH_WITH_SECONED_24);

	public FileResponseData(boolean success) {
		this.success = success;
	}
}
