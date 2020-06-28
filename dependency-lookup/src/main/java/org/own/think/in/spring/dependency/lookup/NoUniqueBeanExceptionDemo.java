package org.own.think.in.spring.dependency.lookup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class NoUniqueBeanExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(NoUniqueBeanExceptionDemo.class);
        context.refresh();
        try {
            String messge = context.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            e.printStackTrace();
        }

        context.close();
    }

    @Bean
    public String bean1(){
        return "bean1";
    }

    @Bean
    public String bean2(){
        return "bean2";
    }

    @Bean
    public String bean3(){
        return "bean3";
    }

}


