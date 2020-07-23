package org.own.think.in.spring.conversion;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import spring.ioc.domain.User;

public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(User.class, "context", new StringToPropertiesPropertyEditor());
    }
}
