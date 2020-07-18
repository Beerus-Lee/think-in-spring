package org.own.think.in.spring.resource;

import org.own.think.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

public class CustomizedResourcePatternResolveDemo {
    public static void main(String[] args) throws IOException {
        String currentFilePath = System.getProperty("user.dir") + "/resource/src/main/java/org/own/think/in/spring/resource/";
        String locationPattern = currentFilePath + "*.java";
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        patternResolver.setPathMatcher(new JavaPathMacher() );
        Resource[] resources = patternResolver.getResources(locationPattern);
        Stream.of(resources).map(ResourceUtils::getDefaultContent).forEach(System.out::println);



    }


    static class JavaPathMacher implements PathMatcher {

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
