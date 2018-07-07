package com.xavier.api;

import com.xavier.common.FastDFSClient;
import com.xavier.common.util.FileUtil;
import com.xavier.config.FastDFSException;
import com.xavier.config.FileResponseData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(path = "/")
public class FileController {

	@Value("${fdfs.file_server_addr}")
	private String fileServerAddr;

	@Autowired
	private FastDFSClient fastDFSClient;

	@ApiOperation(value = "测试接口")
	@GetMapping(path = "/test")
	public FileResponseData test() {
		return new FileResponseData(true);
	}

	@ApiOperation(value = "上传文件通用，只上传文件到服务器，不会保存记录到数据库")
	@PostMapping(value = "/upload/file/sample")
	public FileResponseData uploadFileSample(
			@ApiParam(name = "file", value = "文件", required = true)
			@RequestParam MultipartFile file,
			HttpServletRequest request) {
		return uploadSample(file, request);
	}

	/**
	 * 上传通用方法，其它方法调用该方法。<br />
	 * 暂不使用，仅作为公共方法放置于此
	 *
	 * @param file    MultipartFile
	 * @param request HttpServletRequest
	 * @return
	 */
	private FileResponseData uploadSample(MultipartFile file, HttpServletRequest request) {
		FileResponseData responseData = new FileResponseData();
		try {
			String filepath = fastDFSClient.uploadFileWithMultipart(file);
			responseData.setFileName(file.getOriginalFilename());
			responseData.setFilePath(filepath);
			responseData.setFileSize(file.getSize());
			responseData.setFileType(FileUtil.EXT_MAPS.get(FileUtil.getFilenameSuffix(file.getOriginalFilename())));
			responseData.setHttpUrl(fileServerAddr + FileUtil.SEPARATOR + filepath);
		} catch (FastDFSException e) {
			responseData.setSuccess(false);
			responseData.setCode(e.getCode());
			responseData.setMessage(e.getMessage());
		} catch (IOException e) {
			responseData.setSuccess(false);
			responseData.setCode(e.getClass().toString());
			responseData.setMessage(e.getMessage());
		}
		return responseData;
	}
}
