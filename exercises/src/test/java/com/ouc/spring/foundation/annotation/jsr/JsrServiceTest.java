package com.ouc.spring.foundation.annotation.jsr;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

/**
 * JsrServiceTest
 *
 * @author skyUnv
 * created on 2018/5/11 20:57
 */
public class JsrServiceTest extends UnitTestBase {

    public JsrServiceTest() {

        super("classpath*:spring-jsr.xml");
    }
    @Test
    public void save() {
        JsrService jsrService = super.getBean("jsrService");
        jsrService.save();
    }
}