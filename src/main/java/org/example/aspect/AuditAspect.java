package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.core.model.user.User;
import org.example.service.service.AuditService;
import org.springframework.stereotype.Component;

/**
 * Aspect for auditing actions within the application.
 * <p>
 * This aspect intercepts methods annotated with {@link org.example.annotations.Auditable}
 * and logs the action performed by the user. The logging is performed by the {@link AuditService}.
 * </p>
 */
@Aspect
@Component
public class AuditAspect {

    private final AuditService auditService = new AuditService();

    /**
     * Pointcut that matches all methods within classes annotated with {@link org.example.annotations.Auditable}.
     */
    @Pointcut("within(@org.example.annotations.Auditable *) && execution(* * (..))")
    public void annotatedByAuditable() {}

    /**
     * Advice that logs the action of a user before the execution of methods
     * matched by the {@link #annotatedByAuditable()} pointcut.
     * <p>
     * The method expects the first argument to be a {@link User} object and the
     * second argument to be the action performed by the user as a {@link String}.
     * </p>
     *
     * @param joinPoint the {@link JoinPoint} providing reflective access to both the state available at
     *                  a join point and static information about it
     */
    @Before("annotatedByAuditable()")
    public void logAction(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        User user = (User) args[0];
        String action = args[1].toString();
        auditService.logAction(user.getName(), action);
    }
}
