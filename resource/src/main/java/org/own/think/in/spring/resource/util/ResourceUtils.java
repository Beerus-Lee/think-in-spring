package org.own.think.in.spring.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;
import java.util.Map;

public class ResourceUtils {
    public static String getContent(Resource resource, String encode) throws IOException {
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        try(Reader reader = encodedResource.getReader()) {
            return IOUtils.toString(reader);
        }

    }

    public static String getDefaultContent(Resource resource)  {
        try {
            return getContent(resource,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


}
