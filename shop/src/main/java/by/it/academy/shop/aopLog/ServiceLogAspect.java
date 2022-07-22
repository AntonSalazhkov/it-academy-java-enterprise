package by.it.academy.shop.aopLog;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Сервис логирования данных.
 */

@Slf4j
@Aspect
@Component
public class ServiceLogAspect extends BaseAspect {
    @Pointcut("execution(* by.it.academy.shop.services..*(..)) && !@annotation(by.it.academy.shop.aopLog.ExcludeLog)")
    public void before() {
    }

    @Pointcut("execution(* by.it.academy.shop.services..*(..)) && !@annotation(by.it.academy.shop.aopLog.ExcludeLog)")
    public void after() {
    }

    @Before("before()")
    public void logServicesBefore(JoinPoint joinPoint) {

        log.info(BEFORE_SERVICE_PATTERN,
                joinPoint.getSignature().toShortString(),
                getArgsWithNames(joinPoint));
    }

    @AfterReturning(pointcut = "after()", returning = "result")
    public void logServicesAfter(JoinPoint joinPoint, Object result) {

        log.info(AFTER_SERVICE_PATTERN,
                joinPoint.getSignature().toShortString(),
                getStringInstanceOf(Optional.ofNullable(result).orElse("not defined")),
                getArgsWithNames(joinPoint));
    }
}
