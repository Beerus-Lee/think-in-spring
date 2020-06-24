package spring.ioc.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.respository.UserRespository;

public class DependenceInjectionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:\\META-INF\\ioc-injection-context.xml");
        UserRespository userRespository = (UserRespository) beanFactory.getBean("userRespository");
//        System.out.println(userRespository);
//
//        System.out.println(userRespository.getBeanFactory());
//        System.out.println(userRespository.getBeanFactory() == beanFactory);
       // System.out.println(beanFactory.getBean(BeanFactory.class));
        System.out.println(userRespository.getObjectFactory().getObject());
        System.out.println(beanFactory == userRespository.getObjectFactory().getObject());
    }
}
