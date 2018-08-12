package com.ouc.spring.foundation.annotation.javabased;

/**
 * IntegerStore
 *
 * @author skyUnv
 * created on 2018/5/11 19:38
 */
public class IntegerStore implements Store<Integer> {
    public void init() {
        System.out.println(this.getClass().getName() + ":call init!");
    }
}
