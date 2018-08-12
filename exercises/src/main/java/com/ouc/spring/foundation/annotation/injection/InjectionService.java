package com.ouc.spring.foundation.annotation.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * InjectionService
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 11:32
 */

//使用默认的方式，因此 ID 为类名称第一个字母小写
//service 常用于注解 service 类
@Service
public class InjectionService {

    //这种注解方法和直接注解 setter 效果一样，设值注入，不需要写 setter，可以帮助生成
    @Autowired

    InjectionDao injectionDao;

    public InjectionService(){}

    //构造器注入
//    @Autowired
    public InjectionService(InjectionDao injectionDao) {
        this.injectionDao = injectionDao;
    }

    //设值注入
//    @Autowired
    //如果 属性必须被装填，就使用 require 修饰 setter ，不要使用 Autowired 注解
//    @Required
    public void setInjectionDao(InjectionDao injectionDao) {
        this.injectionDao = injectionDao;
    }


    public void save(String arg){
        System.out.println("Services 接受参数:" + arg);
        arg = arg + this.hashCode();
        injectionDao.save(arg);
        System.out.println("Services 处理成功!" );
    }

}
