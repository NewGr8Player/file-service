package com.xavier.data.service.impl;

import com.xavier.data.bean.FileInfo;
import com.xavier.data.dao.FileInfoDao;
import com.xavier.data.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件信息Service实现
 *
 * @author NewGr8Player
 */
@Service
@Transactional(readOnly = true)
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	private FileInfoDao fileInfoDao;

	@Override
	@Transactional
	public Integer save(FileInfo fileInfo) {
		return this.fileInfoDao.insert(fileInfo);
	}
}