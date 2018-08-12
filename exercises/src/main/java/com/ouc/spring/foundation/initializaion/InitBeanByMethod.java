package com.ouc.spring.foundation.initializaion;

/**
 * InitBean
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 13:43
 */
public class InitBeanByMethod {
    // 在 XML 使用 ini-method 初始化
    public void init() {
        System.out.println("这是使用 init-method 进行初始化");
    }

    public void destroy(){
        System.out.println("这是使用 destroy-method 进行初始化");
    }
}
