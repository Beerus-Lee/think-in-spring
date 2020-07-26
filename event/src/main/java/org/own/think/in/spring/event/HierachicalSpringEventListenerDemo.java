package org.own.think.in.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HierachicalSpringEventListenerDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyListener.class);

        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.register(MyListener.class);
        currentContext.setParent(parentContext);
        parentContext.refresh();
        currentContext.refresh();

        currentContext.close();
        parentContext.close();


    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent> {
        private static Set<ApplicationContextEvent> events = new LinkedHashSet();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if (events.add(event)) {
                System.out.printf("监听到事件[%s]当前应用上下文id:[%s]%n", event.getClass().getSimpleName(), event.getApplicationContext().getId());
            }

        }
    }
}
