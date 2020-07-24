package org.own.think.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class,Comparable.class,"getString");
        System.out.println("=====================================");
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class,"getArrayList");
        System.out.println("=====================================");
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class,"getStringList");
        System.out.println("=====================================");

        Map<TypeVariable, Type> map= GenericTypeResolver.getTypeVariableMap(String.class);
        System.out.println(map);



    }
    public static StringList getStringList() {
        return null;
    }

    public static String getString() {
        return null;
    }

    public static ArrayList<Object> getArrayList() {
        return null;
    }

    private static void displayReturnTypeGenericInfo(Class<?> containsClass,Class<?> genericClass,String methodName,Class... argurmentsType) throws NoSuchMethodException {
        Method method = containsClass.getMethod(methodName, argurmentsType);
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method,genericClass);
        System.out.println(returnType);
        Class<?> returnArgumentType = GenericTypeResolver.resolveReturnTypeArgument(method,genericClass);
        System.out.println(returnArgumentType);
    }

}
