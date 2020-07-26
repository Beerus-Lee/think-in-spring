package org.own.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

public class CustomizedSpringEventDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.addApplicationListener(new MySpringEventListener());

        context.addApplicationListener(new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                System.out.println("当前event: " + event);
            }
        });

        context.refresh();

        context.publishEvent(new MySpringEvent("heelooo world"));
        context.publishEvent(new MySpringEvent2("2020"));
        context.close();
    }
}
