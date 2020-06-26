package spring.ioc.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import spring.ioc.domain.User;

import java.util.Map;

/**
 * IOC容器加载bean
 */
public class IocContainerDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:\\META-INF\\ioc-dependence-lookup.xml";
        int beanDefinitionCount = reader.loadBeanDefinitions(location);
        System.out.println("bean 加载个数：" + beanDefinitionCount);
        lookupCollectionsByType(beanFactory);

    }

    private static void lookupCollectionsByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> map = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("类型查找集合：" + map);
        }
    }
}
