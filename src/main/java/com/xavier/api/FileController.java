package com.xavier.api;

import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.MataData;
import com.xavier.common.FastDFSClient;
import com.xavier.common.util.FileUtil;
import com.xavier.config.FastDFSException;
import com.xavier.config.FileResponseData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

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
		FileResponseData responseData = new FileResponseData();
		//responseData.setMessage(this.fastDFSClient.queryFileInfo("group1", "M00/00/00/wKi3JltAZfWAC7GMAAFftA_sIUA826.jpg").toString(),"盒盒");
		return responseData;
	}

	@ApiOperation(value = "上传文件通用，只上传文件到服务器，不会保存记录到数据库")
	@PostMapping(value = "/upload/file/sample")
	public FileResponseData uploadFileSample(
			@ApiParam(name = "file", value = "文件", required = true)
			@RequestParam(name = "file")
					MultipartFile file,
			HttpServletRequest request) {
		return uploadSample(file, request);
	}

	@ApiOperation(value = "下载文件")
	@GetMapping(path = "/download/file/sample")
	public FileResponseData downloadFileSample(
			@ApiParam(name = "group", value = "分组", required = true)
			@RequestParam(name = "group")
					String group,
			@ApiParam(name = "path", value = "路径", required = true)
			@RequestParam(name = "path")
					String path,
			@ApiParam(name = "filename", value = "路径")
			@RequestParam(name = "filename", required = false)
					String filename,
			HttpServletResponse response) {
		return downloadSample(group, path, filename, response);
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
			String filepath = this.fastDFSClient.uploadFileWithMultipart(file);
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

	/**
	 * 文件下载通用方法，其它方法调用该方法。<br />
	 * 暂不使用，仅作为公共方法放置于此
	 *
	 * @param group
	 * @param path
	 * @param response
	 * @return
	 */
	private FileResponseData downloadSample(String group, String path, String fileName, HttpServletResponse response) {
		FileResponseData responseData = new FileResponseData();
		try {
			Set<MataData> mataData = this.fastDFSClient.getMataData(group, path);
			if (StringUtils.isBlank(fileName)) {//TODO 获取文件名
				fileName = String.valueOf(mataData);
			}
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.getOutputStream().write(this.fastDFSClient.downloadFile(group, path));
		} catch (FastDFSException e) {
			responseData.setSuccess(false);
			responseData.setCode(e.getCode());
			responseData.setMessage(e.getMessage());
		} catch (Exception e) {
			responseData.setSuccess(false);
			responseData.setCode(e.getClass().toString());
			responseData.setMessage(e.getMessage());
		}

		return responseData;
	}
}
