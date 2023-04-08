package com.miu.lab2.aspect;

import com.miu.lab2.entity.ExceptionEntry;
import com.miu.lab2.repository.IExceptionRepository;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class LogExceptionAspect {
    @Autowired
    IExceptionRepository exceptionRepository;
    @Pointcut("execution(* com.miu.lab2.*.*(..)) && " +
            "!execution(* com.miu.lab2.repository.IExceptionRepository.*(..)) &&" +
            "!execution(* com.miu.lab2.aspect.LogExceptionAspect.*(..))")
    private void logException() {}

    @AfterThrowing(pointcut="logException()", throwing = "ex")
    public void logExceptionDetails(Throwable ex) {
        saveLog(ex);
    }

    private void saveLog(Throwable ex){
        var exception = new ExceptionEntry();

        exception.setPrinciple("Md Monir");
        exception.setOperation(ex.getMessage());
        exception.setExceptionType(ex.getStackTrace()[0].getClassName());
        exception.setDate(new Date());

        exceptionRepository.save(exception);
    }
}
