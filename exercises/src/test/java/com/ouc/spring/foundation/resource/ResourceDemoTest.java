package com.ouc.spring.foundation.resource;

import com.ouc.spring.UnitTestBase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ResourceDemoTest
 *
 * @author skyUnv
 * created on 2018/5/1 19:48
 */
public class ResourceDemoTest extends UnitTestBase {

    public ResourceDemoTest(){
        super("classpath*:spring-resource.xml");
    }

    @Test
    public void resourceClasspath() {
        ResourceDemo resourceDemo = super.getBean("resourceDemo");
        resourceDemo.resourceClasspath();
    }
}