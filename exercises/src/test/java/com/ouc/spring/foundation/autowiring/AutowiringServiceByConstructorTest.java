package com.ouc.spring.foundation.autowiring;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * AutowiringServiceByConstructorTest
 *
 * @author skyUnv
 * created on 2018/5/1 18:03
 */
public class AutowiringServiceByConstructorTest extends UnitTestBase {

    public AutowiringServiceByConstructorTest(){
        super("classpath*:spring-autowiring.xml");
    }
    @Test
    public void say() {
        AutowiringServiceByConstructor autowiringServiceByConstructor = super.getBean("autowiringServiceByConstructor");
        autowiringServiceByConstructor.say("this is a test sentence from autowiringServiceByConstructorTest!");
    }
}