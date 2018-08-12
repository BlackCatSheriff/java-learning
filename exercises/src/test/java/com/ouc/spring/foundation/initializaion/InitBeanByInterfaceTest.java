package com.ouc.spring.foundation.initializaion;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;
import org.springframework.beans.factory.InitializingBean;

import static org.junit.Assert.*;

/**
 * InitBeanByInterfaceTest
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 14:01
 */
public class InitBeanByInterfaceTest extends UnitTestBase {

    public InitBeanByInterfaceTest(){
        super("classpath*:spring-beans.xml");
    }

    @Test
    public void afterPropertiesSet() {
//        super.getBean("initBeanByInterface");
    }
}