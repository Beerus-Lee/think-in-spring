package org.own.think.in.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableAsync
public class AnnotatedAsynEventHandlerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedAsynEventHandlerDemo.class);
        context.refresh();

        context.publishEvent(new MySpringEvent("hello world"));


        context.close();
    }


    @Async
    @EventListener
    public void onApplicationEvent (MySpringEvent event) {
        System.out.printf("当前线程[%s],监听到事件：%s%n",Thread.currentThread().getName(),event.getMessage());

    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-task-"));
    }

}
