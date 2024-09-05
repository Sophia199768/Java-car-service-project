package org.example.aspects.aspect;

import org.example.aspects.auditService.AuditService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Aspect for auditing actions within the application.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditService auditService;

    /**
     * Pointcut that matches all methods within classes annotated.
     */

    /**
     * Advice that logs the action of a user before the execution of methods
     *
     * @param joinPoint the {@link JoinPoint} providing reflective access to both the state available at
     *                  a join point and static information about it
     */
    @Before("execution(* com.example..*(..))")
    public void logAction(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String action = args[1].toString();
        auditService.logAction(action);
    }
}
