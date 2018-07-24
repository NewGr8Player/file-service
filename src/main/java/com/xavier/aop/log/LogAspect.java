package com.xavier.aop.log;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("execution(public * com.xavier.api.*Controller.*(..))")
	private void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		String methodName = method.getName();
		String returnType = method.getReturnType().getName();

		logger.info("AOP begin ,请求方法  :{}", method.getDeclaringClass() + "." + methodName + "(" + returnType + ")");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("请求url = {}", request.getRequestURL().toString());
		logger.info("请求方法requestMethod = {}", request.getMethod());
		logger.info("请求资源uri = {}", request.getRequestURI());
		request.getParameterMap().forEach(
				(key, value) -> logger.info("param:value = {}:{}", key, value)
		);
		Map<String,Object> LOG_INGO = new HashMap<>();
		LOG_INGO.put("","");
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		logger.info("RESPONSE : " + JSON.toJSONString(ret));
	}

	@AfterThrowing("webLog()")
	public void throwss(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		String methodName = method.getName();
		logger.info("AOP begin ,请求方法  :{}", method.getDeclaringClass() + "." + methodName);
	}

}
