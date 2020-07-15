package org.own.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import spring.ioc.domain.User;

public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource= resourceLoader.getResource("classpath:/META-INF/user.properties");
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        int beanDefinitionsNumbers = reader.loadBeanDefinitions(encodedResource);
        System.out.printf("找到%sBean%n",beanDefinitionsNumbers);
        User user = beanFactory.getBean("user",User.class);
        System.out.println(user);


    }
}
