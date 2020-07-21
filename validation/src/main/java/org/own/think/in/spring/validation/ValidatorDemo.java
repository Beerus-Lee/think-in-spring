package org.own.think.in.spring.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.*;
import spring.ioc.domain.User;

import java.util.Locale;

public class ValidatorDemo {
    public static void main(String[] args) {
        Validator validator = new UserValidation();
        User user = new User();
        System.out.println("校验 user: " + validator.supports(User.class));
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user,errors);
        MessageSource  messageSource = createMessageSource();
        for (ObjectError error : errors.getAllErrors()) {
           String message = messageSource.getMessage(error.getCode(),error.getArguments(),Locale.getDefault());
            System.out.println(message);
        }





    }

    static class UserValidation implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.equals(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User)target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "100002");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "100001");

        }
    }

    private static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("-1", Locale.getDefault(), "user.property is not null");
        messageSource.addMessage("100001", Locale.getDefault(), "name is not null");
        messageSource.addMessage("100002",Locale.getDefault(),"id is not null");
        return messageSource;
    }
}
