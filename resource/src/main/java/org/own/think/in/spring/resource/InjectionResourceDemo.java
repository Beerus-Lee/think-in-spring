package org.own.think.in.spring.resource;

import org.own.think.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

public class InjectionResourceDemo {


    @Value("classpath:/META-INF/default.properties")
    private Resource resource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @Value("${user.dir}")
    private String currentPath;


    @PostConstruct
    private void init() {
        System.out.println(ResourceUtils.getDefaultContent(resource));
        System.out.println("==============================");
        Stream.of(resources).map(ResourceUtils::getDefaultContent).forEach(System.out::println);

    }



    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectionResourceDemo.class);

        applicationContext.refresh();

        applicationContext.close();

    }

}
