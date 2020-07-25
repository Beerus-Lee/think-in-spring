package org.own.think.in.spring.generic;

import org.springframework.core.ResolvableType;

public class ResolveTypeDemo {
    public static void main(String[] args) {
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        ResolvableType  typeSuperType = resolvableType.getSuperType();
        System.out.println(typeSuperType);
        System.out.println(resolvableType.getSuperType().getSuperType());
        System.out.println(resolvableType.asCollection().resolve());
        System.out.println(resolvableType.asCollection().resolveGeneric(0));
    }
}
