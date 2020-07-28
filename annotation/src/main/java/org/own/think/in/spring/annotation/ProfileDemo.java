package org.own.think.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

public class ProfileDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProfileDemo.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setDefaultProfiles("odd");


        context.refresh();

        Integer number = context.getBean("number",Integer.class);
        System.out.println(number);



        context.close();


    }

    @Bean(name = "number")
    @Profile("odd")
    public Integer odd () {
        return 1;
    }

    @Bean(name = "number")
//    @Profile("even")
    @Conditional(ProfileCondition.class)
    public Integer even () {
        return 2;
    }
}
