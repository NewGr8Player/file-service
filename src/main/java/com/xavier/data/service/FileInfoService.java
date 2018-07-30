package com.xavier.data.service;

import com.xavier.data.bean.FileInfo;

/**
 * 文件信息Service
 *
 * @author NewGr8Player
 */
public interface FileInfoService {
	/**
	 * 保存文件信息
	 *
	 * @param fileInfo
	 * @return
	 */
	Integer save(FileInfo fileInfo);
}
