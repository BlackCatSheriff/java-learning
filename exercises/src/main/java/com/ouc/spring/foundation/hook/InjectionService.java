package com.ouc.spring.foundation.hook;

/**
 * InjectionService
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 11:32
 */
public class InjectionService {

    InjectionDao injectionDao;
    public InjectionService(){}
    public InjectionService(InjectionDao injectionDao) {
        this.injectionDao = injectionDao;
    }

    //设值注入，必须有 set 方法，这样才能被 spring 调用，而且函数名称就是这样的方式，规范
    public void setInjectionDao(InjectionDao injectionDao) {
        this.injectionDao = injectionDao;
    }

    //构造器注入
    public void save(String arg){
        System.out.println("Services 接受参数:");
        arg = arg + this.hashCode();
        injectionDao.save(arg);
        System.out.println("Services 处理成功!" );
    }

}
