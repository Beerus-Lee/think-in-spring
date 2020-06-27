package bean.instance;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.domain.User;

public class BeanInstanceDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:\\META-INF\\bean-instance-context.xml");
        //通过静态方法初始化
        User user = beanFactory.getBean("user-by-static-method",User.class);
        System.out.println(user);
        //通过工厂创建
        User userByFactory = beanFactory.getBean("user-by-factory-method",User.class);
        System.out.println(userByFactory);
        //通过factorybean创建
        User userFactory = beanFactory.getBean("user-by-factory-bean",User.class);
        System.out.println(userFactory);
        System.out.println(user == userByFactory);
        System.out.println(user == userFactory);


    }
}
