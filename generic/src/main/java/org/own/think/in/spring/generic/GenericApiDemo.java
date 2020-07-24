package org.own.think.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class GenericApiDemo {
    public static void main(String[] args) {
        Class intClass = int.class;

        Class arrayClass = Object[].class;

        Class rawClass = String.class;

        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();

        System.out.println(parameterizedType.toString());

        Type[] types = parameterizedType.getActualTypeArguments();
        Stream.of(types)
                .map(TypeVariable.class::cast)
                .forEach(System.out::println);

    }
}
