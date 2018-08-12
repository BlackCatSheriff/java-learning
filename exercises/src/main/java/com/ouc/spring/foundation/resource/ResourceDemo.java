package com.ouc.spring.foundation.resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * ResourceDemo
 *
 * @author skyUnv
 * created on 2018/5/1 18:22
 */
public class ResourceDemo implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void resourceClasspath(){
        Resource resource = this.applicationContext.getResource("classpath:config.txt");
        try {
            System.out.println("[classpath] File name:" + resource.getFilename() + " File length:" + resource.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
