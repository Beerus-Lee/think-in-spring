package org.own.think.in.spring.conversion;

import java.beans.PropertyEditor;
import java.util.Properties;

public class PropertyEditorDemo {
    public static void main(String[] args) {
        String text = "name = 超";
        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        propertyEditor.setAsText(text);
        System.out.println(propertyEditor.getAsText());

    }
}
