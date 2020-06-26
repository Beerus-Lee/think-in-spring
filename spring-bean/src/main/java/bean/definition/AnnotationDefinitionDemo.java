package bean.definition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import spring.ioc.domain.User;

import java.util.Map;

@Import(AnnotationDefinitionDemo.Config.class)
public class AnnotationDefinitionDemo {
    public static void main(String[] args) {
        //创建beanfactory容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册config
        context.register(AnnotationDefinitionDemo.class);


        //命名bean方式注册
        registerUserBeanDefinition(context, "chaoge");

        //非命名bean方式注册
        registerUserBeanDefinition(context);

        //启动上下文
        context.refresh();



        Map<String, User> map = context.getBeansOfType(User.class);
        Map<String, Config> configMap = context.getBeansOfType(Config.class);
        System.out.println("User 类型的所有 beans :" + map);
        System.out.println("Config 类型的所有beans :" + configMap);


        context.close();

    }


    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L).addPropertyValue("name", "超哥");
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    @Component
    static class Config {
        @Bean(name = {"user", "chaoge-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("超哥");
            return user;
        }
    }

}
