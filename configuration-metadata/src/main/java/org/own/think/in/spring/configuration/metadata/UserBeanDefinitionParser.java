package org.own.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import spring.ioc.domain.User;

public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        setPropertyValue("id",element,builder);
        setPropertyValue("name",element,builder);
        setPropertyValue("cityEnum",element,builder);
    }

    private void setPropertyValue (String elementName,Element element ,BeanDefinitionBuilder builder) {
        String elementValue = element.getAttribute(elementName);
        if (StringUtils.hasText(elementValue)) {
            builder.addPropertyValue(elementName,elementValue);
        }
    }
}
