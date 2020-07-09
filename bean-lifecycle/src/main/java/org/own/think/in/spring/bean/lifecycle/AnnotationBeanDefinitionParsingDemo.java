package org.own.think.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

public class AnnotationBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beforeBeanDefinitionNums = beanFactory.getBeanDefinitionCount();
        reader.register(AnnotationBeanDefinitionParsingDemo.class);
        int afterBeanDefinitionNums = beanFactory.getBeanDefinitionCount();
        System.out.println("afterBeanDefinitionNums - beforeBeanDefinitionNums = " + (afterBeanDefinitionNums - beforeBeanDefinitionNums));
        AnnotationBeanDefinitionParsingDemo demo = beanFactory.getBean("annotationBeanDefinitionParsingDemo",AnnotationBeanDefinitionParsingDemo.class);
        System.out.println(demo);
    }
}
