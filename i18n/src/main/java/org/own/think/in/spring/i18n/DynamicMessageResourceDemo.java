package org.own.think.in.spring.i18n;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class DynamicMessageResourceDemo extends AbstractMessageSource implements ResourceLoaderAware {

    private String messagePropertyName = "msg.properties";

    private final String ENCODING = "UTF-8";

    private String messagePropertyPath = "/META-INF/" + messagePropertyName;

    private ResourceLoader resourceLoader;

    private final Resource messagePropertiesResource;

    private final Properties messageResourceProperty ;

    private final ExecutorService executorService;




    private DynamicMessageResourceDemo() {
        this.messagePropertiesResource = getMessageResource();
        this.messageResourceProperty = loadMessageResourceProperty();
        this.executorService = Executors.newSingleThreadExecutor();
        //监听文件
        onMessageResourcePropertiesChanges();

    }

    private void onMessageResourcePropertiesChanges() {
        if (this.messagePropertiesResource.isFile()) {
            try {
                File file = this.messagePropertiesResource.getFile();
                FileSystem fileSystem = FileSystems.getDefault();
                WatchService watchService = fileSystem.newWatchService();
                Path filePath = file.toPath();
                Path dirPath = filePath.getParent();
                dirPath.register(watchService, ENTRY_MODIFY);
                System.out.println(dirPath);
                processMessagePropertiesSourceChanged(watchService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processMessagePropertiesSourceChanged(WatchService watchService) {
        executorService.submit(() -> {
            while (true) {
                WatchKey watchKey = watchService.take();
                try {
                    if (watchKey.isValid()) {
                        for (WatchEvent watchEvent : watchKey.pollEvents()) {
                           Watchable watchable = watchKey.watchable();
                           //目录路径
                           Path path = (Path) watchable;
                           //监听文件路径
                           Path fileRelativePath = (Path) watchEvent.context();
                           if (this.messagePropertyName.equals(fileRelativePath.getFileName().toString())) {
                               Path filePath = path.resolve(path);
                               File file = filePath.toFile();
                               Properties properties = loadMessageResourceProperty(new FileReader(file));
                               synchronized (this.messageResourceProperty) {
                                   this.messageResourceProperty.clear();
                                   this.messageResourceProperty.putAll(properties);
                               }

                           }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (watchKey != null) {
                        watchKey.reset();
                    }
                }
            }
            
        });

    }

    private Properties loadMessageResourceProperty() {
        EncodedResource encodedResource = new EncodedResource(this.messagePropertiesResource,ENCODING);
        try {
            return loadMessageResourceProperty(encodedResource.getReader());
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private Properties loadMessageResourceProperty(Reader reader) {
        Properties properties = new Properties();
        try {
            try {
                properties.load(reader);
            } catch (IOException e) {
               throw new RuntimeException();
            }
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
               throw new RuntimeException();
            }
        }
        return properties;
    }


    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = this.messageResourceProperty.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)) {
            MessageFormat messageFormat = new MessageFormat(messageFormatPattern, locale);
            return messageFormat;
        }

        return null;
    }


    public static void main(String[] args) {
        DynamicMessageResourceDemo messageResourceDemo = new DynamicMessageResourceDemo();
        String message = messageResourceDemo.getMessage("name", new Object[]{}, Locale.getDefault());
        System.out.println(message);

    }

    private Resource getMessageResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(this.messagePropertyPath);
        return resource;
    }


    private ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
