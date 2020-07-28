package org.own.think.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class InjectionEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;

    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment2;
    @Autowired
    private ApplicationContext applicationContext2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectionEnvironmentDemo.class);
        context.refresh();
        InjectionEnvironmentDemo demo = context.getBean(InjectionEnvironmentDemo.class);
        Environment environment = context.getEnvironment();

        System.out.println(environment);
        System.out.println(demo.environment == demo.environment2);
        System.out.println(demo.environment == environment);
        System.out.println(demo.environment == demo.applicationContext.getEnvironment());
        System.out.println(demo.environment == demo.applicationContext2.getEnvironment());





        context.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;

    }
}
