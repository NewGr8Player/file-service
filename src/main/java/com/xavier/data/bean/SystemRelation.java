package com.xavier.data.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xavier.data.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 系统间关系，子系统的数据，父系统可以查看
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_system_relation")
public class SystemRelation extends BaseEntity {

	/**
	 * 父系统编号
	 */
	@TableField(value = "parent_code")
	private String parentCode;

	/**
	 * 子系统编号
	 */
	@TableField(value = "system_code")
	private String systemCode;

	/**
	 * 数据操作权限
	 * <ul>
	 * <li>R : Read</li>
	 * <li>W : Write</li>
	 * <li>RW : Read and Write</li>
	 * </ul>
	 */
	@TableField(value = "operate_permission")
	private String operatePermission;

}
