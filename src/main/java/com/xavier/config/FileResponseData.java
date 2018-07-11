package com.xavier.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.xavier.common.util.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 上传文件后的数据返回对象，便于前台获取数据.
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
	 * 文件所在分组
	 */
	@JsonInclude(Include.NON_NULL)
	private String fileGroup;

	/**
	 * 文件路径
	 */
	@JsonInclude(Include.NON_NULL)
	private String filePath;

	/**
	 * 完整文件路径
	 */
	@JsonInclude(Include.NON_NULL)
	private String fullPath;

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
	 * 文件类型
	 */
	@JsonInclude(Include.NON_NULL)
	private Long fileSize;

	/**
	 * Http URL
	 */
	@JsonInclude(Include.NON_NULL)
	private String httpUrl;

	/**
	 * Http URL
	 */
	@JsonInclude(Include.NON_NULL)
	private String date = DateUtil.getNowDateString(DateUtil.YMD_DASH_WITH_SECONED_24);

	/**
	 * Http Token
	 */
	@JsonInclude(Include.NON_NULL)
	private String token;

	public FileResponseData(boolean success) {
		this.success = success;
	}
}
