package com.xavier.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 注入FastDFS-Client组件
 *
 * @author NewGr8Player
 */
@Documented
@Inherited
@Import(FdfsClientConfig.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableFdfsClient {
}
