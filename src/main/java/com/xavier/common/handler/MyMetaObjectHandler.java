package com.xavier.common.handler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据预处理
 * 在进行数据库操作前操作数据
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    private final static Logger logger = LoggerFactory.getLogger(MetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        logger.info("新增:" + metaObject.toString());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("更新:" + metaObject.toString());
    }
}
