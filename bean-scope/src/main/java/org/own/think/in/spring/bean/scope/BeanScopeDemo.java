package org.own.think.in.spring.bean.scope;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import spring.ioc.domain.User;

import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

public class BeanScopeDemo implements DisposableBean {


    @Autowired
    @Qualifier("singletonType")
    private User singletonTypeUser;
    @Autowired
    @Qualifier("singletonType")
    private User singletonTypeUser1;

    @Autowired
    @Qualifier("protoType")
    private User protoTypeUser1;
    @Autowired
    @Qualifier("protoType")
    private User protoTypeUser2;
    @Autowired
    @Qualifier("protoType")
    private User protoTypeUser3;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;


    @Bean
    private User singletonType() {
        return createUser(System.nanoTime());
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    private User protoType() {
        return createUser(System.nanoTime());
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanScopeDemo.class);
        context.refresh();
        BeanScopeDemo demo = context.getBean(BeanScopeDemo.class);
        System.out.println("singletonTypeUser: " + demo.singletonTypeUser);
        System.out.println("singletonTypeUser1: " + demo.singletonTypeUser1);
        System.out.println("protoTypeUser1: " + demo.protoTypeUser1);
        System.out.println("protoTypeUser2: " + demo.protoTypeUser2);
        System.out.println("protoTypeUser3: " + demo.protoTypeUser3);
        System.out.println("users: " + demo.users);

        for (int i = 0; i < 3; i++) {
            User singletonTypeUser = context.getBean("singletonType", User.class);
            User protoTypeUser = context.getBean("protoType", User.class);
            System.out.println("依赖查找 singletonTypeUser: " + singletonTypeUser);
            System.out.println("依赖查找 protoTypeUser: " + protoTypeUser);
        }


        context.close();

    }


    @Override
    public void destroy() throws Exception {
        System.out.printf("当前 Bean 销毁中...\n");
        this.protoTypeUser1.destory();
        this.protoTypeUser2.destory();
        this.protoTypeUser3.destory();
        for (Map.Entry<String, User> entry : this.users.entrySet()) {

            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()) {
                User user = entry.getValue();
                user.destory();
            }


        }

        System.out.println("当前bean销毁完成.....");

    }
}
