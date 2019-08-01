package org.godseop.apple.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
public class LoggingAdvice {

    @Before(value = "execution(* org.godseop.apple.service.*Service.*(..))")
    public void beforeService(JoinPoint joinPoint) {
        log.debug("{} execute. parameter: {}", joinPoint.toLongString(), joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* org.godseop.apple.service.*Service.*(..))", returning = "result")
    public void afterReturningService(JoinPoint joinPoint, Object result) {
        log.debug("{} complete. result: {}", joinPoint.toLongString(), result);
    }



    @Around("@annotation(transactional)")
    public Object aroundTransactionalService(final ProceedingJoinPoint joinPoint, final Transactional transactional) throws Throwable {
        log.debug("TRANSACTIONAL SERVICE START ===========================================");
        // you can write some logic before service method execution
        Object result = joinPoint.proceed();
        // you can write some logic here and be able to change result
        log.debug("TRANSACTIONAL SERVICE STOP ============================================");
        return result;
    }

    @Around("@annotation(scheduled)")
    public Object process(final ProceedingJoinPoint joinPoint, final Scheduled scheduled) throws Throwable {
        log.debug("SCHEDULED SERVICE START ===============================================");
        Object result = joinPoint.proceed();
        log.debug("SCHEDULED SERVICE STOP ================================================");
        return result;
    }


}
