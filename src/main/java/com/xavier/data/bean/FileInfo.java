package com.xavier.data.bean;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
public class FileInfo {

	@TableId(type = IdType.UUID)
	private String id;

	@TableField
	private String systemName;

	@TableField
	private String permisstionCode;

}
