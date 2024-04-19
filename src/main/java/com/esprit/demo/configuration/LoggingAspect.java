package com.esprit.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Before("execution(* com.esprit.demo.service.ClientService.retrieveAllClients(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("In method " + name + " : ");
    }
    @After("execution(* com.esprit.demo.service.ClientService.retrieveAllClients(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Exiting from method " + name + " : ");
    }
    @AfterReturning(pointcut = "execution(* com.esprit.demo.service.ClientService.addClient(..))", returning = "retVal")
    public void logMethodReturn(JoinPoint joinPoint, Object retVal) {
        String name = joinPoint.getSignature().getName();
        log.info("Method " + name + " returned value: " + retVal);
    }

    @AfterThrowing(pointcut = "execution(* com.esprit.demo.service.ClientService.removeClient(..))", throwing = "e")
    public void logMethodException(JoinPoint joinPoint, Throwable e) {
        String name = joinPoint.getSignature().getName();
        log.error("Exception in method " + name + ": " + e.getMessage());
    }

}
