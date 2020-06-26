package bean.definition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import spring.ioc.domain.User;

public class BeanDefinitionDemo {
    public static void main(String[] args) {
        //1.通过beandefinitionbuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //设置属性
        beanDefinitionBuilder
                .addPropertyValue("id",1)
                .addPropertyValue("name","超哥");
        //返回beandefintaion 属性
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        //2.通过AbstractBeanDefinition类来创建bean
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置beandefiniton 类型
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues
                .add("id",1)
                .add("name","超哥");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }
}
