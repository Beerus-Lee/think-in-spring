package org.own.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import spring.ioc.domain.User;

public class ExtensibleXmlBeanDefinitionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("/META-INF/user-context.xml");
        User user = beanFactory.getBean(User.class);
        System.out.println(user);

    }
}
