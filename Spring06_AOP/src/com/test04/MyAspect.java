package com.test04;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {

	// 메소드 실행 전 Advice 실행
	@Before("execution(public * *(..))")
	public void before(JoinPoint join) {
		System.out.println("출석한다.");
	}
	
	// 메소드 실행 후 Advice 실행
	@After("execution(public * *(..))")
	public void after(JoinPoint join) {
		System.out.println("집에 간다.");
	}
	
	// 메서드가 성공 후(예외 없이) Advice 실행
	@AfterReturning(pointcut = "execution(public * *(..))", returning = "returnVal")
	public void returning(JoinPoint join, Object returnVal) {
		System.out.println(returnVal + " 열심히 하는 날이었다.");
	} 
	
	// 메서드가 예외발생 후 Advice 실행
	@AfterThrowing("execution(public * *(..))")
	public void throwing(JoinPoint join) {
		System.out.println("쉬는날이었다...");
	}
	
}
