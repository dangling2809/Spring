package com.piyush.test.springaop.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate your method with this annotation if you want to enable logging for it
 * Currently applicable to {@link org.springframework.stereotype.Controller} methods and its powered by Spring AOP.
 * @author Piyush
 * @version 1
 * @since 1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {

}
