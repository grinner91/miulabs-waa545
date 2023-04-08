package com.miu.lab2.aspect;

import com.miu.lab2.entity.LogEntry;
import com.miu.lab2.repository.ILoggerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class ExecutionTimeAspect {
    @Autowired
    private ILoggerRepository loggerRepository;

    @Pointcut("@annotation(com.miu.lab2.aspect.annotation.Lab4ExecutionTime)")
    public void Lab4ExecutionTime() {

    }

    @Around("Lab4ExecutionTime()")
    public Object calculateExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.nanoTime();
        var result = proceedingJoinPoint.proceed();
        long finish = System.nanoTime();
        long exeTime = (finish - start);

        System.out.println(proceedingJoinPoint.getSignature().toShortString() + " takes ns: " + exeTime );

        String operation =  proceedingJoinPoint.getSignature().toShortString();

        String principle = "md moniruzzaman";

        LogEntry log = new LogEntry();
        log.setPrinciple(principle);
        log.setOperation(operation);
        log.setExecutionTime(String.valueOf(exeTime) + " ns");
        log.setDate(new Date());

        this.saveLogEntry(log);

        return result;
    }

    private void saveLogEntry(LogEntry log) {
        loggerRepository.save(log);
    }

}
