package com.ouc.spring.foundation.annotation.jsr;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * JsrService
 *
 * @author skyUnv
 * created on 2018/5/11 20:53
 */
@Service(value = "jsrService")
public class JsrService {
    //    @Resource
    JsrDao jsrDao1;

    @Resource
    public void setJsrDao1(JsrDao jsrDao1) {
        this.jsrDao1 = jsrDao1;
    }

    @PreDestroy
    public void cleanALl() {
        System.out.println(this.getClass().getName() + ": 运行清理函数");
    }

    @PostConstruct
    public void initAll() {
        System.out.println(this.getClass().getName() + ": 运行初始化函数");
    }

    public void save() {
        jsrDao1.save();
    }

}
