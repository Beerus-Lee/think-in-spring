package org.own.think.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

public class FileSystemResourceDemo {
    public static void main(String[] args) throws IOException {
        String currentFilePath = System.getProperty("user.dir") + "\\resource\\src\\main\\java\\org\\own\\think\\in\\spring\\resource\\FileSystemResourceDemo.java";
        System.out.println(currentFilePath);
        FileSystemResource fileSystemResource = new FileSystemResource(currentFilePath);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "utf-8");
        try(Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }

    }
}
