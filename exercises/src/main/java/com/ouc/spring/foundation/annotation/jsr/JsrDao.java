package com.ouc.spring.foundation.annotation.jsr;

import org.springframework.stereotype.Repository;

/**
 * JsrDao
 *
 * @author skyUnv
 * created on 2018/5/11 20:53
 */
@Repository
public class JsrDao {
    public void save(){
        System.out.println("JsrDao invoke!");
    }
}
