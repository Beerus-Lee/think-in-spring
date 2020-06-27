package bean.definition;

import bean.factory.DefaultUserFactory;
import bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanInitalizationDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建beanfactory容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册configuration 类
        context.register(BeanInitalizationDemo.class);

        //启动容器
        context.refresh();
        System.out.println("Spring 上下文启动");

        UserFactory userFactory = context.getBean(UserFactory.class);
       // System.out.println(userFactory);
        //关闭容器
        System.out.println("spring上下文准备关闭...");
        context.close();
        System.out.println("spring 上下文关闭");


    }


    @Bean(initMethod = "initUserFactory",destroyMethod = "destoryUserFactory")
    @Lazy
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }
}
