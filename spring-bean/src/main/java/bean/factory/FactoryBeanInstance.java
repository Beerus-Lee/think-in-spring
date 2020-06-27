package bean.factory;

import org.springframework.beans.factory.FactoryBean;
import spring.ioc.domain.User;

public class FactoryBeanInstance implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
