package org.own.think.in.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import spring.ioc.domain.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        EnvironmentAware,InitializingBean,SmartInitializingSingleton,DisposableBean {
    private User user;

    private Long id;

    private String description;

    private String beanName;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private Environment environment;

    public Environment getEnvironment() {
        return environment;
    }

    public String getBeanName() {
        return beanName;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @PostConstruct
    public void init() {
        this.description = "user holder v4";
        System.out.println("@PostConstruct:" + this.description);
    }

    @PreDestroy
    public void destoryMethod() {
        this.description = "user holder v10";
        System.out.println("@PreDestroy:" + this.description);
    }

    public void destoryBeanProcessor() {
        this.description = "user holder v12";
        System.out.println("destoryBeanProcessor:" + this.description);
    }


    public void initBeanProcessor() {
        this.description = "user holder v6";
        System.out.println("initBeanProcessor:" + this.description);
    }


    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
         this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = "user holder v5";
        System.out.println("afterPropertiesSet: " + this.description);
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.description = "user holder v8";
        System.out.println("SmartInitializingSingleton: " + this.description);
    }

    @Override
    public void destroy() throws Exception {
        this.description = "user holder v11";
        System.out.println("DisposableBean: " + this.description);

    }

    @Override
    protected void finalize() throws Throwable {
        this.description = "user holder 13";
        System.out.println("jvm gc finalize: " + this.description);
    }
}
