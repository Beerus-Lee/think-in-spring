package org.own.think.in.spring.conversion;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.domain.User;

public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/property-editor-context.xml");
        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);
        System.out.println(user);

        applicationContext.close();


    }
}
