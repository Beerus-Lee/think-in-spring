package org.own.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.*;
import spring.ioc.domain.User;

import java.util.Map;

@ImportResource("classpath:/META-INF/ioc-dependence-lookup.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user.properties")
@PropertySource("classpath:/META-INF/user.properties")
public class AnnotatedSpringIocContainerMetadataConfigurationDemo {

    @Bean
    private User configurationUser(@Value("${user.id}") Long id ,@Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedSpringIocContainerMetadataConfigurationDemo.class);
        applicationContext.refresh();
        Map<String,User> map = applicationContext.getBeansOfType(User.class);
        for (Map.Entry<String,User> entry : map.entrySet()) {
            System.out.println("当前beanName:" + entry.getKey() + ",user: " + entry.getValue());
        }


        applicationContext.close();

    }
}
