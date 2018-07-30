package com.xavier.data.bean;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xavier.data.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 文件信息
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_file_info")
public class FileInfo extends BaseEntity {

	/**
	 * 系统代码
	 */
	@TableField(value = "system_code")
	private String systemCode;

	/**
	 * 文件路径[保存后]
	 */
	@TableField(value = "file_path")
	private String filePath;

	/**
	 * 文件名称[保存前]
	 */
	@TableField(value = "file_name")
	private String fileName;

	/**
	 * 文件大小
	 */
	@TableField(value = "file_size")
	private Long fileSize;

	/**
	 * 文件类型
	 */
	@TableField(value = "file_type")
	private String fileType;
}