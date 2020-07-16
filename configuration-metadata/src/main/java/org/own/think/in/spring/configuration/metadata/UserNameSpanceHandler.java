package org.own.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class UserNameSpanceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
