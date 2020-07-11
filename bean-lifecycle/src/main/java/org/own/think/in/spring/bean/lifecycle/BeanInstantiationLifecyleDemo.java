package org.own.think.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.domain.SuperUser;
import spring.ioc.domain.User;

public class BeanInstantiationLifecyleDemo {
    public static void main(String[] args) {

        instantiationByBeanFactory();
        System.out.println("=========================================");
        instantiationByApplicationContext();


    }

    private static void instantiationByBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/bean-lifecycle-context.xml","META-INF/ioc-dependence-lookup.xml"};
        reader.loadBeanDefinitions(locations);
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        User user = beanFactory.getBean("user", User.class);
        System.out.println("user: " + user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println("superUser: " + superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder",UserHolder.class);
        System.out.println("userHolder: " + userHolder);
    }

    private static void instantiationByApplicationContext() {

        String[] locations = {"META-INF/bean-lifecycle-context.xml","META-INF/ioc-dependence-lookup.xml"};
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(locations);
        applicationContext.refresh();
        User user = applicationContext.getBean("user", User.class);
        System.out.println("user: " + user);

        SuperUser superUser = applicationContext.getBean("superUser", SuperUser.class);
        System.out.println("superUser: " + superUser);

        UserHolder userHolder = applicationContext.getBean("userHolder",UserHolder.class);
        System.out.println("userHolder: " + userHolder);
        applicationContext.close();
    }




}
