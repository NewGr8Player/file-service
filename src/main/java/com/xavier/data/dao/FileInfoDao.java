package com.xavier.data.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xavier.data.bean.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * FileInfo Mappoer
 *
 * @author NewGr8Player
 */
@Mapper
public interface FileInfoDao extends BaseMapper<FileInfo>{

	@Select("select * from t_file_info where system_name= #{systemName}")
	FileInfo searchBySystemName(@Param("systemName") String systemName);
}
