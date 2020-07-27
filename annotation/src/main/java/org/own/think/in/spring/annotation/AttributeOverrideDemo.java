package org.own.think.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@MyComponentScan2(scanBasePackages = "org.own.think.in.spring.annotation")
public class AttributeOverrideDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AttributeOverrideDemo.class);
        context.refresh();

        MyTest myTest = context.getBean(MyTest.class);
        System.out.println(myTest);

        context.close();
    }
}
