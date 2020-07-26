package org.own.think.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class InjectionApplicationEventPublisherDemo implements ApplicationEventPublisherAware,
        ApplicationContextAware , BeanPostProcessor {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;


    @PostConstruct
    public void init () {
        applicationEventPublisher.publishEvent(new MySpringEvent("@Autowired applicationEventPublisher hello world"));
        applicationContext.publishEvent(new MySpringEvent("@Autowired applicationContext hello world"));

    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectionApplicationEventPublisherDemo.class);
        context.addApplicationListener(new MySpringEventListener());
        context.refresh();
        context.close();

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new MySpringEvent("applicationEventPublisher hello world"));

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.publishEvent(new MySpringEvent("applicationContext hello world"));

    }
}
