package bean.definition;

import bean.factory.DefaultUserFactory;
import bean.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonBeanDefinitionDemo {
    public static void main(String[] args) {
        //创建beanfactory容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //创建一个UserFactory
        UserFactory userFactory = new DefaultUserFactory();
        //注册外部单例对象
        SingletonBeanRegistry beanFactory = context.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);
        //启动容器
        context.refresh();

        UserFactory userFactoryBean = context.getBean("userFactory",UserFactory.class);
        System.out.println(userFactory == userFactoryBean);
        context.close();

    }



}
