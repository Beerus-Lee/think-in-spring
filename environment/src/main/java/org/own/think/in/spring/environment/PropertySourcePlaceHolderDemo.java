package org.own.think.in.spring.environment;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.domain.User;

public class PropertySourcePlaceHolderDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF\\placeholder-resolver.xml");
        User user = context.getBean("user", User.class);

        System.out.println(user);



        context.close();


    }
}
