package org.own.think.in.spring.injection;

import org.own.think.in.spring.injection.annotation.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.ioc.domain.User;

import java.util.Collection;

public class ApiQualifierDependencyInejctionDemo {

    @Autowired
    private User user;   //superUser

    @Autowired
    @Qualifier("user")
    private User user1;  //user


    @Bean
    @Qualifier
    private User user3(){  //User3
        return createUser(3L);
    }

    @Bean
    @Qualifier
    private User user4(){ //User4
        return createUser(4L);
    }

    @Autowired
    private Collection<User> users;  //user + SUperUser


    @Autowired
    @Qualifier
    private Collection<User>  users2;  //user3 +user4 + user5 + user6


    @Bean
    @UserGroup
    private User user5(){  //User5
        return createUser(5L);
    }

    @Bean
    @UserGroup
    private User user6(){ //User6
        return createUser(6L);
    }

    @Autowired
    @UserGroup
    private Collection<User>  users3;


    private static User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApiQualifierDependencyInejctionDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String location = "classpath:/META-INF/ioc-dependence-lookup.xml";
        reader.loadBeanDefinitions(location);
        context.refresh();
        ApiQualifierDependencyInejctionDemo demo = context.getBean(ApiQualifierDependencyInejctionDemo.class);
        System.out.println("user:" + demo.user);
        System.out.println("user1:" + demo.user1);
        System.out.println("user3:" + demo.user3());
        System.out.println("user4:" + demo.user4());
        System.out.println("allUsers:" + demo.users);
        System.out.println("allUsers2:" + demo.users2);
        System.out.println("allUsers3:" + demo.users3);



    }


    private static BeanDefinition createBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        beanDefinitionBuilder.addPropertyReference("user", "superUser");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        return beanDefinition;
    }


}
