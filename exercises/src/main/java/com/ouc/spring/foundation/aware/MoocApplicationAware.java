package com.ouc.spring.foundation.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * MoocApplicationAware
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 15:09
 */
public class MoocApplicationAware implements ApplicationContextAware, BeanNameAware {

    //获取这个 IOC 上下文，就可以在其他方法对其操作
    ApplicationContext applicationContext;
    String beanName;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        //do something
    }

    public void setBeanName(String s) {
        this.beanName = s;
    }

    public void productAnother(){
        System.out.println("By applicationAware to get another obj :" + applicationContext.getBean(this.beanName).hashCode());
    }
}
