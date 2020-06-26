package spring.ioc.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import spring.ioc.respository.UserRespository;

public class DependenceInjectionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:\\META-INF\\ioc-injection-context.xml");
        //自定义bean
        UserRespository userRespository = (UserRespository) beanFactory.getBean("userRespository");
//        System.out.println(userRespository);
        //内建依赖
       System.out.println(userRespository.getBeanFactory());
//        System.out.println(userRespository.getBeanFactory() == beanFactory);
       // System.out.println(beanFactory.getBean(BeanFactory.class));
        System.out.println(userRespository.getObjectFactory().getObject());
        System.out.println(beanFactory == userRespository.getObjectFactory().getObject());
        //内建bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("内建bean: " + environment);
    }
}
