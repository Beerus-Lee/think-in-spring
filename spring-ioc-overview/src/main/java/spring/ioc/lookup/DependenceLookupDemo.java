package spring.ioc.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.domain.User;
import spring.ioc.annotation.Super;

import java.util.Map;

public class DependenceLookupDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:\\META-INF\\ioc-dependence-lookup.xml");
        //及时查找
        lookupInRealTime(beanFactory);
        //延时查找
        lookupByLazy(beanFactory);
        //按类型查找
        lookupByType(beanFactory);
        //集合查找
        lookupCollectionsByType(beanFactory);
        //注解查找
        lookupByAnnotation(beanFactory);

    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> map = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("根据注解查找@Super:" + map);
        }
    }

    private static void lookupCollectionsByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> map = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("类型查找集合：" + map);
        }
    }

    private static void lookupByLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }


    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("及时查找" + user);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean(User.class);
        System.out.println("按类型查找" + user);
    }

}
