package com.ouc.bible.typeinfo;

import java.lang.reflect.InvocationTargetException;

/**
 * ReflectLoadClass
 *
 * @author skyUnv
 * created on 2018/8/15 14:05
 */

class Test{
    public Test(int v){
        System.out.println("Test create successful , init p is "+v);
    }

    public Test(String  str, int v){
        System.out.printf("use String:%s  int:%d",str,v);
    }
}
public class ReflectLoadClass {

    public static void main(String[] args) throws NoSuchMethodException {

        try {
            Test.class.getDeclaredConstructor(String.class,int.class).newInstance("hhhh",99);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


//        for (Method method : Test.class.getMethods()) {
//            System.out.println(method);
//        }
//        for (Constructor<?> constructor : Test.class.getConstructors()) {
//
//            for (Class<?> aClass : constructor.getParameterTypes()) {
//                System.out.println(aClass.getName());
//            }
//        }
    }

}
