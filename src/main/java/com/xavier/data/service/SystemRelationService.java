package com.xavier.data.service;

import com.xavier.data.bean.SystemRelation;

import java.util.List;
import java.util.Map;

/**
 * 系统间关系Service
 *
 * @author NewGr8PLayer
 */
public interface SystemRelationService {

	/**
	 * 根据map查询列表
	 *
	 * @param map
	 * @return
	 */
	List<SystemRelation> searchByMap(Map<String, Object> map);

	/**
	 * 判断是否有权限操作附件
	 *
	 * @param currentSystemCode
	 * @param aimSystemCode
	 * @return
	 */
	Boolean hasPermission(String currentSystemCode, String aimSystemCode, String operateType);
}
