package org.own.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {
    public static void main(String[] args) {
        //GenericApplicationContext context = new GenericApplicationContext();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationListenerDemo.class);

        //方法一：基于 spring 上下文注册
        // a.基于ConfigurationApplicationContext API 实现
        context.addApplicationListener(event -> println(event));

        //b.基于 applicatinoListener 注册为bean
        // 通过configuration class注册
        context.register(MyApplicationListener.class);



        context.refresh();
        context.start();
        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("hello world") {
        });
        applicationEventPublisher.publishEvent("hello world");
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            println("MyApplicationListener: " + event);
        }
    }


    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        println("@EventListener: ContextStartedEvent" );
    }

    @EventListener
    @Async
    public void onApplicationEvent(ContextRefreshedEvent event) {
        println("@EventListener: ContextRefreshedEvent" );
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        println("@EventListener: ContextClosedEvent" );
    }

    private static void println(Object printObject) {
        System.out.printf("当前线程[%s]: 接受事件: %s%n", Thread.currentThread().getName(),printObject);
    }
}
