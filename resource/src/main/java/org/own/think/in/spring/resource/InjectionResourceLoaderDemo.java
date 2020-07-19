package org.own.think.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class InjectionResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader loader;

    @Autowired
    private ResourceLoader resourceLoader;


    @Autowired
    private AbstractApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        System.out.println("loader == resourceLoader : " + (loader == resourceLoader));
        System.out.println("loader == applicationContext : " + (loader == applicationContext));

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectionResourceLoaderDemo.class);
        applicationContext.refresh();

        applicationContext.close();

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }
}
