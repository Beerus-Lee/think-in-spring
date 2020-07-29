package org.own.think.in.spring.environment;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceDemo.class)
@TestPropertySource(
        properties = "user.name=11111f",
        locations = "classpath:/META-INF/test.properties"
)
public class TestPropertySourceDemo {

    @Value("${user.name}")
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;


    @Test
    public void testUserName() {
        PropertySources propertySources = environment.getPropertySources();
        for(PropertySource propertySource : propertySources) {
            System.out.println(propertySource + " : " + propertySource.getProperty("user.name"));
        }

    }
}
