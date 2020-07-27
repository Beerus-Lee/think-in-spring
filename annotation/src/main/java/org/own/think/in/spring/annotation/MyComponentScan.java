package org.own.think.in.spring.annotation;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan
public @interface MyComponentScan {
    @AliasFor(annotation = ComponentScan.class,attribute = "value")
    String[] packagesScan() default {"#"};




}
