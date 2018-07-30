package com.xavier.data.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xavier.data.bean.base.BaseEntity;
import lombok.*;

/**
 * 账户信息
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@TableName(value = "t_system_info")
public class User extends BaseEntity {

	/**
	 * 系统名称
	 */
	@TableField(value = "system_name")
	private String systemName;

	/**
	 * 系统代码
	 */
	@TableField(value = "system_code")
	private String systemCode;

	/**
	 * 私钥
	 */
	@TableField(value = "private_key")
	private String privateKey;
}
