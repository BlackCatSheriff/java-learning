package com.ouc.spring.foundation.autowiring;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

/**
 * AutowiringServiceByTypeTest
 *
 * @author skyUnv
 * created on 2018/5/1 16:48
 */
public class AutowiringServiceByTypeTest extends UnitTestBase {

    public AutowiringServiceByTypeTest() {
        super("classpath*:spring-autowiring.xml");
    }

    @Test
    public void say() {
        AutowiringServiceByType autowiringServiceByType = super.getBean("autowiringServiceByType");
        autowiringServiceByType.say("this is a test sentence from serviceByType");
    }
}