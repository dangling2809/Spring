package com.piyush.test.springaop.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.servlet.ModelAndView;

/**
 * Aspect for logging exception and method join points.
 *
 * @author Piyush
 * @version 1
 * @since 1
 */
@Aspect
public class LoggingAspect {

    /**
     * PointCut for all the @Controller annotated beans.
     */
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controllerBean() {
    }

    /**
     * PointCut for the method in class-path.
     */
    @Pointcut("execution(* *(..))")
    public void allMethodPointcut() {
    }
    
    /**
     * PointCut for the methods annotated by {@link Loggable}
     */
    @Pointcut("@annotation(com.piyush.test.springaop.aspects.Loggable)")
    public void loggable() {
    }
    
    /**
     * A around advice for logging controller method and exceptions if thrown any.
     * @param proceedingJoinPoint necessary parameter for executing method within around advice.{@link ProceedingJoinPoint}
     * @return {@link ModelAndView}
     */
    @Around(value = "controllerBean() && loggable()")
    public ModelAndView loggingAdviceForControllers(ProceedingJoinPoint proceedingJoinPoint) {
        ModelAndView mav = null;
        Logger logger = null;
        try {
            System.out.println("Before @Loggable method");
            logger = Logger.getLogger(proceedingJoinPoint.getTarget().getClass().getName());
            logger.trace("Method " + proceedingJoinPoint.getSignature() + "starting");
            mav = (ModelAndView) proceedingJoinPoint.proceed();
            logger.trace("Method " + proceedingJoinPoint.getSignature() + "sucessfully ended");
            System.out.println("After @Loggable method");
        } catch (Throwable ex) {
            mav = new ModelAndView();
            mav.setViewName("error");
            logger.error("Exception in " + proceedingJoinPoint.getSignature() + " mehtod " + ex.getMessage() +" Stack trace is"+ex.fillInStackTrace());
        }
        logger.trace("Method executed successfully returning view now");
        return mav;
    }
    
 
    
}
