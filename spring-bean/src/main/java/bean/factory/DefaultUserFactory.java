package bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class DefaultUserFactory implements UserFactory, InitializingBean {


    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct初始化bean....");
    }


    public void initUserFactory(){
        System.out.println("自定义初始化bean....");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean初始化bean....");
    }
}
