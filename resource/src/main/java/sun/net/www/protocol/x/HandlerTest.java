package sun.net.www.protocol.x;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class HandlerTest {

    public static void main(String[] args) throws IOException {
        URL url = new URL("x:///META-INF/default.properties");
        URLConnection connection = url.openConnection();
        System.out.println(StreamUtils.copyToString(connection.getInputStream(), Charset.defaultCharset()));

    }
}
