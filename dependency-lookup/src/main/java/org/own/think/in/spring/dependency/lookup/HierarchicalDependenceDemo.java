package org.own.think.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HierarchicalDependenceDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        HierarchicalBeanFactory parentFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentFactory);

        displayBeanFactory(beanFactory,"user");
        displayBeanFactory(parentFactory, "user");

        displayLocalBeanFactory(beanFactory,"user");
        displayLocalBeanFactory(parentFactory, "user");


        context.refresh();

        context.close();


    }


    private static boolean displayBeanFactory(HierarchicalBeanFactory beanFactory,String beanName) {
        System.out.println("当前 beanFactory:" + beanFactory + "包含beanName: " + beanName + ": " + contains(beanFactory,beanName));
        return contains(beanFactory, beanName);
    }

    private static boolean contains(HierarchicalBeanFactory beanFactory,String beanName) {
        BeanFactory parentFactory = beanFactory.getParentBeanFactory();
        if (parentFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentFactory);
            if (contains(hierarchicalBeanFactory,beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }


    private static boolean displayLocalBeanFactory(HierarchicalBeanFactory beanFactory,String beanName) {
        System.out.println("当前 beanFactory:" + beanFactory + "包含beanName: " + beanName + ": " + beanFactory.containsLocalBean(beanName));
        return beanFactory.containsLocalBean(beanName);
    }

    private static HierarchicalBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/ioc-dependence-lookup.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }
}



