package org.own.think.in.spring.bean.scope.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import spring.ioc.domain.User;


@Configuration
@EnableWebMvc
public class WebConfiguration {




    @Bean
    @RequestScope
    public static User user() {
        User user = new User();
        user.setId(1L);
        user.setName("超哥");
        return user;
    }

}
