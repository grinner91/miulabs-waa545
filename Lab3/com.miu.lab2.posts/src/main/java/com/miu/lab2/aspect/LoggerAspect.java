package com.miu.lab2.aspect;

import com.miu.lab2.entity.LogEntry;
import com.miu.lab2.repository.ILoggerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LoggerAspect {
    @Autowired
    private ILoggerRepository loggerRepository;


    @Pointcut("@annotation(com.miu.lab2.aspect.annotation.Lab4LogInfo)")
    public void Lab4LogInfo(){}

//    @Pointcut("execution( * com.miu.lab2.controllers.*.*(..))")
//    public void Lab4LogInfo() {
//    }


    @Before("Lab4LogInfo()")
    public void logBefore(JoinPoint joinPoint) {
        logOperation(joinPoint);
    }

    //@AfterReturning(pointcut = "execution(* com.miu.lab2.controllers.*.*(..))")
     //@AfterReturning("LogInfo")
    public void logOperation(JoinPoint joinPoint) {
        String operation =  joinPoint.getSignature().toShortString();

//        System.out.println(" joinPoint.getSignature().getDeclaringTypeName(): " +  joinPoint.getSignature().toShortString());
//        System.out.println(" joinPoint.getSignature().getName(): " +  joinPoint.getSignature().getName());
//        System.out.println(" joinPoint.getSignature().getShortName(): " +  joinPoint.getSignature().toShortString());

        String principle = "md moniruzzaman";

        LogEntry log = new LogEntry();
        log.setPrinciple(principle);
        log.setOperation(operation);
        log.setDate(new Date());

        this.saveLogEntry(log);
    }

    private void saveLogEntry(LogEntry log) {
       loggerRepository.save(log);
    }
}
