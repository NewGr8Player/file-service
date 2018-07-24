package com.xavier.api;

import com.github.tobato.fastdfs.domain.StorePath;
import com.xavier.common.FastDFSClient;
import com.xavier.common.util.FileUtil;
import com.xavier.config.ErrorCode;
import com.xavier.config.FastDFSException;
import com.xavier.config.FileResponseData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 文件服务RestController
 *
 * @author NewGr8Player
 */
@RestController
@RequestMapping(path = "/")
public class FileController {

	private final Logger logger = LoggerFactory.getLogger(FileController.class);

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
			@RequestParam(name = "file")
					MultipartFile file,
			HttpServletRequest request) {
		return uploadSample(file, request);
	}

	@ApiOperation(value = "只能上传图片，只上传文件到服务器，不会保存记录到数据库. <br>会检查文件格式是否正确，默认只能上传 ['png', 'gif', 'jpeg', 'jpg'] 几种类型.")
	@PostMapping(value = "/upload/image/sample")
	public FileResponseData uploadImageSample(
			@ApiParam(name = "file", value = "图片文件", required = true)
			@RequestParam MultipartFile file,
			HttpServletRequest request) {
		if (!FileUtil.checkImage(file.getOriginalFilename())) {
			FileResponseData responseData = new FileResponseData(false);
			responseData.setCode(ErrorCode.FILE_TYPE_ERROR_IMAGE.CODE);
			responseData.setMessage(ErrorCode.FILE_TYPE_ERROR_IMAGE.MESSAGE);
			return responseData;
		}
		return uploadSample(file, request);
	}

	@ApiOperation(value = "只能上传文档，只上传文件到服务器，不会保存记录到数据库. <br> 会检查文件格式是否正确，默认只能上传 ['pdf', 'ppt', 'xls', 'xlsx', 'pptx', 'doc', 'docx'] 几种类型.")
	@PostMapping(value = "/upload/doc/sample")
	public FileResponseData uploadDocSample(
			@ApiParam(name = "file", value = "文档文件", required = true)
			@RequestParam MultipartFile file,
			HttpServletRequest request) {
		if (!FileUtil.checkDoc(file.getOriginalFilename())) {
			FileResponseData responseData = new FileResponseData(false);
			responseData.setCode(ErrorCode.FILE_TYPE_ERROR_DOC.CODE);
			responseData.setMessage(ErrorCode.FILE_TYPE_ERROR_DOC.MESSAGE);
			return responseData;
		}

		return uploadSample(file, request);
	}

	@ApiOperation(value = "根据指定的路径删除服务器文件，适用于没有保存数据库记录的文件")
	@DeleteMapping(value = "/delete/file")
	public FileResponseData deleteFile(
			@ApiParam(name = "filePath", value = "路径(groupName/path)", required = true)
			@RequestParam(name = "filePath")
					String filePath) {
		FileResponseData responseData = new FileResponseData();
		try {
			this.fastDFSClient.deleteFile(filePath);
		} catch (FastDFSException e) {
			e.printStackTrace();
			responseData.setSuccess(false);
			responseData.setCode(e.getCode());
			responseData.setMessage(e.getMessage());
		}
		return responseData;
	}

	@ApiOperation(value = "下载文件")
	@GetMapping(path = "/download/file/sample")
	public void downloadFileSample(
			@ApiParam(name = "filePath", value = "路径(groupName/path)", required = true)
			@RequestParam(name = "filePath")
					String filePath,
			HttpServletResponse response) {
		downloadSample(filePath, response);
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
			StorePath storePath = this.fastDFSClient.uploadFileWithMultipart(file);
			responseData.setFileName(file.getOriginalFilename());
			responseData.setFilePath(storePath.getFullPath());
			responseData.setFileSize(file.getSize());
			responseData.setFileType(FileUtil.EXT_MAPS.get(FileUtil.getFilenameSuffix(file.getOriginalFilename())));
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
	 * @param filePath
	 * @param response
	 * @return
	 */
	private void downloadSample(String filePath, HttpServletResponse response) {
		try {
			Map mataData = this.fastDFSClient.getMataData(filePath);
			String fileName = (String) mataData.get(FileUtil.FILE_NAME);
			String contentType = FileUtil.EXT_MAPS.get(FileUtil.getFilenameSuffix(fileName));
			String encoderName = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20").replace("%2B", "+");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + encoderName + "\"");
			response.setContentType(contentType + ";charset=UTF-8");
			response.setHeader("Accept-Ranges", "bytes");
			response.getOutputStream().write(this.fastDFSClient.downloadFile(filePath));
		} catch (FastDFSException e) {
			logger.debug(e.toString());
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
	}
}
