package com.xavier.common;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xavier.common.util.FileUtil;
import com.xavier.config.ErrorCode;
import com.xavier.config.FastDFSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * FastDFS Java API. 文件上传下载主类.
 */
@Component
public class FastDFSClient {

	@Autowired
	public FastFileStorageClient fastFileStorageClient;

	/**
	 * <p>MultipartFile 上传文件</p>
	 *
	 * @param file MultipartFile
	 * @return 返回上传成功后的文件路径
	 */
	public String uploadFileWithMultipart(MultipartFile file) throws FastDFSException, IOException {
		if (file == null || file.isEmpty()) {
			throw new FastDFSException(ErrorCode.FILE_ISNULL.CODE, ErrorCode.FILE_ISNULL.MESSAGE);
		}
		return fastFileStorageClient.uploadFile(
				file.getInputStream(),
				file.getSize(),
				FileUtil.getFilenameSuffix(file.getOriginalFilename()),
				null)
				.getFullPath();
	}
}