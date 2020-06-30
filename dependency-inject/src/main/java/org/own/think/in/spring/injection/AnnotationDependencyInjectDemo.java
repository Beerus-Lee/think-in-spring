package org.own.think.in.spring.injection;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.ioc.domain.User;

public class AnnotationDependencyInjectDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationDependencyInjectDemo.class);
        String location = "classpath:/META-INF/ioc-dependence-lookup.xml";
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions(location);
        context.refresh();
        UserHolder userHolder = context.getBean(UserHolder.class);
        System.out.println(userHolder);
        context.close();


    }



    @Bean
    private UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
}
