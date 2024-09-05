package org.example.aspects.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging method execution details within the application.
 */
@Aspect
@Component
public class LoggableAspect {

    /**
     * Pointcut that matches all methods within classes annotated.
     */
    @Pointcut("within(@org.example.aspects.annotations.EnableLoggable *) && execution(* * (..))")
    public void annotatedByLoggable() {}

    /**
     * Advice that logs the details of method execution for methods
     * matched by the {@link #annotatedByLoggable()} pointcut.
     * <p>
     * The method logs the start time, calls the target method, logs the end time, and calculates
     * the duration of the method execution.
     * </p>
     *
     * @param proceedingJoinPoint the {@link ProceedingJoinPoint} providing reflective access to both the state available at
     *                            a join point and static information about it
     * @return the result of the method execution
     * @throws Throwable if the target method throws an exception
     */
    @Around("annotatedByLoggable()")
    public Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Calling method " + proceedingJoinPoint.getSignature());
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis() - start;
        System.out.println("Execution of method " + proceedingJoinPoint.getSignature() +
                " finished. Execution time is " + end + " ms.");

        return result;
    }
}
