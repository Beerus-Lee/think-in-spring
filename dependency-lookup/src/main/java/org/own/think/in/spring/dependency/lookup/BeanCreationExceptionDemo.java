package org.own.think.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        context.registerBeanDefinition("errorBean",beanDefinitionBuilder.getBeanDefinition());
        context.refresh();
        context.close();
    }

    static class POJO implements InitializingBean {

        @PostConstruct
        private void init() throws Throwable {
            throw new Throwable("POJO init error");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet error");
        }
    }
}
