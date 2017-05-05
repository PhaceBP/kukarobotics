package com.kukarobotics.reflectiondemo.examples;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE })
@Inherited
@Documented
public @interface Secure {

	Roles role() default Roles.USER;
}
