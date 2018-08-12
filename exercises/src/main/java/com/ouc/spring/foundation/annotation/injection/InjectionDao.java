package com.ouc.spring.foundation.annotation.injection;

import org.springframework.stereotype.Repository;

/**
 * InjectionDao
 *
 * @author skyUnv
 * @email skyUnv@stu.ouc.edu.cn
 * @date 2018/5/1 11:32
 */
//使用默认的方式，因此 ID 为类名称第一个字母小写
//Repository 常用于注解 Dao 类
@Repository
public class InjectionDao {
    public void save(String str){
        System.out.println("模拟进行数据保存：" + str);
    }

}
