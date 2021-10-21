package codegym.aspect;

import codegym.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(* codegym.service.iml.UserServiceImpl2.*(..))")
    public void serviceMethods() {};

    @Around("serviceMethods()")
    public Object logMethodCall(ProceedingJoinPoint jp) throws Throwable {
        String methodName = jp.getSignature().getName();
        logger.info("Start: " + jp.getSignature().toShortString() + "-" + methodName);
        Object o = jp.proceed();
        logger.info("End: " + jp.getSignature().toShortString());
        return o;
    }
}
