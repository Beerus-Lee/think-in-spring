package org.own.think.in.spring.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import spring.ioc.domain.User;

import java.util.List;
import java.util.Locale;

public class ErrorsMessageDemo {

    public static void main(String[] args) {
        User user = new User();
        user.setName("超哥");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        errors.reject("-1");
        errors.rejectValue("name", "100001");
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> objectErrors = errors.getAllErrors();
        System.out.println("globalErrors: " + globalErrors);
        System.out.println("fieldErrors: " + fieldErrors);
        System.out.println("objectErrors: " + objectErrors);

        MessageSource messageSource = createMessageSource();
        for (ObjectError error :errors.getAllErrors()) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
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
