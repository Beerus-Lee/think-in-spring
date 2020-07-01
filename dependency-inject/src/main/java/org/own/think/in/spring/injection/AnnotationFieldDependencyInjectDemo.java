package org.own.think.in.spring.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.ioc.domain.User;

import javax.annotation.Resource;

public class AnnotationFieldDependencyInjectDemo {

    @Autowired
    private UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;



    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationFieldDependencyInjectDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String location = "classpath:/META-INF/ioc-dependence-lookup.xml";
        reader.loadBeanDefinitions(location);
        context.refresh();
        AnnotationFieldDependencyInjectDemo demo = context.getBean(AnnotationFieldDependencyInjectDemo.class);
        System.out.println(demo.userHolder);
        System.out.println(demo.userHolder2);
        System.out.println(demo.userHolder == demo.userHolder2);
        context.close();


    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

}
