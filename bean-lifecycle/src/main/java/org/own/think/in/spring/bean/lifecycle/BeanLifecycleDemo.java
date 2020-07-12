package org.own.think.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import spring.ioc.domain.SuperUser;
import spring.ioc.domain.User;

public class BeanLifecycleDemo {
    public static void main(String[] args) throws InterruptedException {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/bean-lifecycle-context.xml","META-INF/ioc-dependence-lookup.xml"};
        reader.loadBeanDefinitions(locations);
        beanFactory.preInstantiateSingletons();

        User user = beanFactory.getBean("user", User.class);
        System.out.println("user: " + user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println("superUser: " + superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder",UserHolder.class);
        System.out.println("userHolder: " + userHolder);

        beanFactory.destroyBean("userHolder", userHolder);
        System.out.println(userHolder);
       // beanFactory.destroySingletons();

        beanFactory.destroySingletons();
        System.gc();
        Thread.sleep(1000L);
        System.gc();

    }







}
