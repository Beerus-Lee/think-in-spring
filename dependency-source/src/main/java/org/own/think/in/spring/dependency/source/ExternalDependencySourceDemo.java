package org.own.think.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource(value = "META-INF/default.properties" ,encoding = "utf-8")
public class ExternalDependencySourceDemo {


    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String userName;
    @Value("${user.resource}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ExternalDependencySourceDemo.class);
        context.refresh();

        ExternalDependencySourceDemo demo = context.getBean(ExternalDependencySourceDemo.class);
        System.out.println(demo.id);
        System.out.println(demo.userName);
        System.out.println(demo.resource);
        context.close();
    }
}
