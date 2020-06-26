package bean.alias;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.domain.User;

public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        //通过别名获取
        User user = beanFactory.getBean("chaoge-user", User.class);
        //通过名称获取
        User userbyname = beanFactory.getBean("user",User.class);


        System.out.println("通过别名获取bean 与 通过名称获取bean是否相同：" + (user == userbyname));


    }
}
