package com.ouc.spring.foundation.hook;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * InjectionServiceTest
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 11:45
 */
public class InjectionServiceTest extends UnitTestBase {

    public InjectionServiceTest(){
        super("classpath*:spring-beans.xml");
    }

    @Test
    public void setter() {
        InjectionService injectionService = super.getBean("injectionService");
        injectionService.save("save success");
    }


    @Test
    public void cons() {
        InjectionService injectionService = super.getBean("injectionService");
        injectionService.save("save success");
    }
}