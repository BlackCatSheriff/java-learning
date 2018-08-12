package com.ouc.spring.foundation;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * HelloSpringTest
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 10:56
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class HelloSpringTest extends UnitTestBase {

    public HelloSpringTest(){
        super("classpath*:spring-beans.xml");
    }

    @Test
    public void say() {
        HelloSpring helloSpring = super.getBean("helloSpring");
        helloSpring.say("sty" + "id:" + helloSpring.hashCode());
        HelloSpring helloSpring1 = super.getBean("helloSpring");
        helloSpring.say("sty" + "id:" + helloSpring1.hashCode());
    }

}