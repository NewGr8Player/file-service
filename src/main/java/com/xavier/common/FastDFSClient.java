package com.xavier.common;

import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.MataData;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xavier.common.util.DateUtil;
import com.xavier.common.util.FileUtil;
import com.xavier.config.ErrorCode;
import com.xavier.config.FastDFSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * FastDFS Java API. 文件上传下载主类.
 */
@Component
public class FastDFSClient {

	@Autowired
	public FastFileStorageClient fastFileStorageClient;

	/**
	 * MultipartFile 上传文件
	 *
	 * @param file MultipartFile
	 * @return 返回上传成功后的文件路径
	 */
	public String uploadFileWithMultipart(MultipartFile file) throws FastDFSException, IOException {
		if (file == null || file.isEmpty()) {
			throw new FastDFSException(ErrorCode.FILE_ISNULL.CODE, ErrorCode.FILE_ISNULL.MESSAGE);
		}
		return this.fastFileStorageClient.uploadFile(
				file.getInputStream(),
				file.getSize(),
				FileUtil.getFilenameSuffix(file.getOriginalFilename()),
				new HashSet<>(Arrays.asList(
						new MataData(FileUtil.FILE_NAME, file.getOriginalFilename()),
						new MataData(FileUtil.FILE_SIZE, String.valueOf(file.getSize())),
						new MataData(FileUtil.UPLOAD_DATE_TIME, DateUtil.getNowDateString(DateUtil.YMD_DASH_WITH_SECONED_24))
				)))
				.getFullPath();
	}

	/**
	 * 下载文件
	 *
	 * @param group 所在组
	 * @param path  所在路径
	 * @return byte[] 字节流
	 */
	public byte[] downloadFile(String group, String path) throws FastDFSException {
		return this.fastFileStorageClient.downloadFile(group, path, new DownloadByteArray());
	}

	/**
	 * 查看文件信息
	 *
	 * @param group
	 * @param path
	 * @return
	 */
	public FileInfo queryFileInfo(String group, String path) throws FastDFSException {
		return this.fastFileStorageClient.queryFileInfo(group, path);
	}

	/**
	 * 获取
	 *
	 * @param group
	 * @param path
	 * @return
	 * @throws FastDFSException
	 */
	public Set<MataData> getMataData(String group, String path) throws FastDFSException {
		return this.fastFileStorageClient.getMetadata(group, path);
	}
}