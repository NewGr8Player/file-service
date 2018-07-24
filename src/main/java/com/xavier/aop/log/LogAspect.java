package com.xavier.aop.log;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    //TODO 确认日止记录形式

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("execution(public * com.xavier.api.*Controller.*(..))")
    private void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        String returnType = method.getReturnType().getName();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("AOP begin ,请求方法  :{}", method.getDeclaringClass() + "." + methodName + "(" + returnType + ")");
        logger.info("请求url = {}", request.getRequestURL().toString());
        logger.info("请求方法requestMethod = {}", request.getMethod());
        logger.info("请求资源uri = {}", request.getRequestURI());
        request.getParameterMap().forEach(
                /* 理论上当value是MFile时候，toStirng是空串 */
                (key, value) -> logger.info("param:value = {}:{}", key, value.toString())
        );
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("RESPONSE : " + JSON.toJSONString(ret));
        this.redisTemplate.opsForValue().set(System.currentTimeMillis() + ":" + ret.hashCode(),JSON.toJSONString(ret));
    }

    @AfterThrowing("webLog()")
    public void throwss(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        logger.info("AOP begin ,请求方法  :{}", method.getDeclaringClass() + "." + methodName);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class LogInfo {

        /**
         * 执行时刻
         */
        private String requestDate;

        /**
         * 请求方法所在包
         */
        private String requestPackage;

        /**
         * 请求方法
         */
        private String requestMethod;

        /**
         * 请求方法返回类型
         */
        private String returnType;

        /**
         * 请求url
         */
        private String requestUrl;

        /**
         * HTTP请求方法
         */
        public String httpMethod;

        /**
         * 请求资源路径
         */
        private String requestUri;

        /**
         * 请求参数与参数值
         */
        private Map<String,?> requestParam;
    }
}
