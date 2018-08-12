package com.ouc.spring.foundation.initializaion;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * InitBeanByInterface
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 13:58
 */
public class InitBeanByInterface implements InitializingBean, DisposableBean {



    public void afterPropertiesSet() throws Exception {
        System.out.println("使用 initialization interface 初始化");
    }

    public void destroy() throws Exception {
        System.out.println("使用 Disposable interface 初始化");
    }

    //说明同时使用接口和方法进行初始化操作有顺序并且两个都被执行

    // 在 XML 使用 ini-method 初始化
    public void start() {
        System.out.println("这是使用 init-method 进行初始化");
    }

    public void stop(){
        System.out.println("这是使用 destroy-method 进行初始化");
    }


}
