package org.own.think.in.spring.configuration.metadata;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import spring.ioc.domain.User;

import java.util.HashMap;
import java.util.Map;

@PropertySource("META-INF/user.properties")
public class PropertySourceDemo {

    @Bean
    private User user (@Value("${user.id}") Long id ,@Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PropertySourceDemo.class);

        Map<String, Object> propertiesSource = new HashMap<>();
        propertiesSource.put("user.name", "超啊");
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("property-source",propertiesSource);
        context.getEnvironment().getPropertySources().addFirst(propertySource);


        context.refresh();
        Map<String,User> userMap = context.getBeansOfType(User.class);
        for (Map.Entry<String,User> entry : userMap.entrySet()) {
            System.out.println(entry.getKey() +" : " +entry.getValue());
        }




        context.close();
    }
}
