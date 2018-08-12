package com.ouc.spring.foundation.autowiring;

/**
 * AutowiringDao
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 15:41
 */
public class AutowiringDao {
    public void say(String word){
        System.out.println("AutowiringDao@"+this.hashCode()+":" + word);
    }
}
