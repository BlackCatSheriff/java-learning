package com.ouc.spring.foundation.annotation.javabased;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * AppConfigTest
 *
 * @author skyUnv
 * created on 2018/5/10 21:31
 */
public class AppConfigTest extends UnitTestBase {

    public AppConfigTest(){
        super("classpath*:spring-beanannotation.xml");
    }

    @Test
    public void getStringStore() {
//        Store store = super.getBean("store");
        //JdbcDao jdbcDao = super.getBean("jdbcDao1");
        super.getBean("testStringStore");


    }
}