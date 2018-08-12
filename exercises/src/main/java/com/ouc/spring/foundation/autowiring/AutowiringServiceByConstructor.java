package com.ouc.spring.foundation.autowiring;

/**
 * AutowiringServiceByConstructor
 *
 * @author skyUnv
 * created on 2018/5/1 18:00
 */
public class AutowiringServiceByConstructor {
    AutowiringDao autowiringDao;
    AutowiringDao autowiringDao1;

    public AutowiringServiceByConstructor(AutowiringDao autowiringDao, AutowiringDao autowiringDao1) {
        this.autowiringDao = autowiringDao;
        this.autowiringDao1 = autowiringDao1;
        System.out.println("AutowiringServiceByConstructor init double obj: Dao Dao1");
    }



    public void say(String word){
        autowiringDao.say(word);
        autowiringDao1.say(word);
    }
}
