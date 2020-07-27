package org.own.think.in.spring.annotation;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class)
//@Import(HelloWorldImportSelector.class)
@Import(HelloWorldImportBeanDefinitionRegistrar.class)
public @interface EnableHelloWorld {

}
