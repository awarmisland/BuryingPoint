package com.awarmisland.aptannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS) //编译时注解
@Target(ElementType.METHOD) //作用方法上
public @interface RecordClick {
    String value();
}
