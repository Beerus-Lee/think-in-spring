package org.own.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class XmlBeanYamlPropertySourceDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("META-INF/yaml-property-source-context.xml");

        Map<String,Object> userMap = beanFactory.getBean("yamlMap", Map.class);
        System.out.println(userMap);


    }
}
