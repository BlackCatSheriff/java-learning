package com.ouc.spring.foundation.annotation.javabased;

/**
 * StringStore
 *
 * @author skyUnv
 * created on 2018/5/10 21:27
 */
public class StringStore implements Store<String>{

    public void init(){
        System.out.println(this.getClass().getName() + ":call init!");
    }

    public void destroy(){
        System.out.println("This is destroy");
    }

}
