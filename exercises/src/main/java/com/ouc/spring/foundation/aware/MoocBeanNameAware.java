package com.ouc.spring.foundation.aware;

import org.springframework.beans.factory.BeanNameAware;

/**
 * MoocBeanNameAware
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 15:09
 */
public class MoocBeanNameAware implements BeanNameAware {
    String beanName;
    public void setBeanName(String s) {
        this.beanName = s;
        System.out.println("BeanNameAware of this class:" + this.beanName);
    }
}
