package com.ouc.spring.foundation.annotation.injection;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * InjectionServiceTest
 *
 * @author skyUnv
 * created on 2018/5/1 20:44
 */
public class InjectionServiceTest extends UnitTestBase {

    public InjectionServiceTest(){
        super("classpath*:spring-beanannotation.xml");
    }

    @Test
    public void save() {
        InjectionService injectionService = super.getBean("injectionService");
        injectionService.save("this is a test!");
    }
}