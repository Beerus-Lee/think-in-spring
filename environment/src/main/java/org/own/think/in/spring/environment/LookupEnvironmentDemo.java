package org.own.think.in.spring.environment;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.Environment;

public class LookupEnvironmentDemo implements EnvironmentAware {
    private Environment environment;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LookupEnvironmentDemo.class);
        context.refresh();
        LookupEnvironmentDemo demo = context.getBean(LookupEnvironmentDemo.class);
        Environment environment =  context.getBean(AbstractApplicationContext.ENVIRONMENT_BEAN_NAME,Environment.class);
        System.out.println(demo.environment == environment);


        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
