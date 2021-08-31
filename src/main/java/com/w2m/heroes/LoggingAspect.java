package com.w2m.heroes;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2(topic = "Timer")
public class LoggingAspect {

   @Pointcut("@annotation(com.w2m.heroes.annotations.Loggable)")
   private void pointcut() {
   }

   @Around("pointcut()")
   public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

      String clazzName = joinPoint.getTarget().getClass().getName();
      String methodName = joinPoint.getSignature().getName();
      long start = System.currentTimeMillis();
      log.info("{}: {}: start...", clazzName, methodName);

      Object result = joinPoint.proceed();

      long time = System.currentTimeMillis() - start;
      log.info("{}: {}: : end... cost time: {} ms", clazzName, methodName, time);

      return result;
   }
}
