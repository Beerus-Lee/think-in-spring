package org.own.think.in.spring.i18n;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


@EnableAutoConfiguration
public class CustomizedMessageResourceBeanDemo {


    @Bean
    private MessageSource messageSource () {
        return new ReloadableResourceBundleMessageSource();
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(CustomizedMessageResourceBeanDemo.class)
                        .web(WebApplicationType.NONE)
                        .run(args);


        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (beanFactory.containsLocalBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)){
            System.out.println(beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));
            MessageSource messageSource = beanFactory.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(messageSource);

        }

        applicationContext.close();
    }
}


