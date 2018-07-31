package com.xavier.data.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xavier.data.bean.SystemRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * SystemRelation Mapper
 *
 * @author NewGr8Player
 */
@Mapper
public interface SystemRelationDao extends BaseMapper<SystemRelation> {

	@Select("SELECT sc.id,sc.parent_code parentCode,sc.system_code systemCode,sc.operate_permission operatePermission FROM t_system_relation sc WHERE sc.system_code =#{systemCode}")
	List<SystemRelation> searchBySystemCode(@Param("systemCode")String systemCode);
}
