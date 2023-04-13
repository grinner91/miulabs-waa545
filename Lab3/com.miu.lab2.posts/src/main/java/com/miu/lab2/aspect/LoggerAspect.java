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



/*
 import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeControllerAspect {

    @After("@annotation(Alert) &&
    (execution(* create*(..))
    || execution(* delete*(..))
    || execution(* update*(..)))")
    public void sendAlert(JoinPoint joinPoint) {
        // implementation to send alert ...
        System.out.println("Alert: EmployeeController " + joinPoint.getSignature().getName() + " method has been called.");
    }
}


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeControllerAspect {

    @After("@annotation(Alert) && execution(* EmployeeController.create*(..))")
    public void sendAlertOnCreate(JoinPoint joinPoint) {
        // implementation to send alert on create method...
        System.out.println("Alert: EmployeeController create method has been called.");
    }

    @After("@annotation(Alert) && execution(* EmployeeController.delete*(..))")
    public void sendAlertOnDelete(JoinPoint joinPoint) {
        // implementation to send alert on delete method...
        System.out.println("Alert: EmployeeController delete method has been called.");
    }

    @After("@annotation(Alert) && execution(* EmployeeController.update*(..))")
    public void sendAlertOnUpdate(JoinPoint joinPoint) {
        // implementation to send alert on update method...
        System.out.println("Alert: EmployeeController update method has been called.");
    }
}




 */
