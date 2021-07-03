package com.samajackun.summer.conf.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertiesSource
{
public enum Position {
	ABSOLUTE, RELATIVE
};

public Position position() default Position.ABSOLUTE;
}
