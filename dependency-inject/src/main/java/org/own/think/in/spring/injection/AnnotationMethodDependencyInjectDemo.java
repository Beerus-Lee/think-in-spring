package org.own.think.in.spring.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.ioc.domain.User;

import javax.annotation.Resource;

public class AnnotationMethodDependencyInjectDemo {

    private  UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    private void init1(UserHolder userHolder){
        this.userHolder = userHolder;
    }

    @Resource
    private void init2(UserHolder userHolder2){
        this.userHolder2 = userHolder2;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationMethodDependencyInjectDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String location = "classpath:/META-INF/ioc-dependence-lookup.xml";
        reader.loadBeanDefinitions(location);
        context.refresh();
        AnnotationMethodDependencyInjectDemo demo = context.getBean(AnnotationMethodDependencyInjectDemo.class);
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
