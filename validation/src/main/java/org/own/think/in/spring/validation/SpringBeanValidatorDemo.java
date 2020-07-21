package org.own.think.in.spring.validation;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SpringBeanValidatorDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");
        applicationContext.refresh();
        Validator validator = applicationContext.getBean(Validator.class);
        System.out.println(validator instanceof LocalValidatorFactoryBean);
        User user = new User();
        UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);
        userProcessor.process(user);
        applicationContext.close();

    }



    @Component
    @Validated
    static class UserProcessor {
        public void process (@Valid User user) {
            System.out.println(user);
        }
    }


    static class User {
        @NotNull
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
