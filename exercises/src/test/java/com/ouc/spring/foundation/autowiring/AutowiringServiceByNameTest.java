package com.ouc.spring.foundation.autowiring;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * AutowiringServiceByNameTest
 *
 * @author skyUnv
 * created on 2018/5/1 16:36
 */
public class AutowiringServiceByNameTest extends UnitTestBase {

    public AutowiringServiceByNameTest(){
        super("classpath*:spring-autowiring.xml");
    }

    @Test
    public void say() {
        AutowiringServiceByName autowiringServiceByName = super.getBean("autowiringServiceByName");
        autowiringServiceByName.say("this is a test sentence by serviceByName");
    }
}