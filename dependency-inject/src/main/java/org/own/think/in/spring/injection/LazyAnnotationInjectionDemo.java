package org.own.think.in.spring.injection;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ioc.domain.User;

import javax.inject.Inject;
import java.util.Set;

public class LazyAnnotationInjectionDemo {

    @Autowired
    @Qualifier("user")
    private User user;

    @Autowired
    private ObjectProvider<User> objectProvider;

    @Autowired
    private ObjectFactory<Set<User>> objectFactory;

    @Inject
    private User user1;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LazyAnnotationInjectionDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String location = "classpath:/META-INF/ioc-dependence-lookup.xml";
        reader.loadBeanDefinitions(location);
        context.refresh();
        LazyAnnotationInjectionDemo demo = context.getBean(LazyAnnotationInjectionDemo.class);

        System.out.println("user: " + demo.user);
        System.out.println("objectProvider: " + demo.objectProvider.getObject());
        System.out.println("objectFactory: " + demo.objectFactory.getObject());
        demo.objectProvider.forEach(System.out::println);

        System.out.println("injectUser: " + demo.user1);
        context.close();
    }
}
