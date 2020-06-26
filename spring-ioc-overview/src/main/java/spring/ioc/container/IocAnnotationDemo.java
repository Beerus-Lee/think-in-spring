package spring.ioc.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.ioc.domain.User;

import java.util.Map;

public class IocAnnotationDemo {
    public static void main(String[] args) {
        //创建容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //将当前类注册到容器内
        context.register(IocAnnotationDemo.class);
        //启动上下文
        context.refresh();
        //依赖查找集合对象
        lookupCollectionsByType(context);
        //关闭上下文
        context.close();


    }

    @Bean
    public User user(){
        User user = new User();
        user.setId(2L);
        user.setName("超哥");
        return user;


    }


    private static void lookupCollectionsByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> map = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("类型查找集合：" + map);
        }
    }
}
