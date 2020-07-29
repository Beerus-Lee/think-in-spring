package org.own.think.in.spring.environment;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}")
    public String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnvironmentPropertySourceChangeDemo.class);
        context.refresh();

        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        Map<String,Object> map  = new HashMap<>();
        map.put("user.name", "超哥");
        MapPropertySource mapPropertySource = new MapPropertySource("first-property",map);


        propertySources.addFirst(mapPropertySource);

        for (PropertySource propertySource : propertySources) {
            System.out.println(propertySource);
        }



        context.close();

    }
}
