package org.own.think.in.spring.injection;

import org.own.think.in.spring.injection.annotation.InjectUser;
import org.own.think.in.spring.injection.annotation.MyAutowire;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import spring.ioc.domain.User;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

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

    @Autowired
    private Optional<User> optionalUser;

    @MyAutowire
    private User myUser;

    @InjectUser
    private User injectUser;


//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        Set<Class<? extends Annotation>> sets = new LinkedHashSet<>(Arrays.asList(InjectUser.class, Autowired.class));
//        annotationBeanPostProcessor.setAutowiredAnnotationTypes(sets);
//
//        return annotationBeanPostProcessor;
//    }
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE-3)
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return autowiredAnnotationBeanPostProcessor;
    }

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
        System.out.println("otionalUser: " + demo.optionalUser);
        System.out.println("myAutowire: " + demo.myUser);
        System.out.println("injectUser: " + demo.injectUser);
        context.close();
    }
}
