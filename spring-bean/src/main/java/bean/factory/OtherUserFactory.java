package bean.factory;

import spring.ioc.domain.User;

public class OtherUserFactory implements UserFactory{
    public User createUser() {
        User user = new User();
        user.setId(3L);
        user.setName("超哥");
        return user;
    }
}
