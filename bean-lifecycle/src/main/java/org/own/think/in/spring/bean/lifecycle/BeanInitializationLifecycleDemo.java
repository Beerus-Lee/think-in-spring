package org.own.think.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.domain.SuperUser;
import spring.ioc.domain.User;

public class BeanInitializationLifecycleDemo {
    public static void main(String[] args) {

        instantiationByBeanFactory();


    }

    private static void instantiationByBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/bean-lifecycle-context.xml","META-INF/ioc-dependence-lookup.xml"};
        reader.loadBeanDefinitions(locations);
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.preInstantiateSingletons();

        User user = beanFactory.getBean("user", User.class);
        System.out.println("user: " + user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println("superUser: " + superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder",UserHolder.class);
        System.out.println("userHolder: " + userHolder);
    }





}
