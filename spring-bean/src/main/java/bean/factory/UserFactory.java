package bean.factory;

import spring.ioc.domain.User;

public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
