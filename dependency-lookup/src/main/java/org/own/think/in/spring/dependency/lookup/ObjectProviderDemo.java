package org.own.think.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import spring.ioc.domain.User;

public class ObjectProviderDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(ObjectProviderDemo.class);


        configApplicationContext.refresh();

        lookupByObjectProvider(configApplicationContext);
        lookupCollectionByObjectProvider(configApplicationContext);
        lookupByObjectProviderType(configApplicationContext);

        configApplicationContext.close();


    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext configApplicationContext) {
        ObjectProvider<String> objectProvider = configApplicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }


    private static void lookupCollectionByObjectProvider(AnnotationConfigApplicationContext configApplicationContext) {
        ObjectProvider<String> objectProvider = configApplicationContext.getBeanProvider(String.class);
        objectProvider.stream().forEach(System.out::println);

    }

    private static void lookupByObjectProviderType(AnnotationConfigApplicationContext configApplicationContext) {
        ObjectProvider<User> objectProvider = configApplicationContext.getBeanProvider(User.class);
        System.out.println(objectProvider.getIfAvailable(User::createUser));
    }


    @Bean
    private User user(){
        User user = new User();
        user.setId(33L);
        user.setName("hello");
        return user;
    }

    @Bean
    @Primary
    private String helloWord() {
        return "Hello，World";
    }


    @Bean
    private String message() {
        return "message";
    }
}
