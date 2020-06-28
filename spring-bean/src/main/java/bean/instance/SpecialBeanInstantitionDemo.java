package bean.instance;

import bean.factory.DefaultUserFactory;
import bean.factory.OtherUserFactory;
import bean.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.domain.User;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 实例化bean
 */
public class SpecialBeanInstantitionDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantition-context.xml");
        AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
        //通过ServiceLoader初始化实例
        //createUserByServiceLoader();
        //通过ServiceLoaderBeanFactory初始化实例
        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader",ServiceLoader.class);
        serviceLoader(serviceLoader);

        //通过AutwireCapableBeanFacotry创建
        UserFactory userFactory = beanFactory.createBean(OtherUserFactory.class);
        System.out.println(userFactory.createUser());

    }
    public static void createUserByServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class,Thread.currentThread().getContextClassLoader());
        serviceLoader(serviceLoader);
    }


    public static void serviceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterable = serviceLoader.iterator();
        while (iterable.hasNext()) {
            System.out.println(iterable.next().createUser());
        }
    }

}
