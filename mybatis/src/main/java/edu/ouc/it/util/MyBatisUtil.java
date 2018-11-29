package edu.ouc.it.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * MyBatisUtil
 *
 * @author skyUnv
 * created on 2018/11/3 19:49
 */
public class MyBatisUtil {

    private static MyBatisUtil myBatisUtil = new MyBatisUtil();
    private SqlSessionFactory sf;
    private MyBatisUtil(){
        try {
            sf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openseesion(){
        return myBatisUtil.sf.openSession();
    }

}
