package org.own.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import spring.ioc.domain.CityEnum;
import spring.ioc.domain.User;

import java.util.Map;

@PropertySource(name = "yaml-property-source",value = "META-INF/user.yaml" ,factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlBeanPropertySourceDemo {

    @Bean
    private User user(@Value("${user.id}") Long id , @Value("${user.name}") String name,@Value("${user.cityEnum}") CityEnum city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCityEnum(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedYamlBeanPropertySourceDemo.class);
        applicationContext.refresh();


        Map<String,User> yamMap = applicationContext.getBeansOfType(User.class);
        System.out.println(yamMap);

    }
}
