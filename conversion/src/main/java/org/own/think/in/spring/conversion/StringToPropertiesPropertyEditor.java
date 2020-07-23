package org.own.think.in.spring.conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {


    @Override
    public String getAsText() {
        Properties properties = (Properties)getValue();

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Object,Object> entry : properties.entrySet()) {
            stringBuilder.append(entry.getKey() + ":" + entry.getValue());
        }
        return stringBuilder.toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setValue(properties);
    }
}
