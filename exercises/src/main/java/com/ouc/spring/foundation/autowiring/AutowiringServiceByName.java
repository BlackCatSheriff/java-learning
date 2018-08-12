package com.ouc.spring.foundation.autowiring;

import java.security.SecureRandom;

/**
 * AutowiringServiceByName
 *
 * @author skyUnv
 * created on  2018/5/1 16:32 by skyUnv
 */
public class AutowiringServiceByName {
    AutowiringDao autowiringDao;
    AutowiringDao autowiringDao1;

    public void setAutowiringDao1(AutowiringDao autowiringDao1) {
        this.autowiringDao1 = autowiringDao1;
        System.out.println("AutowiringServiceByName init dao1");
    }

    public void setAutowiringDao(AutowiringDao autowiringDao) {
        System.out.println("AutowiringServiceByName init dao");

        this.autowiringDao = autowiringDao;
    }

    public void say(String word){
        autowiringDao.say(word);

        //自动装配不能给 autowiringDao1 赋值，只能给 autowiringDao 赋值，因为 autowiringDao 和 IOC 中 AutowiringDao 类的 ID 一样
//        autowiringDao1.say(word);
    }
}
