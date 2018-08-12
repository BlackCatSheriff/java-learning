package com.ouc.spring.foundation.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * BeanAnnotation
 *
 * @author skyUnv
 * created on 2018/5/1 20:21
 */

//自定义ID
//@Component("myBeanAnnotation")

//省略名称的，系统默认类名称第一个字母小写作为 ID 写入 ApplicationContext 中
@Component

//default is singleton
@Scope

//每次重新 new
//@Scope("prototype")
public class BeanAnnotation {
    public void say() {
        System.out.println("BeanAnnotation say my hash code is: " + this.hashCode());
    }
}
