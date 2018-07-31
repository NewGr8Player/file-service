package com.xavier.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(public * com.xavier.api.*Controller.*(..))")
    private void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map logInfo = new HashMap();
        logInfo.put("requestPackage", method.getDeclaringClass());
        logInfo.put("requestMethod", method.getName());
        logInfo.put("returnType", method.getReturnType().getName());
        logInfo.put("requestUrl", request.getRequestURL().toString());
        logInfo.put("httpMethod", request.getMethod());
        logInfo.put("requestUri", request.getRequestURI());
        logInfo.put("requestParam", request.getParameterMap());
        logger.info(JSON.toJSONString(logInfo));
    }

    @AfterReturning(pointcut = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) {
        logger.info(JSON.toJSONString(ret));
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "exception")
    public void throwss(JoinPoint joinPoint, Exception exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Map logError = new HashMap();
        logError.put("requestPackage", method.getDeclaringClass());
        logError.put("requestMethod", method.getName());
        logError.put("returnType", method.getReturnType().getName());
        logError.put("exceptionClass", exception.getClass());
        logError.put("exceptionCause", exception.getCause());
        logError.put("exceptionMessage", exception.getMessage());
        logger.info(JSON.toJSONString(logError));
    }

}
