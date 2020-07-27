package org.own.think.in.spring.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {
    @AliasFor(annotation = MyComponentScan.class,attribute = "packagesScan")
    String[] packages() default {};

    @AliasFor("packagesScan")
    String[] scanBasePackages () default {};

    String[] packagesScan() default {};
}
