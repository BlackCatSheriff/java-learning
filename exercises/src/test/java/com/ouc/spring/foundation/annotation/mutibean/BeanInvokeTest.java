package com.ouc.spring.foundation.annotation.mutibean;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * BeanInvokeTest
 *
 * @author skyUnv
 * created on 2018/5/1 21:23
 */
public class BeanInvokeTest extends UnitTestBase {

    public BeanInvokeTest(){
        super("classpath*:spring-beanannotation.xml");
    }

    @Test
    public void printList() {
        BeanInvoke beanInvoke = super.getBean("beanInvoke");
//        beanInvoke.printList();
//        beanInvoke.printMap();
//        beanInvoke.printBeanInterface();
    }
}