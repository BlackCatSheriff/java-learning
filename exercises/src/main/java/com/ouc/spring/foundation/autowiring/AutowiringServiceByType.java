package com.ouc.spring.foundation.autowiring;

/**
 * AutowiringServiceByType
 *
 * @author skyUnv
 * created on  2018/5/1 16:32 by skyUnv
 */
public class AutowiringServiceByType {
    AutowiringDao autowiringDao;
    AutowiringDao autowiringDao1;


//    public void setAutowiringDao(AutowiringDao autowiringDao) {
//        this.autowiringDao = autowiringDao;
//        System.out.println("AutowiringServiceByType init dao");
//    }

    //通过 byType 实现自动装配没有对函数名称严格的要求，只要函数名中包含 set 就可以
    public void setDao(AutowiringDao autowiringDao) {
        this.autowiringDao = autowiringDao;
        System.out.println("AutowiringServiceByType init dao");
    }

    public void setAutowiringDao1(AutowiringDao autowiringDao1) {
        this.autowiringDao1 = autowiringDao1;
        System.out.println("AutowiringServiceByType init dao1");
    }

    public void say(String word){
        autowiringDao.say(word);
        autowiringDao1.say(word);
    }
}
