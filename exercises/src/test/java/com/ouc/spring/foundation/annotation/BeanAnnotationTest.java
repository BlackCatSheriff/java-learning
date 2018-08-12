package com.ouc.spring.foundation.annotation;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * BeanAnnotationTest
 *
 * @author skyUnv
 * created on 2018/5/1 20:27
 */
public class BeanAnnotationTest extends UnitTestBase {

    public BeanAnnotationTest(){
        super("classpath*:spring-beanannotation.xml");
    }

    @Test
    public void say() {
        BeanAnnotation beanAnnotation = super.getBean("beanAnnotation");
        beanAnnotation.say();

        BeanAnnotation beanAnnotation1 = super.getBean("beanAnnotation");
        beanAnnotation1.say();

    }
}