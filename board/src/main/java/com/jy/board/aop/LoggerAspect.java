package com.jy.board.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {
	
	@Around("execution(* com.jy.board..controller.*Controller.*(..)) or execution(* com.jy.board..service.*Impl.*(..)) or execution(* com.jy.board..mapper.*Mapper.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();
		if (name.indexOf("Controller") > -1) {
			type = "Controller  \t:  ";
		}
		else if (name.indexOf("Service") > -1) {
			type = "ServiceImpl  \t:  ";
		}
		else if (name.indexOf("Mapper") > -1) {
			type = "Mapper  \t\t:  ";
		}
		log.info(type + name + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
	}
}