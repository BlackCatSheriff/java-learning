package com.ouc.spring.foundation.aware;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * MoocApplicationAwareTest
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 15:25
 */
public class MoocApplicationAwareTest extends UnitTestBase {

    public MoocApplicationAwareTest(){
        super("classpath*:spring-beans.xml");
    }

    @Test
    public void productAnother() {
        MoocApplicationAware moocApplicationAware = super.getBean("moocApplicationAware");
        System.out.println("test case get obj by super.getBean:" + moocApplicationAware.hashCode());
        moocApplicationAware.productAnother();
        //print same hashCode because of IOC default scope is singleton
    }
}