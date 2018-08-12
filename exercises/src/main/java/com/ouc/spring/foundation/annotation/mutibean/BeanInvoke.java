package com.ouc.spring.foundation.annotation.mutibean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * BeanInvoke
 *
 * @author skyUnv
 * created on 2018/5/1 21:19
 */

@Component
public class BeanInvoke {

    @Autowired
    List<BeanInterface> list;


    @Autowired
    // string : IOC 中 ID
    Map<String, BeanInterface> map;


    //由于 BeanInterface 有两个实现类，所以必须明确自动装配的类型是什么
//    @Autowired
//    @Qualifier("beanImplOne")
    //使用 Resource 代替上面两个，name 就是 beanID
    @Resource(name = "beanImplOne")
    BeanInterface beanInterface;



    //将 IOC 容器中的 BeanInterface 类族的对象 自动装备到参数中
    @Autowired
    public void collect2List(List<BeanInterface> list) {
        System.out.println("parameter collect2List collect all interface implClass...");
        if (null != list && 0 != list.size()) {
            for (BeanInterface beanInterface : list)
                System.out.println(beanInterface.getClass().getName());
        } else {
            System.out.println("List<BeanInterface> is null!!!!!!!!");
        }
    }


    //只收集 BeanInterface 中某一特定的类
    @Autowired
    public void collect2ListOnly(@Qualifier("beanImplTwo") List<BeanInterface> list) {
        System.out.println("parameter collect2List only collect beanImplTwo ...");
        if (null != list && 0 != list.size()) {
            for (BeanInterface beanInterface : list)
                System.out.println(beanInterface.getClass().getName());
        } else {
            System.out.println("List<BeanInterface> is null!!!!!!!!");
        }
    }



    public void printList() {
        System.out.println("List...");
        if (null != list && 0 != list.size()) {
            for (BeanInterface beanInterface : list)
                System.out.println(beanInterface.getClass().getName());
        } else {
            System.out.println("List<BeanInterface> is null!!!!!!!!");
        }
    }

    public void printMap() {
        System.out.println("Map...");
        if (null != map && 0 != map.size()) {
            for(Map.Entry<String, BeanInterface> entry : map.entrySet())
                System.out.println(entry.getKey()+ "   " + entry.getValue().getClass().getName());
        } else {
            System.out.println(" Map<String, BeanInterface> map is null!!!!!!!!");
        }
    }

    public void printBeanInterface(){
        if (beanInterface != null) {
            System.out.println(beanInterface.getClass().getName());
        }else {
            System.out.println("beanInterface is null!!!!!");
        }


    }
}
