package com.xavier.data.bean.base;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 公共父类
 *
 * @author NewGr8Player
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

	/**
	 * 主键Id
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;

	/**
	 * 重写toString,输出Json格式字符串
	 *
	 * @return JsonString
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
