package bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {


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


    @PreDestroy
    public void destory(){
        System.out.println("@PreDestroy 销毁bean.....");
    }

    public void destoryUserFactory(){
        System.out.println("自定义销毁bean");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destory()销毁bean...");
    }

    @Override
    public void finalize(){
        System.out.println("正在被GC.....");

    }

}
