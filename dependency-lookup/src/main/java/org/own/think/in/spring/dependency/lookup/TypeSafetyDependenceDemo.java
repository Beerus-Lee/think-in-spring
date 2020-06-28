package org.own.think.in.spring.dependency.lookup;

import com.sun.tracing.ProviderFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ioc.domain.User;

public class TypeSafetyDependenceDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TypeSafetyDependenceDemo.class);
        context.refresh();

        displayBeanFactoryGetBean(context);
        displayListableBeanFactoryGetBeanType(context);
        displayBeanProviderIfAvaliableGetBean(context);
        displayObjectFactoryGetBean(context);
        displayBeanProviderStreamOps(context);

        context.close();
    }



    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext configApplicationContext) {
        printGetBeanException("displayBeanFactoryGetBean",() -> configApplicationContext.getBeanFactory().getBean(User.class));
    }

    private static void displayListableBeanFactoryGetBeanType(AnnotationConfigApplicationContext configApplicationContext) {
        printGetBeanException("displayListableBeanFactoryGetBeanType",()-> configApplicationContext.getBeansOfType(User.class));
    }
    private static void displayObjectFactoryGetBean(AnnotationConfigApplicationContext configApplicationContext){
        ObjectFactory<User> objectFactory = configApplicationContext.getBeanProvider(User.class);
        printGetBeanException("displayObjectFactoryGetBean",()-> objectFactory.getObject());
    }

    private static void displayBeanProviderIfAvaliableGetBean(AnnotationConfigApplicationContext configApplicationContext){
        ObjectProvider<User> providerFactory = configApplicationContext.getBeanProvider(User.class);
        printGetBeanException("displayBeanProviderIfAvaliableGetBean",() -> providerFactory.getIfAvailable());
    }

    private static void displayBeanProviderStreamOps(AnnotationConfigApplicationContext configApplicationContext) {
        ObjectProvider<User> objectProvider = configApplicationContext.getBeanProvider(User.class);
        printGetBeanException("displayBeanProviderStreamOps",()-> objectProvider.stream().forEach(System.out::println));
    }

    private static void printGetBeanException(String source,Runnable runnable) {
        System.err.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.err.println("source: " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
