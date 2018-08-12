package com.ouc.spring.foundation.aware;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * MoocBeanNameAwareTest
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 15:21
 */
public class MoocBeanNameAwareTest extends UnitTestBase {

    public MoocBeanNameAwareTest(){
        super("classpath*:spring-beans.xml");
    }
    @Test
    public void setBeanName() {

    }
}