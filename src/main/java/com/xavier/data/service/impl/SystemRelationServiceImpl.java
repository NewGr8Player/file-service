package com.xavier.data.service.impl;

import com.xavier.data.bean.SystemRelation;
import com.xavier.data.common.ConstVars;
import com.xavier.data.dao.SystemRelationDao;
import com.xavier.data.service.SystemRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统间关系Service实现
 *
 * @author NewGr8Player
 */
@Service
@Transactional(readOnly = true)
public class SystemRelationServiceImpl implements SystemRelationService {

	@Autowired
	private SystemRelationDao systemRelationDao;

	@Override
	public List<SystemRelation> searchByMap(Map<String, Object> map) {
		return this.systemRelationDao.selectByMap(map);
	}

	@Override
	public Boolean hasPermission(String currentSystemCode, String aimSystemCode, String operateType) {
		if (currentSystemCode.equals(aimSystemCode)) {
			return true;
		}
		Map<String, Object> map = new HashMap<>();//TODO 可优化部分
		map.put(ConstVars.TABLE_COLUMN_SYSTEM_RELATION_PARENT_CODE, currentSystemCode);
		map.put(ConstVars.TABLE_COLUMN_SYSTEM_RELATION_SYSTEM_CODE, aimSystemCode);
		List<SystemRelation> systemRelationList = searchByMap(map);
		if (null == systemRelationList || systemRelationList.size() == 0) {
			return false;
		}
		for (SystemRelation systemRelation : systemRelationList) {
			if(systemRelation.getOperatePermission().contains(operateType)){
				return true;
			}
		}
		return false;
	}
}
