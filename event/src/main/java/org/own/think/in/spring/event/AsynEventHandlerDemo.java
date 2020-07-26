package org.own.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.ErrorHandler;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynEventHandlerDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.addApplicationListener(new MySpringEventListener());
        context.refresh();
        ApplicationEventMulticaster applicationEventMulticaster = context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME,ApplicationEventMulticaster.class);
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster)applicationEventMulticaster;
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            simpleApplicationEventMulticaster.setTaskExecutor(executorService);
            simpleApplicationEventMulticaster.setErrorHandler(new ErrorHandler() {
                @Override
                public void handleError(Throwable t) {
                    System.err.println("当前监听器抛出异常:" + t.getMessage());
                }
            });
            applicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if (!executorService.isShutdown()) {
                        executorService.shutdown();
                    }
                }
            });
        }

        context.addApplicationListener(new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                throw new RuntimeException("故意抛出异常");
            }
        });

        context.publishEvent(new MySpringEvent("hello world"));


        context.close();
    }
}
