package org.own.think.in.spring.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class Handler extends sun.net.www.protocol.x.Handler {
    public static void main(String[] args) throws IOException {
        URL url = new URL("springx:///META-INF/production.properties");
        URLConnection connection = url.openConnection();
        System.out.println(StreamUtils.copyToString(connection.getInputStream(), Charset.defaultCharset()));

    }

}
