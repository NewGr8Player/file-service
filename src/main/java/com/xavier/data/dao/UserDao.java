package com.xavier.data.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xavier.data.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * User Mapper
 *
 * @author NewGr8player
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
